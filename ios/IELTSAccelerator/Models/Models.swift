import Foundation

public struct DayPlanItem: Identifiable {
    public let id: Int
    public let dayNumber: Int
    public let phaseName: String
    public let title: String
    public let focusArea: String
    public let dailyQuota: String
    public let kpiTarget: String
    public let recommendedTab: String

    public init(dayNumber: Int, phaseName: String, title: String, focusArea: String, dailyQuota: String, kpiTarget: String, recommendedTab: String) {
        self.id = dayNumber
        self.dayNumber = dayNumber
        self.phaseName = phaseName
        self.title = title
        self.focusArea = focusArea
        self.dailyQuota = dailyQuota
        self.kpiTarget = kpiTarget
        self.recommendedTab = recommendedTab
    }
}

public struct TaskOneDrillItem: Identifiable {
    public let id: String
    public let type: String
    public let chartType: String
    public let title: String
    public let prompt: String
    public let options: [String]
    public let correctIndex: Int
    public let explanation: String
}

public struct WordFamilyItem: Identifiable {
    public var id: String { base }
    public let base: String
    public let verb: String
    public let noun: String
    public let adjective: String
    public let trapPrompt: String
    public let options: [String]
    public let correctIndex: Int
}

public struct DictionaryEntry: Identifiable {
    public var id: String { wrongOrSimple + academicFix }
    public let category: String
    public let wrongOrSimple: String
    public let academicFix: String
    public let explanation: String
    public let bandLevel: Double
}

public struct EssayTopic: Identifiable {
    public var id: String { title }
    public let taskType: Int
    public let category: String
    public let title: String
    public let prompt: String
    public let keyTips: String
}

public struct SurgeryDrillItem: Identifiable {
    public let id: String
    public let targetBand: Double
    public let category: String
    public let rawSentence: String
    public let correctedSentence: String
    public let ruleExplanation: String
}
