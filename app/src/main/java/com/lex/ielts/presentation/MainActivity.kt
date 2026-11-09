package com.lex.ielts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lex.ielts.presentation.screens.DictionaryScreen
import com.lex.ielts.presentation.screens.EssayEditorScreen
import com.lex.ielts.presentation.screens.RoadmapScreen
import com.lex.ielts.presentation.screens.SentenceSurgeryScreen
import com.lex.ielts.presentation.screens.TaskOneScreen
import com.lex.ielts.presentation.screens.WordFamilyScreen

enum class Screen(val shortTitle: String, val icon: ImageVector) {
    ROADMAP("План", Icons.Default.DateRange),
    TASK1("Task 1", Icons.Default.Edit),
    SURGERY("Хирургия", Icons.Default.Build),
    WORD_FAMILY("Слова", Icons.Default.List),
    DICTIONARY("Словарь", Icons.Default.Search)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IeltsAppRoot()
        }
    }
}

@Composable
fun IeltsAppRoot() {
    var currentScreen by remember { mutableStateOf(Screen.ROADMAP) }
    var isEssayEditorOpen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF181818))
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "LEX MECHANICUS",
                    color = Color(0xFF00E676),
                    fontSize = 15.sp
                )

                Button(
                    onClick = { isEssayEditorOpen = !isEssayEditorOpen },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isEssayEditorOpen) Color(0xFF00E676) else Color(0xFF282828)
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (isEssayEditorOpen) "Вернуться" else "✍️ Инкубатор PEEL",
                        color = if (isEssayEditorOpen) Color.Black else Color.White,
                        fontSize = 11.sp
                    )
                }
            }
        },
        bottomBar = {
            if (!isEssayEditorOpen) {
                NavigationBar(
                    containerColor = Color(0xFF181818),
                    contentColor = Color.White
                ) {
                    Screen.values().forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = screen.shortTitle) },
                            label = { Text(screen.shortTitle, fontSize = 11.sp) },
                            selected = currentScreen == screen,
                            onClick = { currentScreen = screen },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF00E676),
                                selectedTextColor = Color(0xFF00E676),
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color(0xFF242424)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF121212))
        ) {
            if (isEssayEditorOpen) {
                EssayEditorScreen(taskType = 2, onSaveEssay = { isEssayEditorOpen = false })
            } else {
                when (currentScreen) {
                    Screen.ROADMAP -> {
                        RoadmapScreen(
                            onNavigateToTab = { tabKey ->
                                when (tabKey) {
                                    "SURGERY" -> currentScreen = Screen.SURGERY
                                    "WORD_FAMILY" -> currentScreen = Screen.WORD_FAMILY
                                    "TASK1_LAB" -> currentScreen = Screen.TASK1
                                    "PEEL_EDITOR" -> isEssayEditorOpen = true
                                    "DICTIONARY" -> currentScreen = Screen.DICTIONARY
                                    else -> currentScreen = Screen.SURGERY
                                }
                            }
                        )
                    }
                    Screen.TASK1 -> {
                        TaskOneScreen()
                    }
                    Screen.SURGERY -> {
                        SentenceSurgeryScreen()
                    }
                    Screen.WORD_FAMILY -> {
                        WordFamilyScreen()
                    }
                    Screen.DICTIONARY -> {
                        DictionaryScreen()
                    }
                }
            }
        }
    }
}
