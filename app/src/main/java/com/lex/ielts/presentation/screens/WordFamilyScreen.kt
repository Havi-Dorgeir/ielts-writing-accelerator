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

data class WordFamilyItem(
    val base: String,
    val verb: String,
    val noun: String,
    val adjective: String,
    val trapPrompt: String,
    val options: List<String>,
    val correctIndex: Int
)

/**
 * Экран «Word Family Matrix» (Матрица частей речи).
 * Искореняет скрапкод смешения глаголов, существительных и прилагательных в IELTS.
 */
@Composable
fun WordFamilyScreen(
    onProgressUpdate: (Int) -> Unit
) {
    val items = remember {
        listOf(
            WordFamilyItem(
                base = "DOMINATE",
                verb = "dominate",
                noun = "dominance",
                adjective = "dominant",
                trapPrompt = "Food became the ________ category in 2010.",
                options = listOf("dominate", "dominant", "dominance"),
                correctIndex = 1
            ),
            WordFamilyItem(
                base = "CONSUME",
                verb = "consume",
                noun = "consumption",
                adjective = "consumable",
                trapPrompt = "The ________ of fossil fuels increased significantly.",
                options = listOf("consume", "consumption", "consumer"),
                correctIndex = 1
            ),
            WordFamilyItem(
                base = "DEPEND",
                verb = "depend",
                noun = "dependence / dependency",
                adjective = "dependent",
                trapPrompt = "Renewable energy is highly ________ on weather conditions.",
                options = listOf("depend", "dependent", "dependence"),
                correctIndex = 1
            )
        )
    }

    var selectedAnswers by remember { mutableStateOf(mapOf<Int, Int>()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "МАТРИЦА ЧАСТЕЙ РЕЧИ (WORD FAMILIES)",
                color = Color(0xFF00E676),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "Искоренение подмены частей речи: Verb vs Noun vs Adjective.",
                color = Color.LightGray,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(items) { item ->
            val index = items.indexOf(item)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Корень: ${item.base}", color = Color(0xFF80D8FF), fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "V: ${item.verb} | N: ${item.noun} | Adj: ${item.adjective}",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = item.trapPrompt, color = Color.White, fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        item.options.forEachIndexed { optIdx, optText ->
                            val isSelected = selectedAnswers[index] == optIdx
                            val isCorrect = optIdx == item.correctIndex
                            val btnColor = when {
                                !isSelected -> Color(0xFF2A2A2A)
                                isCorrect -> Color(0xFF00E676)
                                else -> Color(0xFFFF5252)
                            }

                            Button(
                                onClick = {
                                    selectedAnswers = selectedAnswers + (index to optIdx)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = btnColor),
                                modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                            ) {
                                Text(optText, fontSize = 12.sp, color = if (isSelected && isCorrect) Color.Black else Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}
