package com.lex.ielts.data.local

import com.lex.ielts.data.local.entity.DrillEntity
import com.lex.ielts.data.local.entity.MistakeEntity

data class DictionaryEntry(
    val category: String, // STOP_LIST, WORD_FAMILY, ACADEMIC_UPGRADE, CONNECTOR
    val wrongOrSimple: String,
    val academicFix: String,
    val explanation: String,
    val bandLevel: Double
)

data class EssayTopic(
    val taskType: Int, // 1 or 2
    val category: String,
    val title: String,
    val prompt: String,
    val keyTips: String
)

object PrepopulatedData {

    val ESSAY_TOPICS = listOf(
        EssayTopic(
            taskType = 2,
            category = "Technology & AI",
            title = "Artificial Intelligence vs Human Labor",
            prompt = "Some scientists believe that computers and artificial intelligence will soon become more intelligent than humans. To what extent do you agree or disagree?",
            keyTips = "Use PEEL. Point: automation displacing white-collar jobs. Counterpoint: emotional intelligence and creative reasoning remain human."
        ),
        EssayTopic(
            taskType = 2,
            category = "Consumerism & Society",
            title = "Luxury Brands & Conspicuous Consumption",
            prompt = "More and more people want to buy famous brands of clothes, cars, and other items. What are the reasons? Do you think it is a positive or negative development?",
            keyTips = "Avoid 'strongly bad'. Use 'social validation', 'financial burden', 'status symbol'. Structure 4 sentences per body paragraph."
        ),
        EssayTopic(
            taskType = 2,
            category = "Environment",
            title = "Single-Use Plastics & Pollution",
            prompt = "Plastic waste has become a severe environmental crisis worldwide. What are the main causes of this problem, and what measures can governments and individuals take?",
            keyTips = "Do not use 'emitter' for plastic. Use 'non-biodegradable waste', 'impose strict levies', 'subsidize biodegradable alternatives'."
        ),
        EssayTopic(
            taskType = 2,
            category = "Education",
            title = "Practical Skills vs Academic Theory",
            prompt = "Some people think that secondary schools should teach practical skills such as car maintenance and financial management alongside traditional academic subjects. Do you agree or disagree?",
            keyTips = "Point: financial literacy prevents debt. Example: budgeting and tax preparation cultivate self-sufficiency."
        ),
        EssayTopic(
            taskType = 2,
            category = "Work & Health",
            title = "Work-Life Balance & Remote Work",
            prompt = "In many countries, working hours are increasing, and employees are experiencing chronic burnout. What are the consequences of this trend for individuals and society?",
            keyTips = "Use 'mental fatigue', 'deteriorating physical health', 'erosion of social bonds'."
        ),
        EssayTopic(
            taskType = 1,
            category = "Map Redevelopment",
            title = "Brandfield Town Planning",
            prompt = "The two maps show the proposed redevelopment of the town of Brandfield between the present day and 2030.",
            keyTips = "STRICT: Zero numbers in Overview! Use passive voice: 'will be demolished', 'is projected to be converted into'."
        ),
        EssayTopic(
            taskType = 1,
            category = "Pie Chart",
            title = "Household Expenditure 1950 vs 2010",
            prompt = "The pie charts illustrate average household expenditure across various categories in a European country in 1950 and 2010.",
            keyTips = "Overview must describe dominant categories without '%'. Use 'accounted for the lion's share', 'followed by', 'marginal proportions'."
        ),
        EssayTopic(
            taskType = 1,
            category = "Process Diagram",
            title = "Wave Energy Generation",
            prompt = "The diagram illustrates how energy is produced by ocean waves through an offshore installation.",
            keyTips = "Strict passive: 'water is forced through', 'turbines are driven by air pressure', 'electricity is fed into the national grid'."
        )
    )

    val DICTIONARY = listOf(
        DictionaryEntry("STOP_LIST", "thresh", "trash / refuse / solid waste", "thresh означает обмолачивать зерно. Мусор — это trash или waste.", 6.0),
        DictionaryEntry("STOP_LIST", "emitter", "pollutant / culprit / emission source", "emitter — это источник выбросов, а не 'проблема' или 'виновник'.", 6.0),
        DictionaryEntry("STOP_LIST", "in twice bigger than", "twice as large as / double that of", "Калька с русского 'в два раза больше'. В английском используется twice as [adj] as.", 6.5),
        DictionaryEntry("STOP_LIST", "cannot to give", "cannot give", "Модальные глаголы (can, must, should) требуют голый инфинитив без частицы to.", 6.0),
        DictionaryEntry("STOP_LIST", "play opposite to us", "backfire / work against us", "Калька с русского 'сыграть против нас'. Академический глагол — backfire.", 7.0),
        DictionaryEntry("STOP_LIST", "strongly bad development", "severely detrimental trend", "Разговорное сочетание, снижающее балл LR до 5.0.", 7.0),
        DictionaryEntry("STOP_LIST", "a lot of money", "substantial financial resources", "a lot of — разговорное выражение, недопустимое в академическом эссе.", 6.5),
        DictionaryEntry("STOP_LIST", "really terrible", "alarming / grave concern", "Избегайте эмоциональных разговорных наречий.", 6.5),
        DictionaryEntry("WORD_FAMILY", "food became dominate", "became dominant", "После глаголов become/remain требуется прилагательное dominant, а не глагол dominate.", 6.0),
        DictionaryEntry("WORD_FAMILY", "consume of beer", "consumption of beer", "consume — глагол. Существительное — consumption.", 6.0),
        DictionaryEntry("WORD_FAMILY", "is depend on", "depends on / is dependent on", "Либо активный глагол depends, либо связка + прилагательное dependent.", 6.5),
        DictionaryEntry("WORD_FAMILY", "produce by famous brand", "produced by a famous brand", "Причастие прошедшего времени (V3) в определительном обороте.", 6.5),
        DictionaryEntry("ACADEMIC_UPGRADE", "people want to buy things", "consumers are driven to acquire goods", "Апгрейд примитивной формулировки до академического уровня.", 7.5),
        DictionaryEntry("ACADEMIC_UPGRADE", "it need money", "this endeavor requires substantial funding", "Номинализация и повышение точности предикатов.", 7.5),
        DictionaryEntry("ACADEMIC_UPGRADE", "kids work on post office", "adolescents seek summer employment", "Замена бытовой лексики на официальный академический регистр.", 7.0),
        DictionaryEntry("CONNECTOR", "In spite of everything", "Nonetheless, / Despite these drawbacks,", "Академическая замена разговорного зачина.", 7.0),
        DictionaryEntry("CONNECTOR", "in result", "As a result, / Consequently,", "В английском нет фразы in result. Только As a result.", 6.0),
        DictionaryEntry("CONNECTOR", "Turning to the details,", "Regarding the specific data,", "Каноническая связка для перехода к абзацам деталей в Task 1.", 6.5)
    )

