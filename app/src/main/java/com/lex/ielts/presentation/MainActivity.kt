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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.lex.ielts.presentation.screens.DictionaryScreen
import com.lex.ielts.presentation.screens.EssayEditorScreen
import com.lex.ielts.presentation.screens.RoadmapScreen
import com.lex.ielts.presentation.screens.SentenceSurgeryScreen
import com.lex.ielts.presentation.screens.WordFamilyScreen

enum class Screen(val title: String, val icon: ImageVector) {
    ROADMAP("14 Дней", Icons.Default.DateRange),
    SURGERY("Хирургия", Icons.Default.Build),
    WORD_FAMILY("Словоформы", Icons.Default.List),
    PEEL_EDITOR("PEEL Темы", Icons.Default.Edit),
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

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1E1E1E),
                contentColor = Color.White
            ) {
                Screen.values().forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title, fontSize = 10.sp) },
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF00E676),
                            selectedTextColor = Color(0xFF00E676),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color(0xFF2A2A2A)
                        )
                    )
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
            when (currentScreen) {
                Screen.ROADMAP -> {
                    RoadmapScreen(
                        onNavigateToTab = { tabKey ->
                            currentScreen = when (tabKey) {
                                "SURGERY" -> Screen.SURGERY
                                "WORD_FAMILY" -> Screen.WORD_FAMILY
                                "PEEL_EDITOR" -> Screen.PEEL_EDITOR
                                "DICTIONARY" -> Screen.DICTIONARY
                                else -> Screen.SURGERY
                            }
                        }
                    )
                }
                Screen.SURGERY -> {
                    SentenceSurgeryScreen()
                }
                Screen.WORD_FAMILY -> {
                    WordFamilyScreen()
                }
                Screen.PEEL_EDITOR -> {
                    EssayEditorScreen(taskType = 2, onSaveEssay = {})
                }
                Screen.DICTIONARY -> {
                    DictionaryScreen()
                }
            }
        }
    }
}
