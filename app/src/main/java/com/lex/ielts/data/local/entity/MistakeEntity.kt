package com.lex.ielts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность зафиксированного грамматического или лексического скрапкода.
 */
@Entity(tableName = "mistakes")
data class MistakeEntity(
    @PrimaryKey
    val id: String,
    val targetBand: Double,          // 6.0, 6.5, 7.0
    val category: String,            // GRA_MISSING_TO_BE, GRA_MODAL_TO, LR_STOP_LIST, etc.
    val rawSentence: String,
    val correctedSentence: String,
    val ruleExplanation: String,
    val repetitionLevel: Int = 0,    // Интервальное повторение (0 - новая, 5 - усвоена)
    val triggerCount: Int = 1,
    val lastPracticedTimestamp: Long = System.currentTimeMillis()
)