    val SURGERY_DRILLS = listOf(
        // BAND 6.0: ФУНДАМЕНТ (Zero-Defect)
        MistakeEntity("s_601", 6.0, "GRA_MISSING_TO_BE", "This certain model so expansive for simple people.", "This certain model is so expensive for average consumers.", "В английском предложении обязателен глагол-связка 'is' перед предикативом/прилагательным."),
        MistakeEntity("s_602", 6.0, "GRA_MODAL_TO", "Governments cannot to solve this problem alone.", "Governments cannot solve this problem alone.", "После модального глагола cannot частица to категорически запрещена."),
        MistakeEntity("s_603", 6.0, "GRA_3RD_PERSON_S", "The chart demonstrate that housing consume the biggest part.", "The chart demonstrates that housing consumes the largest share.", "Подлежащие в 3-м лице единственного числа требуют окончания -s у сказуемых."),
        MistakeEntity("s_604", 6.0, "GRA_VERB_CONFUSION", "Many young individuals are study hard for to pass exams.", "Many young individuals study hard in order to pass exams.", "Паразитная связка 'are study' заменяется на чистый Present Simple 'study'."),
        MistakeEntity("s_605", 6.0, "GRA_PASSIVE", "Energy is generate by wave in this installation.", "Energy is generated by waves in this installation.", "Пассивный залог строится по строгой формуле: to be + V3 (is generated)."),
        MistakeEntity("s_606", 6.0, "LR_STOP_LIST", "People throw a lot of thresh into ocean.", "People discard substantial amounts of waste into the ocean.", "thresh — фонетическая ошибка. Заменяется на waste / refuse."),

        // BAND 6.5: СИНТАКСИЧЕСКИЙ СИНТЕЗ (Complex Sentences)
        MistakeEntity("s_651", 6.5, "GRA_GERUND", "In 2010 housing stopped be the biggest expenditures in country.", "In 2010 housing stopped being the biggest expenditure in the country.", "Глагол stop требует герундия (being), превосходная степень требует 'the', а expenditure — ед. числа."),
        MistakeEntity("s_652", 6.5, "GRA_CONDITIONAL_FRAGMENT", "If students want to buy laptop but lack money, and they work.", "If students want to buy a laptop but lack money, they seek employment.", "Союз and разрушает условное предложение, превращая его в бессмысленный обрубок."),
        MistakeEntity("s_653", 6.5, "LR_WORD_FAMILY", "Renewable energy became dominate source in recent years.", "Renewable energy became the dominant source in recent years.", "dominate — глагол. В роли определения к слову source требуется прилагательное dominant."),
        MistakeEntity("s_654", 6.5, "GRA_COMPARISON", "The second category was in twice bigger than first category.", "The second category was twice as large as the first one.", "Нелегитимная калька 'in twice bigger'. Правильно: twice as large as или double that of."),
        MistakeEntity("s_655", 6.5, "LR_COLLOCATION", "This situation is strongly bad development for modern society.", "This situation is a highly negative development for modern society.", "Коллокация strongly bad нарушает академический регистр. Заменяется на highly negative."),

        // BAND 7.0+: МАГИСТЕРИУМ (Nominalization & Hedging)
        MistakeEntity("s_701", 7.0, "GRA_CONCESSION_IDOM", "This demonstrate useful but later I demonstrate this dream can play opposite.", "Although such goals may seem beneficial, this pursuit can ultimately backfire.", "Устранение анонсов от первого лица, внедрение союза Although и идиомы backfire."),
        MistakeEntity("s_702", 7.0, "GRA_NOMINALIZATION", "Because people want famous brands, it make them work harder.", "The desire for prestigious brands incentivizes individuals to work more diligently.", "Номинализация: замена детского 'because people want' на плотную именную группу 'the desire for...'."),
        MistakeEntity("s_703", 7.0, "GRA_HEDGING", "Overwork always completely destroy human health.", "Chronic overwork tends to severely undermine personal well-being.", "Академический хеджинг: замена категоричного 'always completely destroy' на сдержанное 'tends to severely undermine'.")
    )
}
