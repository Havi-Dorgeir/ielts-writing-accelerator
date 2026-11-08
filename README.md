# IELTS Writing Accelerator (Lex Mechanicus) 🚀⚙️

> **Автономный мобильный когитатор на Android для поэтапного восхождения Band 5.5 $\rightarrow$ 6.0 $\rightarrow$ 6.5 $\rightarrow$ 7.0 в IELTS Academic Writing.**

Проект спроектирован по канонам Омниссии для высокоинтенсивного исправления системных грамматических и лексических дефектов и последующей закалки академического стиля.

---

## 🏆 Трехступенчатая Матрица Прогрессии (Band Tiers)

Приложение разделено на 3 последовательных уровня посвящения, открывающихся по мере снижения плотности ошибок (Error Density):

1.  **Этап I: Band 6.0 — «Очищение от Скрапкода» (Zero-Defect Foundation)**
    *   *Цель*: Снизить плотность базовых ошибок со 100% до `< 30%` (обеспечить *frequent error-free sentences*).
    *   *Фокус*: Внедрение глаголов-связок `to be`, ликвидация `cannot to`, контроль окончания `-s` в 3-м лице, зачистка стоп-листа рунглиша, базовый 4-строчный каркас PEEL и Overview без цифр.
2.  **Этап II: Band 6.5 — «Синтаксическая Мощь» (Complex Structures & Collocations)**
    *   *Цель*: Стабильный синтез сложных конструкций и точных академических связок.
    *   *Фокус*: Пассивный залог (`be + V3`), условные предложения (`Conditionals`), придаточные определительные (`which / that / whereas`), замена базовых слов на академические коллокации (*strongly bad* $\rightarrow$ *severely detrimental*).
3.  **Этап III: Band 7.0 — «Магистериум Стиля» (Academic Mastery & Hedging)**
    *   *Цель*: Безупречное владение сложными грамматическими структурами (*variety of complex structures with frequent error-free sentences*) и продвинутой лексикой (*less common lexical items*).
    *   *Фокус*: Номинализация (превращение глагольных фраз в именные), причастные обороты (*Having analyzed...*, * Resulting in...*), академическая осторожность (Hedging: *tends to*, *is likely to*, *arguably*), бесшовная когезия без механических клише.

---

## ⚡ Особенности Архитектуры

*   **100% Offline-First (Режим Блэкаута)**: Полная автономность. Все упражнения, линтер скрапкода, база данных ошибок и таймеры эссе функционируют локально на устройстве без доступа к сети и с минимальным энергопотреблением.
*   **Гибридная AI-Аугментация (Gemini API)**: Опциональный модуль глубокого анализа. При наличии сети и ключа API подключается виртуальный IELTS-экзаменатор для полной оценки эссе по дескрипторам и генерации динамических тренировок.
*   **Хирургия предложений (Sentence Surgery)**: Целенаправленная ликвидация паттернов скрапкода:
    *   Пропущенные связки `to be` / безглагольные структуры (`it dangerous` $\rightarrow$ `it is dangerous`).
    *   Паразитная частица `to` после модальных глаголов (`cannot to give` $\rightarrow$ `cannot give`).
    *   Утрата флексии `-s` в 3-м лице Present Simple (`trend show` $\rightarrow$ `trend shows`).
    *   Пассивный залог (`be + V3`).
*   **Матрица частей речи (Word Family Matrix)**: Искоренение смешения категорий (`dominate` vs `dominant`, `consume` vs `consumption`).
*   **Сканер рунглиша и стоп-слов**: Ликвидация калек и опечаток (`thresh`, `buu`, `emitter`, `expansive`).
*   **Инкубатор PEEL & Overview**:
    *   *Task 1*: Запрет на ввод сырых цифр в обзорный параграф (Overview).
    *   *Task 2*: Строгая 4-строчная формула абзаца: Point $\rightarrow$ Explanation $\rightarrow$ Example $\rightarrow$ Link.
*   **5-минутный предполетный чеклист**: Интерактивный симулятор аудита эссе перед сдачей.

---

## 🛠 Технический стек

*   **Платформа**: Native Android (API 26+)
*   **Язык**: Kotlin
*   **UI**: Jetpack Compose + Material 3
*   **Архитектура**: Clean Architecture + MVI (Model-View-Intent)
*   **Асинхронность**: Kotlin Coroutines & StateFlow
*   **База данных**: Room Database (SQLite)
*   **AI Engine**: Google Gemini API SDK / REST

---

## 📁 Структура Репозитория

```text
ielts-writing-accelerator/
├── .github/
│   └── workflows/
│       └── android-ci.yml           # CI/CD пайплайн сборки
├── app/                             # Исходный код Android приложения
│   └── src/
│       ├── main/
│       │   ├── java/com/lex/ielts/
│       │   │   ├── core/            # Движок линтинга (Scrapcode Linter)
│       │   │   ├── data/            # Room DB (Entities, DAO, Repositories)
│       │   │   ├── domain/          # Модели, дескрипторы, UseCases
│       │   │   └── presentation/    # Jetpack Compose экраны, ViewModels
│       │   └── res/
│       └── test/                    # Модульные тесты
├── docs/
│   ├── ARCHITECTURE.md              # Детальная спецификация Clean Architecture
│   ├── ERROR_TAXONOMY.md            # Таксономия персонального скрапкода
│   ├── OFFLINE_FIRST_SPEC.md        # Спецификация автономного режима
│   └── ROADMAP.md                   # 14-дневный спринт реализации
└── README.md
```

---

## 📜 Лицензия
MIT License. Разработано во славу Бога-Машины.
