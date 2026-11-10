import SwiftUI

public struct ContentView: View {
    @State private var selectedTab: Int = 0
    
    public init() {}
    
    public var body: some View {
        TabView(selection: $selectedTab) {
            RoadmapView()
                .tabItem {
                    Label("14 Дней", systemImage: "calendar.badge.clock")
                }
                .tag(0)
            
            TaskOneView()
                .tabItem {
                    Label("Task 1 Lab", systemImage: "chart.xyaxis.line")
                }
                .tag(1)
            
            SurgeryView()
                .tabItem {
                    Label("Хирургия", systemImage: "cross.case.fill")
                }
                .tag(2)
            
            WordFamilyView()
                .tabItem {
                    Label("30 Корней", systemImage: "textformat.abc")
                }
                .tag(3)
            
            DictionaryView()
                .tabItem {
                    Label("Словарь", systemImage: "character.book.closed.fill")
                }
                .tag(4)
            
            EssayEditorView()
                .tabItem {
                    Label("PEEL Эссе", systemImage: "pencil.and.outline")
                }
                .tag(5)
        }
        .accentColor(.cyan)
        .preferredColorScheme(.dark)
    }
}
