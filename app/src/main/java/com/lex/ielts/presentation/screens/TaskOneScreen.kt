package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
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

@Composable
fun TaskOneScreen() {
    var selectedCategory by remember { mutableStateOf("ALL") }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var answeredCount by remember { mutableStateOf(0) }

    val categories = listOf(
        "ALL" to "Все типы",
        "PREPOSITIONS" to "Предлоги (at/by/to)",
        "OVERVIEW" to "Обзор без цифр",
        "TRENDS" to "Глаголы трендов",
        "PROCESS_PASSIVE" to "Пассив (Процессы)",
        "COMPARISON" to "Сравнения"
    )

    val drills = remember(selectedCategory) {
        if (selectedCategory == "ALL") PrepopulatedData.TASK1_DRILLS
        else PrepopulatedData.TASK1_DRILLS.filter { it.type == selectedCategory }
    }

    val currentDrill = drills.getOrNull(currentIndex) ?: drills.first()

    LaunchedEffect(selectedCategory) {
        currentIndex = 0
        selectedOption = null
    }

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
            text = "ЛАБОРАТОРИЯ TASK 1: АНАЛИТИКА",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Графики, таблицы, карты и процессы: предлоги, пассив и чистый Overview",
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        // Селектор категорий Task 1
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(categories) { (catKey, catTitle) ->
                val isSelected = selectedCategory == catKey
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = catKey },
                    label = { Text(catTitle, fontSize = 11.sp) },
                    modifier = Modifier.padding(end = 6.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF00E676),
                        selectedLabelColor = Color.Black,
                        containerColor = Color(0xFF1E1E1E),
                        labelColor = Color.LightGray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Прогресс и тип диаграммы
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Задание ${currentIndex + 1} из ${drills.size}",
                color = Color(0xFF80D8FF),
                fontSize = 13.sp
            )
            AssistChip(
                onClick = {},
                label = { Text(currentDrill.chartType, color = Color.White, fontSize = 11.sp) },
                colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFF222222))
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Карточка вопроса (вертикальный скролл)
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = currentDrill.title,
                            color = Color(0xFF00E676),
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = currentDrill.prompt,
                            color = Color.White,
                            fontSize = 15.sp,
                            lineHeight = 22.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Варианты ответов: аккуратный вертикальный список (без каши и наползания!)
                currentDrill.options.forEachIndexed { optIdx, optText ->
                    val isSelected = selectedOption == optIdx
                    val isCorrect = optIdx == currentDrill.correctIndex

                    val cardBg = when {
                        selectedOption == null -> Color(0xFF1C1C1C)
                        isSelected && isCorrect -> Color(0xFF163820)
                        isSelected && !isCorrect -> Color(0xFF381616)
                        optIdx == currentDrill.correctIndex -> Color(0xFF163820).copy(alpha = 0.6f)
                        else -> Color(0xFF181818)
                    }

                    val borderColor = when {
                        selectedOption == null -> Color(0xFF2A2A2A)
                        isSelected && isCorrect -> Color(0xFF00E676)
                        isSelected && !isCorrect -> Color(0xFFFF5252)
                        optIdx == currentDrill.correctIndex -> Color(0xFF00E676)
                        else -> Color.Transparent
                    }

                    Card(
                        onClick = {
                            if (selectedOption == null) {
                                selectedOption = optIdx
                                answeredCount++
                                if (isCorrect) score++
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${('A'.code + optIdx).toChar()}.",
                                color = if (isSelected) Color(0xFF00E676) else Color.Gray,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = optText,
                                color = Color.White,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                // Карточка объяснения
                selectedOption?.let {
                    Spacer(modifier = Modifier.height(10.dp))
                    val isCorrect = it == currentDrill.correctIndex
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCorrect) Color(0xFF16281C) else Color(0xFF2E1717)
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = if (isCorrect) "ВЕРНО! Правильный канон:" else "ОШИБКА АНАЛИТИКИ:",
                                color = if (isCorrect) Color(0xFF00E676) else Color(0xFFFF5252),
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentDrill.explanation,
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Навигация
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0,
                modifier = Modifier.weight(1f).padding(end = 6.dp)
            ) {
                Text("Предыдущее", color = Color.White, fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = { if (currentIndex < drills.size - 1) currentIndex++ },
                enabled = currentIndex < drills.size - 1,
                modifier = Modifier.weight(1f).padding(start = 6.dp)
            ) {
                Text("Следующее", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}
