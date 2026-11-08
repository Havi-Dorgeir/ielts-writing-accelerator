package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Экран «Essay Editor & PEEL Validator» (Инкубатор эссе).
 * Автоматически валидирует структуру параграфов Task 2 (Point -> Explanation -> Example -> Link)
 * и проверяет Task 1 Overview на отсутствие сырых цифр.
 */
@Composable
fun EssayEditorScreen(
    taskType: Int = 2, // 1 = Task 1 (Report), 2 = Task 2 (Essay)
    onSaveEssay: (String) -> Unit
) {
    var essayText by remember { mutableStateOf("") }
    var validationErrors by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text(
            text = if (taskType == 1) "TASK 1: АНАЛИТИЧЕСКИЙ ОТЧЕТ" else "TASK 2: АКАДЕМИЧЕСКОЕ ЭССЕ",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = if (taskType == 1)
                "Канон Омниссии: В Overview категорически запрещены сырые цифры и проценты!"
            else
                "Канон Омниссии: Каждый Body Paragraph обязан содержать 4 предложения PEEL.",
            color = Color.LightGray,
            fontSize = 13.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = essayText,
            onValueChange = { essayText = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = { Text("Введите текст параграфа или эссе...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00E676),
                unfocusedBorderColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val errors = mutableListOf<String>()
                if (taskType == 1) {
                    // Валидация Overview: поиск цифр и процентов
                    if (Regex("""\b\d+(\.\d+)?%?\b""").containsMatchIn(essayText) && essayText.contains("Overall", ignoreCase = true)) {
                        errors.add("ВАРП-СКВЕРНА: В блоке Overall обнаружены конкретные цифры! Замените их общими трендами.")
                    }
                } else {
                    // Валидация PEEL: подсчет предложений
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
}
