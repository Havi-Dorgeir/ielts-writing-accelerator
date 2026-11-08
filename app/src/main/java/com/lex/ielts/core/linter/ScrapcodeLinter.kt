package com.lex.ielts.core.linter

/**
 * Офлайн-линтер грамматического и лексического скрапкода.
 * Спроектирован по канонам Омниссии для мгновенного анализа текста без доступа к сети.
 */
data class LintIssue(
    val category: String,
    val matchedText: String,
    val explanation: String,
    val suggestedFix: String,
    val startIndex: Int,
    val endIndex: Int
)

class ScrapcodeLinter {

    private val stopWords = mapOf(
        "thresh" to "waste / trash / refuse",
        "emitter" to "source of emissions / culprit",
        "buu" to "buy / purchase",
        "heltly" to "healthy / beneficial",
        "expansive" to "expensive / costly (expansive = обширный)",
        "in twice bigger" to "twice as large as / double that of",
        "strongly bad" to "highly negative / severely detrimental",
        "really terrible" to "severely detrimental / alarming",
        "a lot of" to "many / numerous / a significant number of (академический апгрейд)"
    )

    private val modalToPattern = Regex("""\b(can|cannot|could|should|must|will|would|might|may)\s+to\s+([a-z]+)\b""", RegexOption.IGNORE_CASE)
    private val missingToBePattern = Regex("""\b(it|this|that|which|model|trend|housing|food)\s+(really|very|so|extremely)?\s*(dangerous|important|necessary|expensive|cheap|beneficial|popular)\b""", RegexOption.IGNORE_CASE)
    private val fakePassiveOrContinuous = Regex("""\b(they|we|people|students)\s+are\s+(study|work|produce|consume)\b""", RegexOption.IGNORE_CASE)
    private val thirdPersonMissingS = Regex("""\b(student|teenager|trend|government|consumer|device)\s+([a-z]+[^se])\b""", RegexOption.IGNORE_CASE)

    fun analyzeSentence(sentence: String): List<LintIssue> {
        val issues = mutableListOf<LintIssue>()

        // 1. Проверка Stop-List & опечаток
        for ((wrong, fix) in stopWords) {
            val pattern = Regex("""\b${Regex.escape(wrong)}\b""", RegexOption.IGNORE_CASE)
            pattern.findAll(sentence).forEach { match ->
                issues.add(
                    LintIssue(
                        category = "LR_STOP_LIST",
                        matchedText = match.value,
                        explanation = "Обнаружен скрапкод из черного списка лексики / опечатка.",
                        suggestedFix = fix,
                        startIndex = match.range.first,
                        endIndex = match.range.last + 1
                    )
                )
            }
        }

        // 2. Проверка модальных глаголов с 'to' (cannot to give -> cannot give)
        modalToPattern.findAll(sentence).forEach { match ->
            val modal = match.groupValues[1]
            val verb = match.groupValues[2]
            issues.add(
                LintIssue(
                    category = "GRA_MODAL_TO",
                    matchedText = match.value,
                    explanation = "Модальный глагол '$modal' требует голый инфинитив без частицы 'to'.",
                    suggestedFix = "$modal $verb",
                    startIndex = match.range.first,
                    endIndex = match.range.last + 1
                )
            )
        }

        // 3. Проверка пропуска связки to be (it dangerous -> it is dangerous)
        missingToBePattern.findAll(sentence).forEach { match ->
            val subject = match.groupValues[1]
            val adverb = match.groupValues[2]
            val adj = match.groupValues[3]
            val fix = if (adverb.isNotEmpty()) "$subject is $adverb $adj" else "$subject is $adj"
            issues.add(
                LintIssue(
                    category = "GRA_MISSING_TO_BE",
                    matchedText = match.value,
                    explanation = "В английской матрице обязателен глагол-связка 'is/are' перед предикативом/прилагательным.",
                    suggestedFix = fix,
                    startIndex = match.range.first,
                    endIndex = match.range.last + 1
                )
            )
        }

        // 4. Проверка 'are study' вместо 'study' или 'are studying'
        fakePassiveOrContinuous.findAll(sentence).forEach { match ->
            val subj = match.groupValues[1]
            val baseVerb = match.groupValues[2]
            issues.add(
                LintIssue(
                    category = "GRA_VERB_CONFUSION",
                    matchedText = match.value,
                    explanation = "Смешение связки 'are' и базового глагола. Используйте Present Simple ($baseVerb) или Present Continuous (are ${baseVerb}ing).",
                    suggestedFix = "$subj $baseVerb / $subj are ${baseVerb}ing",
                    startIndex = match.range.first,
                    endIndex = match.range.last + 1
                )
            )
        }

        return issues
    }
}
