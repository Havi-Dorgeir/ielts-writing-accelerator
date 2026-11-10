import SwiftUI

public struct TaskOneView: View {
    @State private var selectedCategory: String = "ALL"
    @State private var currentIndex: Int = 0
    @State private var selectedOption: Int? = nil

    private let categories = [
        ("ALL", "Все типы"),
        ("PREPOSITIONS", "Предлоги (at/by/to)"),
        ("OVERVIEW", "Обзор без цифр"),
        ("TRENDS", "Глаголы трендов"),
        ("PROCESS_PASSIVE", "Пассив"),
        ("COMPARISON", "Сравнения")
    ]

    private var filteredDrills: [TaskOneDrillItem] {
        if selectedCategory == "ALL" {
            return PrepopulatedData.taskOneDrills
        } else {
            return PrepopulatedData.taskOneDrills.filter { $0.type == selectedCategory }
        }
    }

    public init() {}

    public var body: some View {
        NavigationStack {
            ZStack {
                Color(red: 0.07, green: 0.07, blue: 0.07).ignoresSafeArea()

                ScrollView {
                    VStack(alignment: .leading, spacing: 14) {
                        // Horizontal category pills
                        ScrollView(.horizontal, showsIndicators: false) {
                            HStack(spacing: 8) {
                                ForEach(categories, id: \.0) { catKey, catName in
                                    Button {
                                        selectedCategory = catKey
                                        currentIndex = 0
                                        selectedOption = nil
                                    } label: {
                                        Text(catName)
                                            .font(.caption)
                                            .fontWeight(.medium)
                                            .padding(.horizontal, 12)
                                            .padding(.vertical, 8)
                                            .background(selectedCategory == catKey ? Color(red: 0.0, green: 0.9, blue: 0.46) : Color(red: 0.15, green: 0.15, blue: 0.15))
                                            .foregroundColor(selectedCategory == catKey ? .black : .white)
                                            .cornerRadius(20)
                                    }
                                }
                            }
                        }

                        if !filteredDrills.isEmpty {
                            let item = filteredDrills[min(currentIndex, filteredDrills.count - 1)]

                            HStack {
                                Text("Задание \(currentIndex + 1) из \(filteredDrills.count)")
                                    .font(.subheadline)
                                    .foregroundColor(Color(red: 0.5, green: 0.85, blue: 1.0))
                                Spacer()
                                Text(item.chartType)
                                    .font(.caption2)
                                    .padding(.horizontal, 8)
                                    .padding(.vertical, 4)
                                    .background(Color(red: 0.18, green: 0.18, blue: 0.18))
                                    .foregroundColor(.white)
                                    .cornerRadius(6)
                            }

                            // Question card
                            VStack(alignment: .leading, spacing: 10) {
                                Text(item.title)
                                    .font(.subheadline)
                                    .fontWeight(.bold)
                                    .foregroundColor(Color(red: 0.0, green: 0.9, blue: 0.46))

                                Text(item.prompt)
                                    .font(.body)
                                    .foregroundColor(.white)
                                    .lineSpacing(4)
                            }
                            .padding()
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .background(Color(red: 0.12, green: 0.12, blue: 0.12))
                            .cornerRadius(12)

                            // Options List
                            VStack(spacing: 8) {
                                ForEach(Array(item.options.enumerated()), id: \.offset) { idx, option in
                                    let isSelected = selectedOption == idx
                                    let isCorrect = idx == item.correctIndex

                                    Button {
                                        if selectedOption == nil {
                                            selectedOption = idx
                                        }
                                    } label: {
                                        HStack(alignment: .top, spacing: 10) {
                                            Text("\(Character(UnicodeScalar(65 + idx)!)).")
                                                .fontWeight(.bold)
                                                .foregroundColor(isSelected ? Color(red: 0.0, green: 0.9, blue: 0.46) : .gray)

                                            Text(option)
                                                .font(.subheadline)
                                                .foregroundColor(.white)
                                                .multilineTextAlignment(.leading)

                                            Spacer()
                                        }
                                        .padding()
                                        .frame(maxWidth: .infinity)
                                        .background(
                                            selectedOption == nil ? Color(red: 0.12, green: 0.12, blue: 0.12) :
                                            (isSelected && isCorrect ? Color(red: 0.09, green: 0.22, blue: 0.12) :
                                             (isSelected && !isCorrect ? Color(red: 0.25, green: 0.09, blue: 0.09) :
                                              (idx == item.correctIndex ? Color(red: 0.09, green: 0.22, blue: 0.12).opacity(0.6) : Color(red: 0.10, green: 0.10, blue: 0.10))))
                                        )
                                        .cornerRadius(10)
                                        .overlay(
                                            RoundedRectangle(cornerRadius: 10)
                                                .stroke(isSelected && isCorrect ? Color(red: 0.0, green: 0.9, blue: 0.46) : (isSelected && !isCorrect ? .red : Color.clear), lineWidth: 1)
                                        )
                                    }
                                }
                            }

                            // Explanation
                            if let opt = selectedOption {
                                let correct = opt == item.correctIndex
                                VStack(alignment: .leading, spacing: 6) {
                                    Text(correct ? "✅ ВЕРНО!" : "❌ ОШИБКА АНАЛИТИКИ:")
                                        .fontWeight(.bold)
                                        .foregroundColor(correct ? Color(red: 0.0, green: 0.9, blue: 0.46) : .red)
                                    Text(item.explanation)
                                        .font(.caption)
                                        .foregroundColor(.white)
                                }
                                .padding()
                                .frame(maxWidth: .infinity, alignment: .leading)
                                .background(correct ? Color(red: 0.09, green: 0.18, blue: 0.12) : Color(red: 0.22, green: 0.09, blue: 0.09))
                                .cornerRadius(8)
                            }

                            // Navigation
                            HStack {
                                Button("Предыдущее") {
                                    if currentIndex > 0 {
                                        currentIndex -= 1
                                        selectedOption = nil
                                    }
                                }
                                .disabled(currentIndex == 0)
                                .foregroundColor(currentIndex == 0 ? .gray : .white)
                                .frame(maxWidth: .infinity)
                                .padding()
                                .background(Color(red: 0.15, green: 0.15, blue: 0.15))
                                .cornerRadius(8)

                                Button("Следующее") {
                                    if currentIndex < filteredDrills.count - 1 {
                                        currentIndex += 1
                                        selectedOption = nil
                                    }
                                }
                                .disabled(currentIndex >= filteredDrills.count - 1)
                                .foregroundColor(currentIndex >= filteredDrills.count - 1 ? .gray : .white)
                                .frame(maxWidth: .infinity)
                                .padding()
                                .background(Color(red: 0.15, green: 0.15, blue: 0.15))
                                .cornerRadius(8)
                            }
                        }
                    }
                    .padding()
                }
            }
            .navigationTitle("TASK 1 ЛАБОРАТОРИЯ")
            .toolbarBackground(Color(red: 0.09, green: 0.09, blue: 0.09), for: .navigationBar)
            .toolbarBackground(.visible, for: .navigationBar)
        }
    }
}
