package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lex.ielts.data.local.PrepopulatedData

data class WordFamilyItem(
    val base: String,
    val verb: String,
    val noun: String,
    val adjective: String,
    val trapPrompt: String,
    val options: List<String>,
    val correctIndex: Int
)

@Composable
fun WordFamilyScreen(
    onProgressUpdate: (Int) -> Unit = {}
) {
    val items = remember { PrepopulatedData.WORD_FAMILIES }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var answeredCount by remember { mutableStateOf(0) }
    var correctCount by remember { mutableStateOf(0) }

    val currentItem = items[currentIndex]

    LaunchedEffect(currentIndex) {
        selectedOption = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text(
            text = "МАТРИЦА ЧАСТЕЙ РЕЧИ (30 КОРНЕЙ)",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = "Искоренение подмены Verb vs Noun vs Adjective в эссе и отчетах",
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Статистика сессии
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Корень ${currentIndex + 1} из ${items.size}",
                color = Color(0xFF80D8FF),
                fontSize = 13.sp
            )
            Text(
                text = "Точность: ${if (answeredCount > 0) (correctCount * 100 / answeredCount) else 100}%",
                color = Color(0xFF00E676),
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Горизонтальная лента корней с кликом
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items.indices.toList()) { index ->
                val isCurrent = index == currentIndex
                Box(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .background(
                            if (isCurrent) Color(0xFF00E676) else Color(0xFF1E1E1E),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable { currentIndex = index }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = items[index].base,
                        color = if (isCurrent) Color.Black else Color.LightGray,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Основное тело (скроллируемое)
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                // Карточка структуры словоформ: ЧЕТКАЯ ВЕРТИКАЛЬНАЯ РАЗМЕТКА
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "КОРЕНЬ: ${currentItem.base}",
                            color = Color(0xFF00E676),
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Строка глагола
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Глагол (V):",
                                color = Color(0xFF80D8FF),
                                fontSize = 12.sp,
                                modifier = Modifier.width(130.dp)
                            )
                            Text(currentItem.verb, color = Color.White, fontSize = 13.sp)
                        }

                        // Строка существительного
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Существительное (N):",
                                color = Color(0xFFB39DDB),
                                fontSize = 12.sp,
                                modifier = Modifier.width(130.dp)
                            )
                            Text(currentItem.noun, color = Color.White, fontSize = 13.sp)
                        }

                        // Строка прилагательного
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Прилагательное (Adj):",
                                color = Color(0xFFFFD54F),
                                fontSize = 12.sp,
                                modifier = Modifier.width(130.dp)
                            )
                            Text(currentItem.adjective, color = Color.White, fontSize = 13.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Контекстная ловушка
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF16251A))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Ловушка IELTS (вставьте правильную часть речи):",
                            color = Color(0xFF80D8FF),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = currentItem.trapPrompt,
                            color = Color.White,
                            fontSize = 15.sp,
                            lineHeight = 22.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Варианты ответов: ВЕРТИКАЛЬНАЯ РАСКЛАДКА БЕЗ СМЯТИЯ ТЕКСТА
                currentItem.options.forEachIndexed { optIdx, optText ->
                    val isSelected = selectedOption == optIdx
                    val isCorrect = optIdx == currentItem.correctIndex

                    val cardBg = when {
                        selectedOption == null -> Color(0xFF1E1E1E)
                        isSelected && isCorrect -> Color(0xFF163820)
                        isSelected && !isCorrect -> Color(0xFF381616)
                        optIdx == currentItem.correctIndex -> Color(0xFF163820).copy(alpha = 0.6f)
                        else -> Color(0xFF181818)
                    }

                    Card(
                        onClick = {
                            if (selectedOption == null) {
                                selectedOption = optIdx
                                answeredCount++
                                if (isCorrect) correctCount++
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${optIdx + 1}.",
                                color = if (isSelected) Color(0xFF00E676) else Color.Gray,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = optText,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                // Пояснение после ответа
                selectedOption?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    val isCorrect = it == currentItem.correctIndex
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCorrect) Color(0xFF16281C) else Color(0xFF2E1717)
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = if (isCorrect)
                                "✅ ВЕРНО! Использована форма '${currentItem.options[currentItem.correctIndex]}'."
                            else
                                "❌ ОШИБКА: Требуется '${currentItem.options[currentItem.correctIndex]}'. Обратите внимание на синтаксическую позицию слова.",
                            color = if (isCorrect) Color(0xFF00E676) else Color(0xFFFF5252),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Навигация между корнями
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0,
                modifier = Modifier.weight(1f).padding(end = 6.dp)
            ) {
                Text("Предыдущий корень", color = Color.White, fontSize = 11.sp)
            }

            OutlinedButton(
                onClick = { if (currentIndex < items.size - 1) currentIndex++ },
                enabled = currentIndex < items.size - 1,
                modifier = Modifier.weight(1f).padding(start = 6.dp)
            ) {
                Text("Следующий корень", color = Color.White, fontSize = 11.sp)
            }
        }
    }
}
