package com.lex.ielts.data.local

import com.lex.ielts.data.local.entity.DrillEntity
import com.lex.ielts.data.local.entity.MistakeEntity

/**
 * Начальный офлайн-банк тренировок, сформированный на основе диагностических свитков Магоса.
 */
object PrepopulatedData {

    val INITIAL_MISTAKES = listOf(
        MistakeEntity(
            id = "m_001",
            targetBand = 6.0,
            category = "GRA_MISSING_TO_BE",
            rawSentence = "because this certain model so expansive",
            correctedSentence = "because this certain model is so expensive",
            ruleExplanation = "Обязателен глагол-связка 'is' перед предикативом/прилагательным."
        ),
        MistakeEntity(
            id = "m_002",
            targetBand = 6.0,
            category = "GRA_GERUND_AFTER_VERB",
            rawSentence = "housing stopped be a biggest expenditures in country",
            correctedSentence = "housing stopped being the biggest expenditure in the country",
            ruleExplanation = "Глагол stop требует герундия (being), превосходная степень требует артикля 'the', согласование числа (housing = singular expenditure)."
        ),
        MistakeEntity(
            id = "m_003",
            targetBand = 6.0,
            category = "GRA_MODAL_TO",
            rawSentence = "they cannot to give enough energy",
            correctedSentence = "they cannot give enough energy",
            ruleExplanation = "После модального глагола cannot используется bare infinitive (без частицы to)."
        )
    )

    val INITIAL_DRILLS = listOf(
        DrillEntity(
            id = "d_001",
            targetBand = 6.0,
            type = "SENTENCE_SURGERY",
            prompt = "Исправьте ошибку: 'housing stopped be a biggest expenditures in country'",
            optionsJson = """["stopped to be the biggest expenditure", "stopped being the biggest expenditure in the country", "stopped be biggest expenditures"]""",
            correctAnswer = "stopped being the biggest expenditure in the country",
            explanation = "После stop требуется being + артикль the перед biggest + единственное число expenditure."
        ),
        DrillEntity(
            id = "d_002",
            targetBand = 6.0,
            type = "WORD_FAMILY",
            prompt = "Выберите правильную часть речи: 'Food became the _______ category in 2010.'",
            optionsJson = """["dominate", "dominant", "dominance"]""",
            correctAnswer = "dominant",
            explanation = "Требуется прилагательное (dominant), а не глагол (dominate) или существительное (dominance)."
        )
    )
}
