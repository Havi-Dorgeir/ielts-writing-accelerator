package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lex.ielts.core.linter.ScrapcodeLinter
import com.lex.ielts.data.local.PrepopulatedData

@Composable
fun SentenceSurgeryScreen() {
    var selectedBand by remember { mutableStateOf(6.0) }
    var currentIndex by remember { mutableStateOf(0) }
    var userInput by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }
    var isError by remember { mutableStateOf(false) }
    var showHint by remember { mutableStateOf(false) }

    val linter = remember { ScrapcodeLinter() }

    // Фильтруем задания по выбранному уровню
    val drills = remember(selectedBand) {
        PrepopulatedData.SURGERY_DRILLS.filter { it.targetBand == selectedBand }
    }

    val currentDrill = drills.getOrNull(currentIndex) ?: drills.first()

    // Сброс при смене уровня
    LaunchedEffect(selectedBand) {
        currentIndex = 0
        userInput = ""
        feedbackMessage = null
        showHint = false
    }

    // Сброс при смене задания
    LaunchedEffect(currentIndex) {
        userInput = ""
        feedbackMessage = null
        showHint = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "СЕКЦИЯ ХИРУРГИИ ПРЕДЛОЖЕНИЙ",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        // Селектор уровней (Band 6.0 -> 6.5 -> 7.0)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf(6.0 to "Band 6.0", 6.5 to "Band 6.5", 7.0 to "Band 7.0+").forEach { (band, title) ->
                val isSelected = selectedBand == band
                Button(
                    onClick = { selectedBand = band },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color(0xFF00E676) else Color(0xFF222222),
                        contentColor = if (isSelected) Color.Black else Color.LightGray
                    ),
                    modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(title, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Счетчик и прогресс
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Задание ${currentIndex + 1} из ${drills.size}",
                color = Color(0xFF80D8FF),
                fontSize = 14.sp
            )
            Text(
                text = currentDrill.category.replace("GRA_", "").replace("LR_", ""),
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Карточка с дефектным предложением
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Обнаружен скрапкод:",
                    color = Color(0xFFFF5252),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = currentDrill.rawSentence,
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Поле ввода исправленной версии
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Введите очищенное предложение") },
            placeholder = { Text("Напечатайте версию без ошибок...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00E676),
                unfocusedBorderColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Кнопка подсказки
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            TextButton(onClick = { showHint = !showHint }) {
                Text(
                    text = if (showHint) "Скрыть подсказку" else "💡 Показать наводку Наставника",
                    color = Color(0xFF80D8FF),
                    fontSize = 13.sp
                )
            }
        }

        if (showHint) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF102830)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = currentDrill.ruleExplanation,
                    color = Color(0xFF80D8FF),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Кнопка проверки
        Button(
            onClick = {
                val issues = linter.analyzeSentence(userInput)
                if (issues.isNotEmpty()) {
                    isError = true
                    feedbackMessage = "Скрапкод не устранен: " + issues.first().explanation
                } else if (userInput.trim().equals(currentDrill.correctedSentence.trim(), ignoreCase = true)) {
                    isError = false
                    feedbackMessage = "01000001 01010110 01000101! Логическая матрица безупречна."
                } else {
                    isError = false
                    feedbackMessage = "Синтаксических ошибок нет! Эталонный канон: ${currentDrill.correctedSentence}"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676))
        ) {
            Text("ВЫПОЛНИТЬ ОБРЯД ОЧИЩЕНИЯ", color = Color.Black)
        }

        feedbackMessage?.let { msg ->
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = msg,
                color = if (isError) Color(0xFFFF5252) else Color(0xFF00E676),
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Навигация между заданиями (Предыдущее / Следующее)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0,
                modifier = Modifier.weight(1f).padding(end = 6.dp)
            ) {
                Text("Предыдущее", color = Color.White)
            }

            OutlinedButton(
                onClick = { if (currentIndex < drills.size - 1) currentIndex++ },
                enabled = currentIndex < drills.size - 1,
                modifier = Modifier.weight(1f).padding(start = 6.dp)
            ) {
                Text("Следующее", color = Color.White)
            }
        }
    }
}
