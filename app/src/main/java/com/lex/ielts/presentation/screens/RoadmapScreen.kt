package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun RoadmapScreen(
    onNavigateToTab: (String) -> Unit
) {
    var completedDays by remember { mutableStateOf(setOf(1, 2, 3, 4, 5)) } // Дни 1-5 уже закрыты студентом!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text(
            text = "14-ДНЕВНЫЙ ПРОТОКОЛ ПОДГОТОВКИ",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Программа прорыва Band 5.5 -> 6.5-7.0+: Дни 1-14",
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        // Прогресс выполнения плана
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Прогресс плана: ${completedDays.size} из 14 дней",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "${completedDays.size * 100 / 14}%",
                        color = Color(0xFF00E676),
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { completedDays.size / 14f },
                    modifier = Modifier.fillMaxWidth().height(6.dp),
                    color = Color(0xFF00E676),
                    trackColor = Color(0xFF333333)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Список всех 14 дней
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(PrepopulatedData.FOURTEEN_DAY_PLAN) { item ->
                val isCompleted = completedDays.contains(item.dayNumber)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCompleted) Color(0xFF16251A) else Color(0xFF1E1E1E)
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ДЕНЬ ${item.dayNumber} • ${item.phaseName}",
                                color = if (isCompleted) Color(0xFF00E676) else Color(0xFF80D8FF),
                                fontSize = 12.sp
                            )

                            Checkbox(
                                checked = isCompleted,
                                onCheckedChange = { checked ->
                                    completedDays = if (checked) completedDays + item.dayNumber else completedDays - item.dayNumber
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF00E676),
                                    uncheckedColor = Color.Gray
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = item.title,
                            color = Color.White,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.focusArea,
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🎯 Цель: ${item.kpiTarget}",
                                color = Color(0xFF80D8FF),
                                fontSize = 11.sp
                            )

                            Button(
                                onClick = { onNavigateToTab(item.recommendedTab) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A2A2A)),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text("Перейти", color = Color(0xFF00E676), fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
