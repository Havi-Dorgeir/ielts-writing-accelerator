import SwiftUI

public struct SurgeryView: View {
    @State private var filterBand: Double? = nil
    @State private var currentIndex: Int = 0
    @State private var userFixText: String = ""
    @State private var showHint: Bool = false
    @State private var isAnswered: Bool = false
    @State private var feedbackText: String = ""
    @State private var isSuccess: Bool = false
    
    private var filteredDrills: [SurgeryDrillItem] {
        if let band = filterBand {
            return PrepopulatedData.surgeryDrills.filter { $0.targetBand == band }
        }
        return PrepopulatedData.surgeryDrills
    }
    
    public init() {}
    
    public var body: some View {
        NavigationView {
            ZStack {
                Color(red: 0.07, green: 0.08, blue: 0.12)
                    .ignoresSafeArea()
                
                VStack(spacing: 16) {
                    // Filter Pills
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 10) {
                            FilterPill(title: "Все (\(PrepopulatedData.surgeryDrills.count))", isSelected: filterBand == nil) {
                                filterBand = nil
                                resetCard()
                            }
                            FilterPill(title: "Band 6.0", isSelected: filterBand == 6.0) {
                                filterBand = 6.0
                                resetCard()
                            }
                            FilterPill(title: "Band 6.5", isSelected: filterBand == 6.5) {
                                filterBand = 6.5
                                resetCard()
                            }
                            FilterPill(title: "Band 7.0+", isSelected: filterBand == 7.0) {
                                filterBand = 7.0
                                resetCard()
                            }
                        }
                        .padding(.horizontal)
                    }
                    
                    if filteredDrills.isEmpty {
                        Spacer()
                        Text("Нет заданий в данной категории")
                            .foregroundColor(.gray)
                        Spacer()
                    } else {
                        let currentDrill = filteredDrills[min(currentIndex, filteredDrills.count - 1)]
                        
                        ScrollView {
                            VStack(alignment: .leading, spacing: 16) {
                                // Header Badge
                                HStack {
                                    Text("Задание \(currentIndex + 1) из \(filteredDrills.count)")
                                        .font(.caption)
                                        .foregroundColor(Color(red: 0.9, green: 0.6, blue: 0.2))
                                        .bold()
                                    Spacer()
                                    Text("Цель: Band \(String(format: "%.1f", currentDrill.targetBand))")
                                        .font(.caption)
                                        .padding(.horizontal, 10)
                                        .padding(.vertical, 4)
                                        .background(Color.yellow.opacity(0.15))
                                        .foregroundColor(.yellow)
                                        .cornerRadius(8)
                                }
                                
                                // Category Pill
                                Text(currentDrill.category)
                                    .font(.caption2)
                                    .bold()
                                    .padding(.horizontal, 8)
                                    .padding(.vertical, 4)
                                    .background(Color.blue.opacity(0.2))
                                    .foregroundColor(.cyan)
                                    .cornerRadius(6)
                                
                                // Patient Sentence
                                VStack(alignment: .leading, spacing: 6) {
                                    Text("⚠️ Пациент с вирусом:")
                                        .font(.caption)
                                        .foregroundColor(.red.opacity(0.8))
                                        .bold()
                                    Text(currentDrill.rawSentence)
                                        .font(.body)
                                        .foregroundColor(.white)
                                        .padding()
                                        .frame(maxWidth: .infinity, alignment: .leading)
                                        .background(Color.red.opacity(0.1))
                                        .overlay(
                                            RoundedRectangle(cornerRadius: 10)
                                                .stroke(Color.red.opacity(0.3), lineWidth: 1)
                                        )
                                        .cornerRadius(10)
                                }
                                
                                // User Surgery Input
                                VStack(alignment: .leading, spacing: 6) {
                                    Text("🔧 Ваша операция (исправьте предложение):")
                                        .font(.caption)
                                        .foregroundColor(.gray)
                                    
                                    TextEditor(text: $userFixText)
                                        .frame(height: 80)
                                        .padding(8)
                                        .background(Color(red: 0.12, green: 0.14, blue: 0.20))
                                        .foregroundColor(.white)
                                        .cornerRadius(10)
                                        .overlay(
                                            RoundedRectangle(cornerRadius: 10)
                                                .stroke(Color.cyan.opacity(0.4), lineWidth: 1)
                                        )
                                }
                                
                                // Buttons
                                HStack(spacing: 12) {
                                    Button(action: {
                                        checkAnswer(target: currentDrill.correctedSentence)
                                    }) {
                                        HStack {
                                            Image(systemName: "checkmark.seal.fill")
                                            Text("Проверить скальпелем")
                                        }
                                        .font(.subheadline)
                                        .bold()
                                        .foregroundColor(.black)
                                        .frame(maxWidth: .infinity)
                                        .padding(.vertical, 12)
                                        .background(Color.cyan)
                                        .cornerRadius(10)
                                    }
                                    
                                    Button(action: {
                                        showHint.toggle()
                                    }) {
                                        Image(systemName: "lightbulb.fill")
                                            .foregroundColor(.yellow)
                                            .padding(12)
                                            .background(Color.yellow.opacity(0.2))
                                            .cornerRadius(10)
                                    }
                                }
                                
                                // Hint or Rule Box
                                if showHint || isAnswered {
                                    VStack(alignment: .leading, spacing: 8) {
                                        HStack {
                                            Image(systemName: "info.circle.fill")
                                                .foregroundColor(.yellow)
                                            Text("Правило Омниссии:")
                                                .font(.subheadline)
                                                .bold()
                                                .foregroundColor(.yellow)
                                        }
                                        Text(currentDrill.ruleExplanation)
                                            .font(.caption)
                                            .foregroundColor(.white.opacity(0.9))
                                        
                                        if isAnswered {
                                            Divider().background(Color.gray)
                                            Text("Каноническая форма:")
                                                .font(.caption)
                                                .bold()
                                                .foregroundColor(.green)
                                            Text(currentDrill.correctedSentence)
                                                .font(.subheadline)
                                                .foregroundColor(.green)
                                        }
                                    }
                                    .padding()
                                    .frame(maxWidth: .infinity, alignment: .leading)
                                    .background(Color(red: 0.14, green: 0.16, blue: 0.24))
                                    .cornerRadius(10)
                                    .overlay(
                                        RoundedRectangle(cornerRadius: 10)
                                            .stroke(Color.yellow.opacity(0.3), lineWidth: 1)
                                    )
                                }
                                
                                // Feedback
                                if isAnswered {
                                    Text(feedbackText)
                                        .font(.subheadline)
                                        .bold()
                                        .foregroundColor(isSuccess ? .green : .orange)
                                }
                                
                                // Next / Previous Navigation
                                HStack {
                                    Button(action: {
                                        if currentIndex > 0 {
                                            currentIndex -= 1
                                            resetCard()
                                        }
                                    }) {
                                        HStack {
                                            Image(systemName: "arrow.left")
                                            Text("Предыдущее")
                                        }
                                        .font(.caption)
                                        .foregroundColor(currentIndex > 0 ? .white : .gray)
                                    }
                                    .disabled(currentIndex == 0)
                                    
                                    Spacer()
                                    
                                    Button(action: {
                                        if currentIndex < filteredDrills.count - 1 {
                                            currentIndex += 1
                                            resetCard()
                                        }
                                    }) {
                                        HStack {
                                            Text("Следующее")
                                            Image(systemName: "arrow.right")
                                        }
                                        .font(.caption)
                                        .foregroundColor(currentIndex < filteredDrills.count - 1 ? .cyan : .gray)
                                    }
                                    .disabled(currentIndex >= filteredDrills.count - 1)
                                }
                                .padding(.top, 8)
                            }
                            .padding()
                        }
                    }
                }
            }
            .navigationTitle("Хирургия предложений")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
    
