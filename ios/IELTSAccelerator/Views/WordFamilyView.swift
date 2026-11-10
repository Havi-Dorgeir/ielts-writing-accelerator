import SwiftUI

public struct WordFamilyView: View {
    @State private var selectedIndex: Int = 0
    @State private var selectedOptionIndex: Int? = nil
    @State private var isChecked: Bool = false
    @State private var correctCount: Int = 0
    @State private var totalAnswered: Int = 0
    
    private let items = PrepopulatedData.wordFamilies
    
    public init() {}
    
    public var body: some View {
        NavigationView {
            ZStack {
                Color(red: 0.07, green: 0.08, blue: 0.12)
                    .ignoresSafeArea()
                
                VStack(spacing: 12) {
                    // Accuracy Bar
                    HStack {
                        Text("Освоено корней: \(items.count)")
                            .font(.caption)
                            .foregroundColor(.gray)
                        Spacer()
                        Text("Точность: \(totalAnswered > 0 ? "\(Int((Double(correctCount) / Double(totalAnswered)) * 100))%" : "0%")")
                            .font(.caption)
                            .bold()
                            .foregroundColor(.green)
                    }
                    .padding(.horizontal)
                    
                    let item = items[selectedIndex]
                    
                    ScrollView {
                        VStack(alignment: .leading, spacing: 16) {
                            // Root Header
                            HStack {
                                Text("Корень #\(selectedIndex + 1):")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                                Text(item.base)
                                    .font(.title2)
                                    .bold()
                                    .foregroundColor(.cyan)
                                Spacer()
                            }
                            
                            // 3-Part Matrix: Verb, Noun, Adjective
                            VStack(spacing: 8) {
                                PartOfSpeechRow(badge: "V", badgeColor: .green, role: "Глагол (Действие):", word: item.verb)
                                PartOfSpeechRow(badge: "N", badgeColor: .cyan, role: "Существительное (Объект):", word: item.noun)
                                PartOfSpeechRow(badge: "Adj", badgeColor: .orange, role: "Прилагательное (Признак):", word: item.adjective)
                            }
                            .padding()
                            .background(Color(red: 0.12, green: 0.14, blue: 0.20))
                            .cornerRadius(12)
                            
                            // IELTS Trap Drill
                            VStack(alignment: .leading, spacing: 12) {
                                HStack {
                                    Image(systemName: "exclamationmark.triangle.fill")
                                        .foregroundColor(.yellow)
                                    Text("Ловушка экзаменатора IELTS:")
                                        .font(.subheadline)
                                        .bold()
                                        .foregroundColor(.yellow)
                                }
                                
                                Text(item.trapPrompt)
                                    .font(.body)
                                    .foregroundColor(.white)
                                    .padding(.vertical, 4)
                                
                                // Options
                                ForEach(0..<item.options.count, id: \.self) { idx in
                                    let option = item.options[idx]
                                    Button(action: {
                                        if !isChecked {
                                            selectedOptionIndex = idx
                                        }
                                    }) {
                                        HStack {
                                            Text(option)
                                                .font(.subheadline)
                                                .foregroundColor(optionTextColor(for: idx, correctIdx: item.correctIndex))
                                            Spacer()
                                            if isChecked && idx == item.correctIndex {
                                                Image(systemName: "checkmark.circle.fill")
                                                    .foregroundColor(.green)
                                            } else if isChecked && selectedOptionIndex == idx && idx != item.correctIndex {
                                                Image(systemName: "xmark.circle.fill")
                                                    .foregroundColor(.red)
                                            }
                                        }
                                        .padding()
                                        .background(optionBackgroundColor(for: idx, correctIdx: item.correctIndex))
                                        .cornerRadius(10)
                                        .overlay(
                                            RoundedRectangle(cornerRadius: 10)
                                                .stroke(selectedOptionIndex == idx ? Color.cyan : Color.white.opacity(0.1), lineWidth: 1.5)
                                        )
                                    }
                                }
                                
                                // Check Button
                                if !isChecked {
                                    Button(action: {
                                        if let selected = selectedOptionIndex {
                                            isChecked = true
                                            totalAnswered += 1
                                            if selected == item.correctIndex {
                                                correctCount += 1
                                            }
                                        }
                                    }) {
                                        Text("Проверить выбор")
                                            .font(.subheadline)
                                            .bold()
                                            .foregroundColor(.black)
                                            .frame(maxWidth: .infinity)
                                            .padding(.vertical, 12)
                                            .background(selectedOptionIndex != nil ? Color.cyan : Color.gray)
                                            .cornerRadius(10)
                                    }
                                    .disabled(selectedOptionIndex == nil)
                                }
                            }
                            .padding()
                            .background(Color(red: 0.12, green: 0.14, blue: 0.20))
                            .cornerRadius(12)
                            
                            // Navigation Buttons
                            HStack {
                                Button(action: {
                                    if selectedIndex > 0 {
                                        selectedIndex -= 1
                                        resetDrill()
                                    }
                                }) {
                                    HStack {
                                        Image(systemName: "chevron.left")
                                        Text("Предыдущий корень")
                                    }
                                    .font(.caption)
                                    .foregroundColor(selectedIndex > 0 ? .cyan : .gray)
                                }
                                .disabled(selectedIndex == 0)
                                
                                Spacer()
                                
                                Button(action: {
                                    if selectedIndex < items.count - 1 {
                                        selectedIndex += 1
                                        resetDrill()
                                    }
                                }) {
                                    HStack {
                                        Text("Следующий корень")
                                        Image(systemName: "chevron.right")
                                    }
                                    .font(.caption)
                                    .foregroundColor(selectedIndex < items.count - 1 ? .cyan : .gray)
                                }
                                .disabled(selectedIndex >= items.count - 1)
                            }
                        }
                        .padding()
                    }
                }
            }
            .navigationTitle("Матрица 30 Частей Речи")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
    
    private func resetDrill() {
        selectedOptionIndex = nil
        isChecked = false
    }
    
    private func optionBackgroundColor(for idx: Int, correctIdx: Int) -> Color {
        if !isChecked {
            return selectedOptionIndex == idx ? Color.cyan.opacity(0.15) : Color(red: 0.16, green: 0.18, blue: 0.26)
        }
        if idx == correctIdx {
            return Color.green.opacity(0.2)
        }
        if selectedOptionIndex == idx {
            return Color.red.opacity(0.2)
        }
        return Color(red: 0.16, green: 0.18, blue: 0.26)
    }
    
    private func optionTextColor(for idx: Int, correctIdx: Int) -> Color {
        if !isChecked {
            return .white
        }
        if idx == correctIdx {
            return .green
        }
        if selectedOptionIndex == idx {
            return .red
        }
        return .gray
    }
}

private struct PartOfSpeechRow: View {
    let badge: String
    let badgeColor: Color
    let role: String
    let word: String
    
    var body: some View {
        HStack(spacing: 12) {
            Text(badge)
                .font(.caption)
                .bold()
                .frame(width: 34, height: 26)
                .background(badgeColor.opacity(0.2))
                .foregroundColor(badgeColor)
                .cornerRadius(6)
            
            Text(role)
                .font(.caption)
                .foregroundColor(.gray)
            
            Spacer()
            
            Text(word)
                .font(.subheadline)
                .bold()
                .foregroundColor(.white)
        }
    }
}
