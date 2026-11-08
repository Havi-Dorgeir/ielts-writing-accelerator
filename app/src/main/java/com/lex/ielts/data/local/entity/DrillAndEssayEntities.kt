package com.lex.ielts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность интерактивного тренировочного задания (Drill).
 */
@Entity(tableName = "drills")
data class DrillEntity(
    @PrimaryKey
    val id: String,
    val targetBand: Double,          // 6.0, 6.5, 7.0
    val type: String,                // SENTENCE_SURGERY, WORD_FAMILY, STOP_LIST, PEEL_BUILDER
    val prompt: String,
    val optionsJson: String,         // Варианты выбора в формате JSON (для офлайн парсинга)
    val correctAnswer: String,
    val explanation: String
)

/**
 * Сущность сохраненного эссе (Task 1 / Task 2).
 */
@Entity(tableName = "essays")
data class EssayEntity(
    @PrimaryKey
    val id: String,
    val taskType: Int,               // 1 = Report, 2 = Essay
    val topic: String,
    val content: String,
    val timeSpentSeconds: Int,
    val overviewHasNoData: Boolean,
    val peelStructureValid: Boolean,
    val aiExaminerFeedback: String? = null,
    val estimatedBand: Double = 5.5,
    val createdAt: Long = System.currentTimeMillis()
)
