package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lex.ielts.data.local.EssayTopic
import com.lex.ielts.data.local.PrepopulatedData

@Composable
fun EssayEditorScreen(
    taskType: Int = 2,
    onSaveEssay: (String) -> Unit
) {
    var essayText by remember { mutableStateOf("") }
    var selectedTopic by remember { mutableStateOf(PrepopulatedData.ESSAY_TOPICS.first { it.taskType == taskType }) }
    var showTopicDialog by remember { mutableStateOf(false) }
    var validationErrors by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (selectedTopic.taskType == 1) "TASK 1: ОТЧЕТ" else "TASK 2: АКАДЕМИЧЕСКОЕ ЭССЕ",
                color = Color(0xFF00E676),
                fontSize = 18.sp
            )

            Button(
                onClick = { showTopicDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF222222)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Icon(Icons.Default.List, contentDescription = "Topics", tint = Color(0xFF00E676), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Выбрать тему", color = Color.White, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Карточка выбранной темы
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "${selectedTopic.category}: ${selectedTopic.title}",
                    color = Color(0xFF80D8FF),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = selectedTopic.prompt,
                    color = Color.White,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "💡 Совет: ${selectedTopic.keyTips}",
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = essayText,
            onValueChange = { essayText = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = { Text("Напишите текст по формуле PEEL...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00E676),
                unfocusedBorderColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val errors = mutableListOf<String>()
                if (selectedTopic.taskType == 1) {
                    if (Regex("""\b\d+(\.\d+)?%?\b""").containsMatchIn(essayText) && essayText.contains("Overall", ignoreCase = true)) {
                        errors.add("ВАРП-СКВЕРНА: В блоке Overall обнаружены конкретные цифры! Замените их общими трендами.")
                    }
                } else {
                    val sentenceCount = essayText.split(Regex("""(?<=[.!?])\s+""")).filter { it.isNotBlank() }.size
                    if (sentenceCount < 4) {
                        errors.add("НАРУШЕНИЕ PEEL: Обнаружено всего $sentenceCount предложений. Требуется минимум 4: Point, Explanation, Example, Link.")
                    }
                }
                validationErrors = errors
                if (errors.isEmpty()) {
                    onSaveEssay(essayText)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676))
        ) {
            Text("ИНСПЕКТИРОВАТЬ СТРУКТУРУ PEEL", color = Color.Black)
        }

        if (validationErrors.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            validationErrors.forEach { err ->
                Text(text = err, color = Color(0xFFFF5252), fontSize = 13.sp)
            }
        }
    }

    // Диалог выбора темы из базы ieltswritingchecker.org
    if (showTopicDialog) {
        AlertDialog(
            onDismissRequest = { showTopicDialog = false },
            confirmButton = {
                TextButton(onClick = { showTopicDialog = false }) {
                    Text("Закрыть", color = Color(0xFF00E676))
                }
            },
            title = { Text("БАНК ТЕМ ДЛЯ ЭССЕ (IELTS)", color = Color.White) },
            text = {
                LazyColumn(modifier = Modifier.fillMaxWidth().height(400.dp)) {
                    items(PrepopulatedData.ESSAY_TOPICS) { topic ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    selectedTopic = topic
                                    showTopicDialog = false
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedTopic == topic) Color(0xFF1B2E20) else Color(0xFF252525)
                            )
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Task ${topic.taskType} • ${topic.category}",
                                    color = Color(0xFF80D8FF),
                                    fontSize = 11.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = topic.title,
                                    color = Color.White,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            },
            containerColor = Color(0xFF1E1E1E)
        )
    }
}
