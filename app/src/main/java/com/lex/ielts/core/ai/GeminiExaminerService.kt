package com.lex.ielts.core.ai

/**
 * Опциональный модуль AI-аугментации через Google Gemini API.
 * Активируется только при наличии сетевого подключения и установленного API-ключа.
 * В случае отсутствия электроэнергии или сети приложение работает на 100% локальном линтере.
 */
data class ExaminerReport(
    val estimatedOverallBand: Double,
    val taskResponseBand: Double,
    val coherenceCohesionBand: Double,
    val lexicalResourceBand: Double,
    val grammaticalRangeAccuracyBand: Double,
    val detectedScrapcode: List<String>,
    val strategicRecommendations: List<String>
)

class GeminiExaminerService(
    private val apiKeyProvider: () -> String?
) {
    fun isAvailable(): Boolean = !apiKeyProvider().isNullOrBlank()

    /**
     * Формирует канонический системный промпт для Gemini в роли старшего экзаменатора IELTS.
     */
    fun buildExaminerPrompt(taskType: Int, topic: String, essayText: String): String {
        return """
            You are a strict, veteran IELTS Academic Writing Senior Examiner.
            Evaluate the following Task $taskType essay strictly using the official May 2023 IELTS Writing Band Descriptors.
            
            TOPIC:
            $topic
            
            ESSAY CONTENT:
            $essayText
            
            Analyze the following 4 criteria rigorously:
            1. Task Achievement / Task Response (TR/TA)
            2. Coherence and Cohesion (CC) - Check PEEL and paragraph flow
            3. Lexical Resource (LR) - Identify informal phrases, spelling, and word family errors
            4. Grammatical Range and Accuracy (GRA) - Count error-free sentences, check missing 'to be', modal verbs, and subject-verb agreement.
            
            Return output in strict JSON format matching ExaminerReport schema.
        """.trimIndent()
    }
}