    private func resetCard() {
        userFixText = ""
        showHint = false
        isAnswered = false
        feedbackText = ""
    }
    
    private func checkAnswer(target: String) {
        let cleanedUser = userFixText.trimmingCharacters(in: .whitespacesAndNewlines).lowercased()
        let cleanedTarget = target.trimmingCharacters(in: .whitespacesAndNewlines).lowercased()
        
        isAnswered = true
        if cleanedUser.isEmpty {
            feedbackText = "⚠️ Введите ваш вариант исправления перед проверкой!"
            isSuccess = false
            return
        }
        
        if cleanedUser == cleanedTarget || cleanedUser.replacingOccurrences(of: ".", with: "") == cleanedTarget.replacingOccurrences(of: ".", with: "") {
            feedbackText = "⚡ Слава Омниссии! Ошибка полностью выжжена!"
            isSuccess = true
        } else {
            feedbackText = "🔍 Сравните ваш вариант с каноническим рецептом ниже."
            isSuccess = false
        }
    }
}

private struct FilterPill: View {
    let title: String
    let isSelected: Bool
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(title)
                .font(.subheadline)
                .bold()
                .padding(.horizontal, 14)
                .padding(.vertical, 8)
                .background(isSelected ? Color.cyan : Color(red: 0.15, green: 0.17, blue: 0.25))
                .foregroundColor(isSelected ? .black : .white)
                .cornerRadius(20)
        }
    }
}
