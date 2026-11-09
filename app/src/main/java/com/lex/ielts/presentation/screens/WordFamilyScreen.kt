package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

    // Сброс при смене корня
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
            text = "МАТРИЦА ЧАСТЕЙ РЕЧИ (WORD FAMILIES)",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "22 корня: дифференциация Verb vs Noun vs Adjective для IELTS Academic",
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 10.dp)
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

        Spacer(modifier = Modifier.height(10.dp))

        // Горизонтальный скролл быстрых корней
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items.indices.toList()) { index ->
                val isCurrent = index == currentIndex
                Box(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .background(
                            if (isCurrent) Color(0xFF00E676) else Color(0xFF222222),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = items[index].base,
                        color = if (isCurrent) Color.Black else Color.LightGray,
                        fontSize = 10.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Карточка структуры словоформ
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "БАЗОВЫЙ КОРЕНЬ: ${currentItem.base}",
                    color = Color(0xFF80D8FF),
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("ГЛАГОЛ (Verb):", color = Color.Gray, fontSize = 11.sp)
                        Text(currentItem.verb, color = Color.White, fontSize = 14.sp)
                    }
                    Column {
                        Text("СУЩЕСТВИТЕЛЬНОЕ (Noun):", color = Color.Gray, fontSize = 11.sp)
                        Text(currentItem.noun, color = Color.White, fontSize = 14.sp)
                    }
                    Column {
                        Text("ПРИЛАГАТЕЛЬНОЕ (Adj):", color = Color.Gray, fontSize = 11.sp)
                        Text(currentItem.adjective, color = Color.White, fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Тестовый контекст
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF16221A))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Контекстная ловушка IELTS:",
                    color = Color(0xFF80D8FF),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = currentItem.trapPrompt,
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Варианты ответов
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            currentItem.options.forEachIndexed { optIdx, optText ->
                val isSelected = selectedOption == optIdx
                val isCorrect = optIdx == currentItem.correctIndex

                val btnColor = when {
                    selectedOption == null -> Color(0xFF2A2A2A)
                    isSelected && isCorrect -> Color(0xFF00E676)
                    isSelected && !isCorrect -> Color(0xFFFF5252)
                    optIdx == currentItem.correctIndex -> Color(0xFF00E676).copy(alpha = 0.5f)
                    else -> Color(0xFF222222)
                }

                Button(
                    onClick = {
                        if (selectedOption == null) {
                            selectedOption = optIdx
                            answeredCount++
                            if (isCorrect) correctCount++
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = btnColor),
                    modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = optText,
                        fontSize = 12.sp,
                        color = if (isSelected && isCorrect) Color.Black else Color.White
                    )
                }
            }
        }

        // Пояснение при ответе
        selectedOption?.let {
            Spacer(modifier = Modifier.height(10.dp))
            val isCorrect = it == currentItem.correctIndex
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (isCorrect) Color(0xFF1B2E20) else Color(0xFF331414)
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = if (isCorrect)
                        "ВЕРНО! Правильная часть речи: ${currentItem.options[currentItem.correctIndex]}"
                    else
                        "ОШИБКА: Требуется '${currentItem.options[currentItem.correctIndex]}'. Не путайте формы!",
                    color = if (isCorrect) Color(0xFF00E676) else Color(0xFFFF5252),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

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
                Text("Предыдущий корень", color = Color.White)
            }

            OutlinedButton(
                onClick = { if (currentIndex < items.size - 1) currentIndex++ },
                enabled = currentIndex < items.size - 1,
                modifier = Modifier.weight(1f).padding(start = 6.dp)
            ) {
                Text("Следующий корень", color = Color.White)
            }
        }
    }
}
