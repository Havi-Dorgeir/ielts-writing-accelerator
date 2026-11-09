package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lex.ielts.data.local.PrepopulatedData

@Composable
fun DictionaryScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("ALL") }

    val categories = listOf(
        "ALL" to "Все",
        "STOP_LIST" to "Стоп-Лист (Рунглиш)",
        "WORD_FAMILY" to "Части Речи",
        "ACADEMIC_UPGRADE" to "Апгрейд 7.5",
        "CONNECTOR" to "Связки"
    )

    val filteredEntries = remember(searchQuery, selectedCategory) {
        PrepopulatedData.DICTIONARY.filter { entry ->
            val matchesCategory = selectedCategory == "ALL" || entry.category == selectedCategory
            val matchesQuery = searchQuery.isBlank() ||
                    entry.wrongOrSimple.contains(searchQuery, ignoreCase = true) ||
                    entry.academicFix.contains(searchQuery, ignoreCase = true) ||
                    entry.explanation.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text(
            text = "СВЯЩЕННЫЙ ЛЕКСИКОН ОМНИССИИ",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = "Внутренний словарь: искоренение рунглиша и академические формулы 7.0+",
            color = Color.LightGray,
            fontSize = 13.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Строка поиска
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color(0xFF00E676)) },
            placeholder = { Text("Поиск по слову, ошибке или правилу...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00E676),
                unfocusedBorderColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Фильтры категорий
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(categories) { (catKey, catTitle) ->
                val isSelected = selectedCategory == catKey
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = catKey },
                    label = { Text(catTitle, fontSize = 12.sp) },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF00E676),
                        selectedLabelColor = Color.Black,
                        containerColor = Color(0xFF1E1E1E),
                        labelColor = Color.LightGray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Список словарных карточек
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(filteredEntries) { entry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Band ${entry.bandLevel}",
                                color = Color(0xFF80D8FF),
                                fontSize = 12.sp
                            )
                            Text(
                                text = entry.category.replace("_", " "),
                                color = Color.Gray,
                                fontSize = 11.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "⛔ " + entry.wrongOrSimple,
                                color = Color(0xFFFF5252),
                                fontSize = 15.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "✅ " + entry.academicFix,
                                color = Color(0xFF00E676),
                                fontSize = 15.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = entry.explanation,
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
