import SwiftUI

public struct EssayEditorView: View {
    @State private var selectedTopicIndex: Int = 0
    @State private var pointText: String = ""
    @State private var explainText: String = ""
    @State private var exampleText: String = ""
    @State private var linkText: String = ""
    
    @State private var linterResult: LintReport? = nil
    
    private let topics = PrepopulatedData.essayTopics
    
    public init() {}
    
    private var fullAssembledText: String {
        [pointText, explainText, exampleText, linkText]
            .map { $0.trimmingCharacters(in: .whitespacesAndNewlines) }
            .filter { !$0.isEmpty }
            .joined(separator: " ")
    }
    
    private var wordCount: Int {
        let words = fullAssembledText.components(separatedBy: .whitespacesAndNewlines).filter { !$0.isEmpty }
        return words.count
    }
    
    public var body: some View {
        NavigationView {
            ZStack {
                Color(red: 0.07, green: 0.08, blue: 0.12)
                    .ignoresSafeArea()
                
                ScrollView {
                    VStack(alignment: .leading, spacing: 16) {
                        // Topic Selector Picker
                        VStack(alignment: .leading, spacing: 6) {
                            Text("ТЕМА ИЗ ИНКУБАТОРА (ieltswritingchecker.org):")
                                .font(.caption2)
                                .bold()
                                .foregroundColor(.cyan)
                            
                            Picker("Тема", selection: $selectedTopicIndex) {
                                ForEach(0..<topics.count, id: \.self) { idx in
                                    Text("Task \(topics[idx].taskType): \(topics[idx].title)").tag(idx)
                                }
                            }
                            .pickerStyle(.menu)
                            .padding(8)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .background(Color(red: 0.14, green: 0.16, blue: 0.22))
                            .cornerRadius(10)
                        }
                        
                        let currentTopic = topics[selectedTopicIndex]
                        
                        // Topic Card
                        VStack(alignment: .leading, spacing: 8) {
                            HStack {
                                Text("Task \(currentTopic.taskType)")
                                    .font(.caption)
                                    .bold()
                                    .padding(.horizontal, 8)
                                    .padding(.vertical, 3)
                                    .background(Color.yellow.opacity(0.2))
                                    .foregroundColor(.yellow)
                                    .cornerRadius(6)
                                Text(currentTopic.category)
                                    .font(.caption)
                                    .foregroundColor(.gray)
                            }
                            
                            Text(currentTopic.prompt)
                                .font(.subheadline)
                                .bold()
                                .foregroundColor(.white)
                            
                            HStack(alignment: .top, spacing: 6) {
                                Image(systemName: "sparkles")
                                    .foregroundColor(.cyan)
                                    .font(.caption)
                                Text("Совет: \(currentTopic.keyTips)")
                                    .font(.caption2)
                                    .foregroundColor(.cyan.opacity(0.9))
                            }
                        }
                        .padding()
                        .background(Color(red: 0.12, green: 0.14, blue: 0.20))
                        .cornerRadius(12)
                        
                        // PEEL Constructor
                        Text("КАНОН PEEL: 4 ПРЕДЛОЖЕНИЯ АБЗАЦА")
                            .font(.caption2)
                            .bold()
                            .foregroundColor(.gray)
                        
                        PEELInputField(badge: "P", badgeColor: .blue, title: "Point (Главная мысль абзаца):", placeholder: "e.g. Firstly, widespread automation significantly displaces blue-collar workers.", text: $pointText)
                        PEELInputField(badge: "E", badgeColor: .green, title: "Explanation (Логическое объяснение):", placeholder: "e.g. This occurs because robotic systems operate tirelessly and reduce operational overhead.", text: $explainText)
                        PEELInputField(badge: "E", badgeColor: .orange, title: "Example (Конкретный факт/исследование):", placeholder: "e.g. For instance, recent studies in Germany reveal that automobile assembly lines have cut human staff by 30%.", text: $exampleText)
                        PEELInputField(badge: "L", badgeColor: .purple, title: "Link (Вывод и связка с тезисом):", placeholder: "e.g. Consequently, uncontrolled tech adoption threatens social stability unless retraining is funded.", text: $linkText)
                        
                        // Action Bar: Word Count & Check
                        HStack {
                            Text("Слов в абзаце: \(wordCount)")
                                .font(.subheadline)
                                .bold()
                                .foregroundColor(wordCount >= 70 ? .green : .orange)
                            
                            Spacer()
                            
                            Button(action: runLinterAudit) {
                                HStack {
                                    Image(systemName: "shield.checkerboard")
                                    Text("Аудит линтером")
                                }
                                .font(.subheadline)
                                .bold()
                                .foregroundColor(.black)
                                .padding(.horizontal, 16)
                                .padding(.vertical, 10)
                                .background(Color.cyan)
                                .cornerRadius(10)
                            }
                        }
                        
                        // Linter Diagnostics Report
                        if let report = linterResult {
                            VStack(alignment: .leading, spacing: 10) {
                                HStack {
                                    Image(systemName: report.issues.isEmpty ? "checkmark.circle.fill" : "exclamationmark.octagon.fill")
                                        .foregroundColor(report.issues.isEmpty ? .green : .red)
                                    Text(report.issues.isEmpty ? "Линтер Омниссии: Скрапкод не обнаружен!" : "Линтер Омниссии: Обнаружен скрапкод!")
                                        .font(.subheadline)
                                        .bold()
                                        .foregroundColor(report.issues.isEmpty ? .green : .red)
                                }
                                
                                if report.issues.isEmpty {
                                    Text("Все структуры чисты. Синтаксис благословлен.")
                                        .font(.caption)
                                        .foregroundColor(.green.opacity(0.8))
                                } else {
                                    ForEach(report.issues) { issue in
                                        HStack(alignment: .top, spacing: 8) {
                                            Text("•")
                                                .foregroundColor(.red)
                                            VStack(alignment: .leading, spacing: 2) {
                                                Text(issue.category)
                                                    .font(.caption2)
                                                    .bold()
                                                    .foregroundColor(.orange)
                                                Text(issue.message)
                                                    .font(.caption)
                                                    .foregroundColor(.white)
                                            }
                                        }
                                    }
                                }
                            }
                            .padding()
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .background(Color(red: 0.14, green: 0.16, blue: 0.22))
                            .cornerRadius(12)
                            .overlay(
                                RoundedRectangle(cornerRadius: 12)
                                    .stroke(report.issues.isEmpty ? Color.green.opacity(0.3) : Color.red.opacity(0.3), lineWidth: 1)
                            )
                        }
                    }
                    .padding()
                }
            }
            .navigationTitle("Инкубатор PEEL")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
    
    private func runLinterAudit() {
        let isTaskOne = topics[selectedTopicIndex].taskType == 1
        linterResult = ScrapcodeLinter.lint(text: fullAssembledText, isTaskOneOverview: isTaskOne)
    }
}

private struct PEELInputField: View {
    let badge: String
    let badgeColor: Color
    let title: String
    let placeholder: String
    @Binding var text: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            HStack(spacing: 8) {
                Text(badge)
                    .font(.caption)
                    .bold()
                    .frame(width: 24, height: 24)
                    .background(badgeColor.opacity(0.25))
                    .foregroundColor(badgeColor)
                    .cornerRadius(6)
                Text(title)
                    .font(.caption)
                    .foregroundColor(.white.opacity(0.8))
            }
            
            TextEditor(text: $text)
                .frame(height: 70)
                .padding(6)
                .background(Color(red: 0.12, green: 0.14, blue: 0.20))
                .foregroundColor(.white)
                .cornerRadius(8)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color.white.opacity(0.12), lineWidth: 1)
                )
        }
    }
}
