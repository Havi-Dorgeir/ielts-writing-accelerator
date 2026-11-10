import SwiftUI

public struct DictionaryView: View {
    @State private var searchText: String = ""
    @State private var selectedCategory: String = "ALL"
    
    private let categories = [
        ("ALL", "Все"),
        ("STOP_LIST", "Стоп-лист"),
        ("ACADEMIC_UPGRADE", "Академ. апгрейд"),
        ("CONNECTOR", "Связки"),
        ("TASK1_DATA", "Предлоги данных")
    ]
    
    public init() {}
    
    private var filteredEntries: [DictionaryEntry] {
        PrepopulatedData.dictionary.filter { entry in
            let matchesCategory = (selectedCategory == "ALL" || entry.category == selectedCategory)
            let matchesSearch = searchText.isEmpty ||
                entry.wrongOrSimple.localizedCaseInsensitiveContains(searchText) ||
                entry.academicFix.localizedCaseInsensitiveContains(searchText) ||
                entry.explanation.localizedCaseInsensitiveContains(searchText)
            return matchesCategory && matchesSearch
        }
    }
    
    public var body: some View {
        NavigationView {
            ZStack {
                Color(red: 0.07, green: 0.08, blue: 0.12)
                    .ignoresSafeArea()
                
                VStack(spacing: 12) {
                    // Search Bar
                    HStack {
                        Image(systemName: "magnifyingglass")
                            .foregroundColor(.gray)
                        TextField("Поиск по словарю...", text: $searchText)
                            .foregroundColor(.white)
                        if !searchText.isEmpty {
                            Button(action: { searchText = "" }) {
                                Image(systemName: "xmark.circle.fill")
                                    .foregroundColor(.gray)
                            }
                        }
                    }
                    .padding(10)
                    .background(Color(red: 0.14, green: 0.16, blue: 0.22))
                    .cornerRadius(10)
                    .padding(.horizontal)
                    
                    // Filter Pills
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 8) {
                            ForEach(categories, id: \.0) { cat in
                                Button(action: {
                                    selectedCategory = cat.0
                                }) {
                                    Text(cat.1)
                                        .font(.caption)
                                        .bold()
                                        .padding(.horizontal, 12)
                                        .padding(.vertical, 6)
                                        .background(selectedCategory == cat.0 ? Color.cyan : Color(red: 0.14, green: 0.16, blue: 0.22))
                                        .foregroundColor(selectedCategory == cat.0 ? .black : .white)
                                        .cornerRadius(16)
                                }
                            }
                        }
                        .padding(.horizontal)
                    }
                    
                    // Entry List
                    ScrollView {
                        LazyVStack(spacing: 12) {
                            ForEach(filteredEntries) { entry in
                                DictionaryCard(entry: entry)
                            }
                        }
                        .padding()
                    }
                }
            }
            .navigationTitle("Академический Словарь")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

private struct DictionaryCard: View {
    let entry: DictionaryEntry
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text(entry.category)
                    .font(.caption2)
                    .bold()
                    .padding(.horizontal, 8)
                    .padding(.vertical, 3)
                    .background(Color.blue.opacity(0.2))
                    .foregroundColor(.cyan)
                    .cornerRadius(6)
                
                Spacer()
                
                Text("Band \(String(format: "%.1f", entry.bandLevel))+")
                    .font(.caption2)
                    .bold()
                    .padding(.horizontal, 8)
                    .padding(.vertical, 3)
                    .background(Color.yellow.opacity(0.15))
                    .foregroundColor(.yellow)
                    .cornerRadius(6)
            }
            
            // Comparison
            HStack(alignment: .top, spacing: 12) {
                VStack(alignment: .leading, spacing: 2) {
                    Text("❌ Мусорная форма:")
                        .font(.caption2)
                        .foregroundColor(.red.opacity(0.7))
                    Text(entry.wrongOrSimple)
                        .font(.subheadline)
                        .strikethrough()
                        .foregroundColor(.red.opacity(0.9))
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                
                Image(systemName: "arrow.right")
                    .foregroundColor(.gray)
                    .padding(.top, 12)
                
                VStack(alignment: .leading, spacing: 2) {
                    Text("⚡ Академический рецепт:")
                        .font(.caption2)
                        .foregroundColor(.green.opacity(0.7))
                    Text(entry.academicFix)
                        .font(.subheadline)
                        .bold()
                        .foregroundColor(.green)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
            }
            
            Divider().background(Color.white.opacity(0.1))
            
            Text(entry.explanation)
                .font(.caption)
                .foregroundColor(.gray)
        }
        .padding()
        .background(Color(red: 0.12, green: 0.14, blue: 0.20))
        .cornerRadius(12)
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(Color.white.opacity(0.08), lineWidth: 1)
        )
    }
}
