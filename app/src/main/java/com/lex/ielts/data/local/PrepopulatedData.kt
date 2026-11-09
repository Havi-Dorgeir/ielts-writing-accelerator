package com.lex.ielts.data.local

import com.lex.ielts.data.local.entity.MistakeEntity
import com.lex.ielts.presentation.screens.WordFamilyItem

data class DayPlanItem(
    val dayNumber: Int,
    val phaseName: String,
    val title: String,
    val focusArea: String,
    val dailyQuota: String,
    val kpiTarget: String,
    val recommendedTab: String
)

data class TaskOneDrillItem(
    val id: String,
    val type: String, // PREPOSITIONS, OVERVIEW, TRENDS, PROCESS_PASSIVE, COMPARISON
    val chartType: String, // Line Graph, Bar Chart, Map, Process, Table, Pie Chart
    val title: String,
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class DictionaryEntry(
    val category: String, // STOP_LIST, WORD_FAMILY, ACADEMIC_UPGRADE, CONNECTOR, TASK1_DATA
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

    // 14-ДНЕВНЫЙ ПЛАН
    val FOURTEEN_DAY_PLAN = listOf(
        DayPlanItem(1, "Фаза 1: Хирургия", "Ликвидация связок to be", "Пропуск is/are перед предикативами ('it dangerous', 'they study').", "15 предложений без пропусков", "0 пропущенных is/are", "SURGERY"),
        DayPlanItem(2, "Фаза 1: Хирургия", "Модальные связки без to", "Искоренение паразитной частицы to после can, cannot, should, must.", "15 предложений с модалами", "0 конструкций cannot to", "SURGERY"),
        DayPlanItem(3, "Фаза 1: Хирургия", "Согласование 3-го лица и пассив", "Окончание -s/es в Present Simple и формула пассива be + V3.", "15 предложений на пассив и -s", "0 потерянных окончаний -s", "SURGERY"),
        DayPlanItem(4, "Фаза 1: Хирургия", "Матрица 30 частей речи", "Различение Verb vs Noun vs Adj и зачистка рунглиша (thresh, emitter).", "25 карточек словоформ", "0 ошибок в частях речи", "WORD_FAMILY"),
        DayPlanItem(5, "Фаза 2: Модули", "Task 1: Введение и Overview", "Строгое правило: Overview без цифр и процентов + пассивное введение.", "2 отчета Intro+Overview", "0 цифр в Overview", "TASK1_LAB"),
        DayPlanItem(6, "Фаза 2: Модули", "Task 1: Предлоги данных и Тренды", "Предлоги at, by, to, of + глаголы soared, plunged, fluctuated.", "20 упражнений Task 1 Lab", "100% точность предлогов", "TASK1_LAB"),
        DayPlanItem(7, "Фаза 2: Модули", "Task 2: PEEL-структура Body 1", "Строгая 4-строчная формула: Point -> Explanation -> Example -> Link.", "2 абзаца PEEL для Opinion Essay", "Ровно 4 предложения на абзац", "PEEL_EDITOR"),
        DayPlanItem(8, "Фаза 2: Модули", "Task 2: PEEL-структура Body 2", "Контраргументация, академические связки (Nonetheless, Consequently).", "2 абзаца PEEL для Discussion Essay", "Академическая лексика без сленга", "PEEL_EDITOR"),
        DayPlanItem(9, "Фаза 2: Модули", "Task 2: Введение и Вывод", "Скоростной перифраз темы и Thesis Statement за 4 минуты.", "3 введения и 3 заключения", "Четкая позиция автора", "PEEL_EDITOR"),
        DayPlanItem(10, "Фаза 3: Симуляция", "Task 1: Процессы и Карты", "Отработка пассива в картах редевелопмента и производственных циклах.", "2 отчета по процессам", "100% пассивный залог", "TASK1_LAB"),
        DayPlanItem(11, "Фаза 3: Симуляция", "Боевой спринт: Task 1 на таймер", "Полный отчет Task 1 за 15 минут + 5 минут аудит по чеклисту.", "1 полный Task 1 на 170+ слов", "Самопроверка за 5 минут", "PEEL_EDITOR"),
        DayPlanItem(12, "Фаза 3: Симуляция", "Боевой спринт: Task 2 на таймер", "Полное эссе Task 2 за 33 минуты + 7 минут дебаггинг.", "1 полное эссе Task 2 на 270+ слов", "0 грамматических сбоев", "PEEL_EDITOR"),
        DayPlanItem(13, "Фаза 3: Симуляция", "Сложные типы эссе Task 2", "Двусоставные вопросы (Two-part) и Causes & Solutions.", "2 полных эссе Task 2", "Ответ на все части задания", "PEEL_EDITOR"),
        DayPlanItem(14, "Фаза 3: Симуляция", "Генеральный аудит экзаменатора", "Полная связка Task 1 + Task 2 за 60 минут с финальным AI-аудитом.", "1 полная связка (Task 1 + Task 2)", "Прогноз Band 6.5–7.0+", "PEEL_EDITOR")
    )

    // СПЕЦИАЛИЗИРОВАННЫЙ ТРЕНАЖЕР TASK 1: 18 ЗАДАНИЙ ПО ГРАФИКАМ, КАРТАМ, ТАБЛИЦАМ
    val TASK1_DRILLS = listOf(
        TaskOneDrillItem(
            "t1_01", "PREPOSITIONS", "Line Graph", "Предлог изменения (На сколько изменилось)",
            "The proportion of car owners rose ________ 15% between 2000 and 2010 (с 20% до 35%).",
            listOf("to", "by", "at", "with"), 1,
            "Предлог 'BY' показывает разницу / величину изменения (rose BY 15%). Предлог 'TO' указывает конечную точку."
        ),
        TaskOneDrillItem(
            "t1_02", "PREPOSITIONS", "Line Graph", "Предлог конечной точки (До какого уровня)",
            "Oil consumption peaked ________ 85 million barrels per day in 2015.",
            listOf("at", "by", "on", "in"), 0,
            "С глаголами peak, stand, start используется предлог 'AT' (peaked AT 85 million)."
        ),
        TaskOneDrillItem(
            "t1_03", "PREPOSITIONS", "Bar Chart", "Предлог уровня (Находился на уровне)",
            "In 2020, unemployment in Spain stood ________ approximately 14%.",
            listOf("at", "to", "by", "with"), 0,
            "Устойчивая связка описания данных: 'stood AT [число]'."
        ),
        TaskOneDrillItem(
            "t1_04", "OVERVIEW", "Pie Chart", "Правило чистого Overview",
            "Какой из вариантов Overview для круговых диаграмм расходов является академически верным?",
            listOf(
                "Overall, in 1950 housing was 72.1% while food was 11.2%, and in 2010 food rose to 34.0%.",
                "Overall, housing initially accounted for the lion's share of spending, but was later surpassed by food.",
                "Overall, we can see that all categories are changed very strongly in two charts."
            ), 1,
            "В блоке Overall КАТЕГОРИЧЕСКИ ЗАПРЕЩЕНЫ конкретные цифры и проценты! Описываются только общие тренды."
        ),
        TaskOneDrillItem(
            "t1_05", "OVERVIEW", "Line Graph", "Выделение ключевого тренда",
            "Выберите безупречный Overview для графика с 4 странами за 50 лет:",
            listOf(
                "Overall, while Country A saw an upward trend throughout the period, the figures for the remaining nations witnessed a steady decline.",
                "Overall, Country A grew by 45% from 10 tons to 55 tons, whereas Country B dropped by 12 tons.",
                "Overall, the line of Country A was very big and others was small."
            ), 0,
            "Идеальный академический контраст через while без единой цифры."
        ),
        TaskOneDrillItem(
            "t1_06", "COMPARISON", "Table", "Сравнение: в два раза выше",
            "The consumption of poultry in the UK was ________ that in Germany.",
            listOf("in twice bigger than", "twice as high as", "two times more higher than", "double bigger as"), 1,
            "Канон английского языка: 'twice as high as' или 'double that of'. Форма 'in twice bigger' — грубый рунглиш!"
        ),
        TaskOneDrillItem(
            "t1_07", "COMPARISON", "Bar Chart", "Превышение показателей (Глагол outstrip)",
            "By 2022, solar power generation had ________ nuclear power output.",
            listOf("surpassed and outstripped", "outstripped", "been more bigger than", "outgrew on"), 1,
            "Глагол 'outstripped' (опередил/превзошел) — академический маркер уровня Band 7.5+."
        ),
        TaskOneDrillItem(
            "t1_08", "TRENDS", "Line Graph", "Стремительное падение (Глагол-коллокация)",
            "Between June and August, coal exports ________ to a record low of 2 million tons.",
            listOf("plummeted", "dropped strongly down", "fell down heavily", "made a big decrease"), 0,
            "Академические глаголы резкого падения: plummeted, plunged. Избегайте разговорных fell down heavily."
        ),
        TaskOneDrillItem(
            "t1_09", "TRENDS", "Line Graph", "Колебания вокруг отметки",
            "Throughout the decade, wheat prices ________ between $200 and $250.",
            listOf("moved up and down", "fluctuated", "made many waves", "were waving"), 1,
            "Колебания цен/данных описываются глаголом 'fluctuated' (fluctuated between X and Y)."
        ),
        TaskOneDrillItem(
            "t1_10", "PROCESS_PASSIVE", "Process", "Пассивный залог в производстве цемента",
            "At the first stage, limestone and clay ________ in a massive crushing machine.",
            listOf("are crushed", "crush", "are crushing", "is crushed"), 0,
            "Подлежащее limestone and clay — множественное число, пассивный залог: 'are crushed' (be + V3)."
        ),
        TaskOneDrillItem(
            "t1_11", "PROCESS_PASSIVE", "Process", "Подача сырья в печь",
            "Next, the raw material ________ into a rotating kiln heated to 1500°C.",
            listOf("is fed", "feeds", "is feeding", "is feed"), 0,
            "Неправильный глагол feed -> fed -> fed. Пассив: 'is fed'."
        ),
        TaskOneDrillItem(
            "t1_12", "PROCESS_PASSIVE", "Map", "Снос старых построек на карте",
            "According to the blueprint, the old industrial warehouse ________ to make way for residential apartments.",
            listOf("will demolish", "will be demolished", "is demolishing", "will been demolished"), 1,
            "В картах планов будущего используется будущее пассивное: 'will be demolished'."
        ),
        TaskOneDrillItem(
            "t1_13", "PROCESS_PASSIVE", "Map", "Появление нового моста",
            "A pedestrian bridge ________ across the canal by 2030.",
            listOf("will construct", "will be constructed", "is going to construct", "constructed"), 1,
            "Мост не строит сам себя: 'will be constructed'."
        ),
        TaskOneDrillItem(
            "t1_14", "COMPARISON", "Table", "Минимальная доля (Negligible share)",
            "Expenditure on leisure accounted for a ________ proportion of the total budget (barely 1.5%).",
            listOf("very small", "negligible", "tiny-tiny", "little"), 1,
            "Академическое прилагательное для мизерных долей — 'negligible' (незначительный/пренебрежимо малый)."
        ),
        TaskOneDrillItem(
            "t1_15", "TRENDS", "Line Graph", "Выход на плато (Стабилизация)",
            "After five years of rapid expansion, the production levels ________ at 50,000 units.",
            listOf("stopped to grow", "plateaued", "made a pause", "placed the same"), 1,
            "Выход на горизонтальную прямую описывается глаголом 'plateaued' или фразой 'remained stable'."
        )
    )

    // МАССИВ ЧАСТЕЙ РЕЧИ: 30 КОРНЕЙ (МАКСИМАЛЬНОЕ РАСШИРЕНИЕ)
    val WORD_FAMILIES = listOf(
        WordFamilyItem("DOMINATE", "dominate", "dominance", "dominant", "Food became the ________ category in 2010.", listOf("dominate", "dominant", "dominance"), 1),
        WordFamilyItem("CONSUME", "consume", "consumption", "consumable", "The average ________ of beer was higher than that of juice.", listOf("consume", "consumption", "consumer"), 1),
        WordFamilyItem("DEPEND", "depend", "dependency", "dependent", "Renewable energy is heavily ________ on weather conditions.", listOf("depend", "dependent", "dependence"), 1),
        WordFamilyItem("PRODUCE", "produce", "production", "productive", "Energy is ________ by offshore wind installations.", listOf("produce", "product", "produced"), 2),
        WordFamilyItem("EXPAND", "expand", "expansion", "expansive", "The local council approved the ________ of the commercial zone.", listOf("expand", "expansion", "expansive"), 1),
        WordFamilyItem("SIGNIFICANT", "signify", "significance", "significant", "There was a ________ increase in household spending.", listOf("signify", "significant", "significance"), 1),
        WordFamilyItem("VARY", "vary", "variety", "various", "The map displays ________ facilities built in the northern area.", listOf("vary", "variety", "various"), 2),
        WordFamilyItem("DIFFER", "differ", "difference", "different", "There is a notable ________ between the two urban plans.", listOf("differ", "different", "difference"), 2),
        WordFamilyItem("POLLUTE", "pollute", "pollution", "pollutant", "Fossil fuels are the primary ________ affecting air quality.", listOf("pollute", "pollution", "pollutants"), 2),
        WordFamilyItem("INVEST", "invest", "investment", "investor", "Public transport requires substantial financial ________.", listOf("invest", "investment", "investor"), 1),
        WordFamilyItem("ECONOMY", "economize", "economy", "economic", "Rapid ________ growth often leads to higher carbon emissions.", listOf("economize", "economy", "economic"), 2),
        WordFamilyItem("INDUSTRY", "industrialize", "industry", "industrial", "The former ________ estate will be demolished for housing.", listOf("industry", "industrial", "industrialize"), 1),
        WordFamilyItem("TRANSFORM", "transform", "transformation", "transformative", "The town is undergoing a dramatic ________.", listOf("transform", "transformation", "transformative"), 1),
        WordFamilyItem("RELY", "rely", "reliance", "reliable", "Modern society has an excessive ________ on fossil fuels.", listOf("rely", "reliable", "reliance"), 2),
        WordFamilyItem("MOTIVATE", "motivate", "motivation", "motivated", "Material rewards can provide strong ________ for employees.", listOf("motivate", "motivation", "motivated"), 1),
        WordFamilyItem("ACQUIRE", "acquire", "acquisition", "acquisitive", "The desire for the ________ of luxury items continues to rise.", listOf("acquire", "acquisition", "acquisitive"), 1),
        WordFamilyItem("DEVELOP", "develop", "development", "developed", "Urbanization is a rapid ________ in Asian countries.", listOf("develop", "development", "developing"), 1),
        WordFamilyItem("CONCLUDE", "conclude", "conclusion", "conclusive", "In ________, the drawbacks of consumerism outweigh the benefits.", listOf("conclude", "conclusion", "conclusive"), 1),
        WordFamilyItem("INFLUENCE", "influence", "influence", "influential", "Social media is an extremely ________ factor in consumer habits.", listOf("influence", "influential", "influencing"), 1),
        WordFamilyItem("THREATEN", "threaten", "threat", "threatening", "Rising sea levels pose a severe ________ to coastal regions.", listOf("threaten", "threat", "threatening"), 1),
        WordFamilyItem("PREDICT", "predict", "prediction", "predictable", "The demographic changes were largely ________ by analysts.", listOf("predict", "predictable", "prediction"), 1),
        WordFamilyItem("BENEFIT", "benefit", "benefit", "beneficial", "Regular physical activity is highly ________ for mental health.", listOf("benefit", "beneficial", "benefiting"), 1),
        WordFamilyItem("COMPARE", "compare", "comparison", "comparative", "In ________ with 1950, spending on recreation doubled.", listOf("compare", "comparison", "comparable"), 1),
        WordFamilyItem("MAINTAIN", "maintain", "maintenance", "maintainable", "The council is responsible for the ________ of public parks.", listOf("maintain", "maintenance", "maintaining"), 1),
        WordFamilyItem("CONTRIBUTE", "contribute", "contribution", "contributory", "Heavy industry makes a massive ________ to greenhouse gases.", listOf("contribute", "contribution", "contributory"), 1),
        WordFamilyItem("STABILIZE", "stabilize", "stability", "stable", "By the fourth quarter, oil prices had remained ________.", listOf("stabilize", "stability", "stable"), 2),
        WordFamilyItem("ISOLATE", "isolate", "isolation", "isolated", "Remote work can lead to acute feelings of social ________.", listOf("isolate", "isolation", "isolated"), 1),
        WordFamilyItem("EXHAUST", "exhaust", "exhaustion", "exhausted", "Chronic overwork causes severe physical and mental ________.", listOf("exhaust", "exhaustion", "exhausting"), 1),
        WordFamilyItem("DETERIORATE", "deteriorate", "deterioration", "deteriorated", "There has been a marked ________ in public transport services.", listOf("deteriorate", "deterioration", "deteriorating"), 1),
        WordFamilyItem("PROPORTION", "proportion", "proportion", "proportionate", "A significant ________ of workers commute by train.", listOf("proportion", "proportionate", "proportionality"), 0)
    )

    // БАНК ТЕМ ДЛЯ ЭССЕ (15 ТЕМ: TASK 1 & TASK 2)
    val ESSAY_TOPICS = listOf(
        EssayTopic(2, "Technology & AI", "Artificial Intelligence vs Human Labor", "Some scientists believe that computers and artificial intelligence will soon become more intelligent than humans. To what extent do you agree or disagree?", "Use PEEL. Point: automation displacing white-collar jobs. Counterpoint: emotional intelligence remains human."),
        EssayTopic(2, "Consumerism & Society", "Luxury Brands & Conspicuous Consumption", "More and more people want to buy famous brands of clothes, cars, and other items. What are the reasons? Do you think it is a positive or negative development?", "Avoid 'strongly bad'. Use 'social validation', 'financial burden', 'status symbol'. Structure 4 sentences per body paragraph."),
        EssayTopic(2, "Environment", "Single-Use Plastics & Pollution", "Plastic waste has become a severe environmental crisis worldwide. What are the main causes of this problem, and what measures can governments and individuals take?", "Do not use 'emitter' for plastic. Use 'non-biodegradable waste', 'impose strict levies', 'subsidize biodegradable alternatives'."),
        EssayTopic(2, "Education", "Practical Skills vs Academic Theory", "Some people think that secondary schools should teach practical skills such as car maintenance and financial management alongside traditional academic subjects. Do you agree or disagree?", "Point: financial literacy prevents debt. Example: budgeting and tax preparation cultivate self-sufficiency."),
        EssayTopic(2, "Work & Health", "Work-Life Balance & Remote Work", "In many countries, working hours are increasing, and employees are experiencing chronic burnout. What are the consequences of this trend for individuals and society?", "Use 'mental fatigue', 'deteriorating physical health', 'erosion of social bonds'."),
        EssayTopic(2, "Global Culture", "Preservation of Traditional Customs", "Some believe globalization is destroying local traditions, while others think it enriches cultures. Discuss both views and give your opinion.", "Contrast homogenisation of pop culture with cross-cultural exchange."),
        EssayTopic(2, "Urban Planning", "Public Transport vs Private Vehicles", "Cities around the globe are facing crippling traffic congestion. Some advocate for free public transport as the primary solution. To what extent do you agree or disagree?", "Discuss economic feasibility for municipal budgets versus reduction of carbon emissions."),
        EssayTopic(2, "Government Spending", "Funding Space Exploration vs Earth Problems", "Billions of dollars are allocated annually to space exploration programs. Many argue this money should be redirected toward poverty and healthcare on Earth. Discuss both views.", "Compare technological spin-offs (satellites, medical imaging) with immediate human welfare needs."),
        EssayTopic(1, "Map Redevelopment", "Brandfield Town Planning", "The two maps show the proposed redevelopment of the town of Brandfield between the present day and 2030.", "STRICT: Zero numbers in Overview! Use passive voice: 'will be demolished', 'is projected to be converted into'."),
        EssayTopic(1, "Pie Chart", "Household Expenditure 1950 vs 2010", "The pie charts illustrate average household expenditure across various categories in a European country in 1950 and 2010.", "Overview must describe dominant categories without '%'. Use 'accounted for the lion's share', 'followed by', 'marginal proportions'."),
        EssayTopic(1, "Process Diagram", "Wave Energy Generation", "The diagram illustrates how energy is produced by ocean waves through an offshore installation.", "Strict passive: 'water is forced through', 'turbines are driven by air pressure', 'electricity is fed into the national grid'."),
        EssayTopic(1, "Bar Chart", "Beverage Consumption Patterns", "The bar chart compares the average consumption of beer, wine, and juice in five countries in 2020.", "Group by highest and lowest consumers. Do not repeat raw data points in Overview."),
        EssayTopic(1, "Line Graph", "Renewable Energy Adoption 1990-2025", "The line graph traces the percentage of electricity generated from renewable sources in four regions over a 35-year timeframe.", "Highlight upward trajectories and point of divergence. Use 'soared', 'stagnated', 'surpassed'."),
        EssayTopic(1, "Table", "University Enrollment by Discipline", "The table details the number of domestic and international students enrolled across six university faculties in Australia in 2023.", "Identify faculties with the greatest disparity between domestic and international cohorts.")
    )

    // ВНУТРЕННИЙ СЛОВАРЬ (30 СТАТЕЙ)
    val DICTIONARY = listOf(
        DictionaryEntry("STOP_LIST", "thresh", "trash / refuse / solid waste", "thresh означает обмолачивать зерно. Мусор — это trash или waste.", 6.0),
        DictionaryEntry("STOP_LIST", "emitter", "pollutant / culprit / emission source", "emitter — это источник выбросов, а не 'проблема' или 'виновник'.", 6.0),
        DictionaryEntry("STOP_LIST", "in twice bigger than", "twice as large as / double that of", "Калька с русского 'в два раза больше'. В английском используется twice as [adj] as.", 6.5),
        DictionaryEntry("STOP_LIST", "cannot to give", "cannot give", "Модальные глаголы (can, must, should) требуют голый инфинитив без частицы to.", 6.0),
        DictionaryEntry("STOP_LIST", "play opposite to us", "backfire / work against us", "Калька с русского 'сыграть против нас'. Академический глагол — backfire.", 7.0),
        DictionaryEntry("STOP_LIST", "strongly bad development", "severely detrimental trend", "Разговорное сочетание, снижающее балл LR до 5.0.", 7.0),
        DictionaryEntry("STOP_LIST", "a lot of money", "substantial financial resources", "a lot of — разговорное выражение, недопустимое в академическом эссе.", 6.5),
        DictionaryEntry("STOP_LIST", "really terrible", "alarming / grave concern", "Избегайте эмоциональных разговорных наречий.", 6.5),
        DictionaryEntry("STOP_LIST", "buu / smartphonw", "buy / smartphones", "Грубые механические опечатки клавиатуры.", 6.0),
        DictionaryEntry("TASK1_DATA", "rose on 10%", "rose by 10%", "Разница изменений в графиках всегда передается предлогом BY.", 6.5),
        DictionaryEntry("TASK1_DATA", "peaked to 50%", "peaked at 50%", "Пиковые точки (peaked, stood, started) оформляются предлогом AT.", 6.5),
        DictionaryEntry("TASK1_DATA", "became bigger than other", "outstripped the remaining categories", "Академический глагол опережения для таблиц и графиков.", 7.0),
        DictionaryEntry("WORD_FAMILY", "food became dominate", "became dominant", "После глаголов become/remain требуется прилагательное dominant.", 6.0),
        DictionaryEntry("WORD_FAMILY", "consume of beer", "consumption of beer", "consume — глагол. Существительное — consumption.", 6.0),
        DictionaryEntry("WORD_FAMILY", "is depend on", "depends on / is dependent on", "Либо активный глагол depends, либо связка + прилагательное dependent.", 6.5),
        DictionaryEntry("WORD_FAMILY", "produce by famous brand", "produced by a famous brand", "Причастие прошедшего времени (V3) в определительном обороте.", 6.5),
        DictionaryEntry("ACADEMIC_UPGRADE", "people want to buy things", "consumers are driven to acquire goods", "Апгрейд примитивной формулировки до академического уровня.", 7.5),
        DictionaryEntry("ACADEMIC_UPGRADE", "it need money", "this endeavor requires substantial funding", "Номинализация и повышение точности предикатов.", 7.5),
        DictionaryEntry("ACADEMIC_UPGRADE", "kids work on post office", "adolescents seek summer employment", "Замена бытовой лексики на официальный академический регистр.", 7.0),
        DictionaryEntry("ACADEMIC_UPGRADE", "good for health", "beneficial to physical well-being", "Замена бытового good на точную академическую коллокацию.", 7.0),
        DictionaryEntry("ACADEMIC_UPGRADE", "bad for nature", "detrimental to the ecosystem", "Академическая формулировка экологического ущерба.", 7.0),
        DictionaryEntry("CONNECTOR", "In spite of everything", "Nonetheless, / Despite these drawbacks,", "Академическая замена разговорного зачина.", 7.0),
        DictionaryEntry("CONNECTOR", "in result", "As a result, / Consequently,", "В английском нет фразы in result. Только As a result.", 6.0),
        DictionaryEntry("CONNECTOR", "Turning to the details,", "Regarding the specific data,", "Каноническая связка для перехода к абзацам деталей в Task 1.", 6.5),
        DictionaryEntry("CONNECTOR", "On the other hand,", "In stark contrast,", "Связка для введения противоположной точки зрения.", 6.5)
    )

    // РАСШИРЕННЫЙ БАНК ХИРУРГИИ: 40+ ПРЕДЛОЖЕНИЙ
    val SURGERY_DRILLS = listOf(
        // BAND 6.0
        MistakeEntity("s_601", 6.0, "GRA_MISSING_TO_BE", "This certain model so expansive for simple people.", "This certain model is so expensive for average consumers.", "В английском предложении обязателен глагол-связка 'is' перед предикативом/прилагательным."),
        MistakeEntity("s_602", 6.0, "GRA_MODAL_TO", "Governments cannot to solve this problem alone.", "Governments cannot solve this problem alone.", "После модального глагола cannot частица to категорически запрещена."),
        MistakeEntity("s_603", 6.0, "GRA_3RD_PERSON_S", "The chart demonstrate that housing consume the biggest part.", "The chart demonstrates that housing consumes the largest share.", "Подлежащие в 3-м лице единственного числа требуют окончания -s у сказуемых."),
        MistakeEntity("s_604", 6.0, "GRA_VERB_CONFUSION", "Many young individuals are study hard for to pass exams.", "Many young individuals study hard in order to pass exams.", "Паразитная связка 'are study' заменяется на чистый Present Simple 'study'."),
        MistakeEntity("s_605", 6.0, "GRA_PASSIVE", "Energy is generate by wave in this installation.", "Energy is generated by waves in this installation.", "Пассивный залог строится по строгой формуле: to be + V3 (is generated)."),
        MistakeEntity("s_606", 6.0, "LR_STOP_LIST", "People throw a lot of thresh into ocean.", "People discard substantial amounts of waste into the ocean.", "thresh — фонетическая ошибка. Заменяется на waste / refuse."),
        MistakeEntity("s_607", 6.0, "GRA_MISSING_TO_BE", "Saving the nature also our responsibility as society.", "Saving nature is also our responsibility as a society.", "Пропуск связки is после герундиального подлежащего Saving nature."),
        MistakeEntity("s_608", 6.0, "GRA_MODAL_TO", "Students should to choose practical skills.", "Students should choose practical skills.", "После модального глагола should используется голый инфинитив без to."),
        MistakeEntity("s_609", 6.0, "GRA_3RD_PERSON_S", "The government need to invest money in hospitals.", "The government needs to invest money in hospitals.", "Government (3-е лицо ед. ч.) требует окончания -s у глагола needs."),
        MistakeEntity("s_610", 6.0, "GRA_VERB_CONFUSION", "Teenagers are work on post office in summer.", "Teenagers work at the post office during summer.", "Паразитная связка are work заменяется на обычное сказуемое work."),

        // BAND 6.5
        MistakeEntity("s_651", 6.5, "GRA_GERUND", "In 2010 housing stopped be the biggest expenditures in country.", "In 2010 housing stopped being the biggest expenditure in the country.", "Глагол stop требует герундия (being), превосходная степень требует 'the', а expenditure — ед. числа."),
        MistakeEntity("s_652", 6.5, "GRA_CONDITIONAL_FRAGMENT", "If students want to buy laptop but lack money, and they work.", "If students want to buy a laptop but lack money, they seek employment.", "Союз and разрушает условное предложение, превращая его в бессмысленный обрубок."),
        MistakeEntity("s_653", 6.5, "LR_WORD_FAMILY", "Renewable energy became dominate source in recent years.", "Renewable energy became the dominant source in recent years.", "dominate — глагол. В роли определения к слову source требуется прилагательное dominant."),
        MistakeEntity("s_654", 6.5, "GRA_COMPARISON", "The second category was in twice bigger than first category.", "The second category was twice as large as the first one.", "Нелегитимная калька 'in twice bigger'. Правильно: twice as large as или double that of."),
        MistakeEntity("s_655", 6.5, "LR_COLLOCATION", "This situation is strongly bad development for modern society.", "This situation is a highly negative development for modern society.", "Коллокация strongly bad нарушает академический регистр. Заменяется на highly negative."),
        MistakeEntity("s_656", 6.5, "GRA_PASSIVE_FUTURE", "The old industrial zone will demolish and replace with houses.", "The old industrial zone will be demolished and replaced with houses.", "В будущем времени пассивный залог строится как will be + V3."),
        MistakeEntity("s_657", 6.5, "GRA_PLURAL_DETERMINER", "The trend shows higher growth than other part of city.", "The trend shows higher growth than other parts of the city.", "После слова other исчисляемые существительные обязаны стоять во множественном числе."),
        MistakeEntity("s_658", 6.5, "GRA_STATIVE_VERB", "When people are wanting new gadgets, they spend savings.", "When people want new gadgets, they spend their savings.", "Глагол want является глаголом состояния и не употребляется в Continuous."),
        MistakeEntity("s_659", 6.5, "LR_ACADEMIC_FORM", "In result people psychological health starts rapidly get bad.", "As a result, people's psychological health starts rapidly deteriorating.", "Замена in result на As a result, апостроф people's и академическое deteriorating."),
        MistakeEntity("s_660", 6.5, "GRA_PREPOSITION_CLAUSE", "Due to that fact they study, they can not work full time.", "Because they are studying, they cannot work full-time.", "Громоздкая калька due to that fact they заменяется на лаконичное Because."),

        // BAND 7.0+
        MistakeEntity("s_701", 7.0, "GRA_CONCESSION_IDIOM", "This demonstrate useful but later I demonstrate this dream can play opposite.", "Although such goals may seem beneficial, this pursuit can ultimately backfire.", "Устранение анонсов от первого лица, внедрение союза Although и идиомы backfire."),
        MistakeEntity("s_702", 7.0, "GRA_NOMINALIZATION", "Because people want famous brands, it make them work harder.", "The desire for prestigious brands incentivizes individuals to work more diligently.", "Номинализация: замена детского 'because people want' на плотную именную группу 'the desire for...'."),
        MistakeEntity("s_703", 7.0, "GRA_HEDGING", "Overwork always completely destroy human health.", "Chronic overwork tends to severely undermine personal well-being.", "Академический хеджинг: замена категоричного 'always completely destroy' на сдержанное 'tends to severely undermine'."),
        MistakeEntity("s_704", 7.0, "GRA_PARTICIPLE_CLAUSE", "Pollution increases rapidly and it cause severe diseases.", "Pollution has increased rapidly, resulting in severe health repercussions.", "Причастный оборот resulting in вместо примитивной склейки через союз and."),
        MistakeEntity("s_705", 7.0, "GRA_CONCESSION_INVERSION", "Even though the technology is expensive, but it is necessary.", "Expensive though this technology may be, it remains indispensable.", "Сложная уступительная инверсия для демонстрации грамматического диапазона Band 8.0.")
    )
}
