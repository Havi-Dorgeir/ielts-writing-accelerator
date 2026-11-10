import Foundation

public struct LintIssue {
    public let category: String
    public let matchedText: String
    public let explanation: String
    public let suggestedFix: String
}

public class ScrapcodeLinter {
    public init() {}

    private let stopWords: [String: String] = [
        "thresh": "waste / trash / refuse",
        "emitter": "source of emissions / culprit",
        "buu": "buy / purchase",
        "heltly": "healthy / beneficial",
        "expansive": "expensive / costly",
        "in twice bigger": "twice as large as / double that of",
        "strongly bad": "highly negative / severely detrimental",
        "really terrible": "severely detrimental / alarming",
        "in result": "As a result, / Consequently,"
    ]

    public func analyzeSentence(_ sentence: String) -> [LintIssue] {
        var issues: [LintIssue] = []

        // 1. Stop words
        for (wrong, fix) in stopWords {
            if sentence.range(of: "\\b\(wrong)\\b", options: [.regularExpression, .caseInsensitive]) != nil {
                issues.append(LintIssue(
                    category: "LR_STOP_LIST",
                    matchedText: wrong,
                    explanation: "Обнаружен скрапкод из черного списка лексики.",
                    suggestedFix: fix
                ))
            }
        }

        // 2. Modal + to
        let modalPattern = "\\b(can|cannot|could|should|must|will|would)\\s+to\\s+([a-z]+)\\b"
        if let regex = try? NSRegularExpression(pattern: modalPattern, options: .caseInsensitive) {
            let range = NSRange(sentence.startIndex..<sentence.endIndex, in: sentence)
            let matches = regex.matches(in: sentence, options: [], range: range)
            for match in matches {
                if let r = Range(match.range, in: sentence) {
                    issues.append(LintIssue(
                        category: "GRA_MODAL_TO",
                        matchedText: String(sentence[r]),
                        explanation: "Модальный глагол требует голый инфинитив без частицы to.",
                        suggestedFix: "Удалите 'to'"
                    ))
                }
            }
        }

        // 3. Missing to be
        let toBePattern = "\\b(it|this|that|model|trend)\\s+(really|very|so)?\\s*(dangerous|important|necessary|expensive|popular)\\b"
        if let regex = try? NSRegularExpression(pattern: toBePattern, options: .caseInsensitive) {
            let range = NSRange(sentence.startIndex..<sentence.endIndex, in: sentence)
            let matches = regex.matches(in: sentence, options: [], range: range)
            for match in matches {
                if let r = Range(match.range, in: sentence) {
                    issues.append(LintIssue(
                        category: "GRA_MISSING_TO_BE",
                        matchedText: String(sentence[r]),
                        explanation: "В английской матрице обязателен глагол-связка is/are перед предикативом.",
                        suggestedFix: "Добавьте 'is' или 'are'"
                    ))
                }
            }
        }

        return issues
    }
}
