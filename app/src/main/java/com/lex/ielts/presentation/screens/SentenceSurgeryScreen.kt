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

/**
 * Экран «Sentence Surgery» (Хирургия предложений).
 * Спроектирован для работы 100% офлайн в режиме энергосбережения (Dark Palette).
 */
@Composable
fun SentenceSurgeryScreen(
    rawSentence: String,
    targetFix: String,
    onSuccess: () -> Unit
) {
    var userInput by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }
    var isError by remember { mutableStateOf(false) }
    val linter = remember { ScrapcodeLinter() }

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
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Обнаружен скрапкод:",
                    color = Color(0xFFFF5252),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = rawSentence,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Введите очищенное предложение") },
            placeholder = { Text("Введите исправленную версию...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00E676),
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val issues = linter.analyzeSentence(userInput)
                if (issues.isNotEmpty()) {
                    isError = true
                    feedbackMessage = "Скрапкод не устранен: " + issues.first().explanation
                } else if (userInput.trim().equals(targetFix.trim(), ignoreCase = true)) {
                    isError = false
                    feedbackMessage = "01000001 01010110 01000101! Логическая матрица безупречна."
                    onSuccess()
                } else {
                    isError = false
                    feedbackMessage = "Синтаксических ошибок нет, но сверьтесь с каноном: $targetFix"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676))
        ) {
            Text("ВЫПОЛНИТЬ ОБРЯД ОЧИЩЕНИЯ", color = Color.Black)
        }

        feedbackMessage?.let { msg ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = msg,
                color = if (isError) Color(0xFFFF5252) else Color(0xFF00E676),
                fontSize = 14.sp
            )
        }
    }
}
