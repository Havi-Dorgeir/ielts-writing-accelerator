import SwiftUI

public struct RoadmapView: View {
    @State private var completedDays: Set<Int> = [1, 2, 3, 4, 5]
    public var onSelectTab: (String) -> Void

    public init(onSelectTab: @escaping (String) -> Void) {
        self.onSelectTab = onSelectTab
    }

    public var body: some View {
        NavigationStack {
            ZStack {
                Color(red: 0.07, green: 0.07, blue: 0.07).ignoresSafeArea()

                ScrollView {
                    VStack(alignment: .leading, spacing: 14) {
                        // Header card
                        VStack(alignment: .leading, spacing: 8) {
                            HStack {
                                Text("Прогресс плана: \(completedDays.count) из 14 дней")
                                    .font(.headline)
                                    .foregroundColor(.white)
                                Spacer()
                                Text("\(completedDays.count * 100 / 14)%")
                                    .font(.headline)
                                    .foregroundColor(Color(red: 0.0, green: 0.9, blue: 0.46))
                            }

                            ProgressView(value: Double(completedDays.count), total: 14.0)
                                .tint(Color(red: 0.0, green: 0.9, blue: 0.46))
                        }
                        .padding()
                        .background(Color(red: 0.12, green: 0.12, blue: 0.12))
                        .cornerRadius(12)

                        // 14 Days List
                        ForEach(PrepopulatedData.fourteenDayPlan) { item in
                            let isDone = completedDays.contains(item.dayNumber)

                            VStack(alignment: .leading, spacing: 8) {
                                HStack {
                                    Text("ДЕНЬ \(item.dayNumber) • \(item.phaseName)")
                                        .font(.caption)
                                        .fontWeight(.bold)
                                        .foregroundColor(isDone ? Color(red: 0.0, green: 0.9, blue: 0.46) : Color(red: 0.5, green: 0.85, blue: 1.0))

                                    Spacer()

                                    Button {
                                        if isDone {
                                            completedDays.remove(item.dayNumber)
                                        } else {
                                            completedDays.insert(item.dayNumber)
                                        }
                                    } label: {
                                        Image(systemName: isDone ? "checkmark.circle.fill" : "circle")
                                            .foregroundColor(isDone ? Color(red: 0.0, green: 0.9, blue: 0.46) : .gray)
                                            .font(.title3)
                                    }
                                }

                                Text(item.title)
                                    .font(.headline)
                                    .foregroundColor(.white)

                                Text(item.focusArea)
                                    .font(.subheadline)
                                    .foregroundColor(.gray)

                                HStack {
                                    Text("🎯 Цель: \(item.kpiTarget)")
                                        .font(.caption2)
                                        .foregroundColor(Color(red: 0.5, green: 0.85, blue: 1.0))

                                    Spacer()

                                    Button("Перейти") {
                                        onSelectTab(item.recommendedTab)
                                    }
                                    .font(.caption)
                                    .fontWeight(.semibold)
                                    .padding(.horizontal, 12)
                                    .padding(.vertical, 6)
                                    .background(Color(red: 0.18, green: 0.18, blue: 0.18))
                                    .foregroundColor(Color(red: 0.0, green: 0.9, blue: 0.46))
                                    .cornerRadius(6)
                                }
                            }
                            .padding()
                            .background(isDone ? Color(red: 0.09, green: 0.15, blue: 0.10) : Color(red: 0.12, green: 0.12, blue: 0.12))
                            .cornerRadius(12)
                        }
                    }
                    .padding()
                }
            }
            .navigationTitle("14-ДНЕВНЫЙ ПЛАН")
            .toolbarBackground(Color(red: 0.09, green: 0.09, blue: 0.09), for: .navigationBar)
            .toolbarBackground(.visible, for: .navigationBar)
        }
    }
}
