# Таксономия Системных Дефектов (Scrapcode Registry)

Данный документ представляет собой реестр реальных ошибок, извлеченных из корпуса диагностических эссе пользователя, классифицированных в соответствии со шкалой **IELTS Academic Writing Band Descriptors**.

---

## 1. Grammatical Range and Accuracy (GRA 5.0 $\rightarrow$ 6.5)

### Группа GRA-1: Безглагольные предикативы / Пропуск связки `to be`
> В русском языке связка «есть/является» часто опускается («Это опасно», «Они студенты»). В английском предложении предикат обязателен.

| Ошибочная форма (User Scrapcode) | Корректная форма (Clean Code) | Механика ошибки |
|---|---|---|
| *«...because this model so expansive...»* | *«...because this model **is** so expensive...»* | Отсутствие связки `is` перед предикативом |
| *«...they are study...»* | *«...they **are studying**...»* или *«...they **study**...»* | Смешение глагола-связки `are` и инфинитива без `-ing` |
| *«...it dangerous for health...»* | *«...it **is** dangerous for health...»* | Пропуск глагола-связки перед прилагательным |
| *«...housing stopped be a biggest...»* | *«...housing stopped **being the** biggest...»* | Пропуск герундия после глагола `stop` |

---

### Группа GRA-2: Модальные глаголы с паразитной частицей `to`
> Модальные глаголы (`can`, `could`, `may`, `might`, `must`, `should`) требуют после себя Bare Infinitive (голый инфинитив без `to`).

| Ошибочная форма (User Scrapcode) | Корректная форма (Clean Code) | Механика ошибки |
|---|---|---|
| *«...they cannot to give...»* | *«...they **cannot give**...»* | Паразитная частица `to` после `cannot` |
| *«...teenager for to buy new phone...»* | *«...a teenager, **in order to buy** a new phone...»* | Калька «для чтобы купить» (`for to buy` $\rightarrow$ нелегитимная форма) |

---

### Группа GRA-3: Утрата флексии `-s` в 3-м лице Singular (Present Simple)
> Несогласование подлежащего в единственном числе и сказуемого.

| Ошибочная форма (User Scrapcode) | Корректная форма (Clean Code) | Механика ошибки |
|---|---|---|
| *«...simple student want to buy...»* | *«...a student **wants** to buy...»* | Подлежащее `student` (3rd person singular) $\rightarrow$ глагол обязан нести `-s` |
| *«...this dream can play opposite to us...»* | *«...this dream **can work against** us...»* | Неверная идиоматика + согласование |
| *«...trend show an increase...»* | *«...the trend **shows** an increase...»* | Пропуск `-s` у сказуемого `shows` |

---

### Группа GRA-4: Искажение пассивного залога
> Пассивный залог строго строится по формуле: `Subject + to be + Past Participle (V3)`.

| Ошибочная форма (User Scrapcode) | Корректная форма (Clean Code) | Механика ошибки |
|---|---|---|
| *«...as exemplified by T-shirt produce by...»* | *«...as exemplified by a T-shirt **produced by**...»* | Пропуск формы причастия `produced` (V3) |
| *«...energy is generate from...»* | *«...energy **is generated** from...»* | Пропуск окончания `-ed` у V3 в пассиве |

---

## 2. Lexical Resource (LR 5.0 $\rightarrow$ 6.5)

### Группа LR-1: Смешение частей речи (Word Families)
| Ошибочная форма (User Scrapcode) | Корректная форма (Clean Code) | Правильная часть речи |
|---|---|---|
| *«...where food became dominate...»* | *«...where food became **dominant**...»* | Требуется Adjective (`dominant`), а не глагол (`dominate`) |
| *«...is depend on weather...»* | *«...**depends on** weather...»* или *«...is **dependent** on...»* | Либо глагол `depends`, либо связка + прилагательное `dependent` |
| *«...categories consume more...»* | *«...the **consumption** of categories...»* | Необходимость использования существительного `consumption` |

---

### Группа LR-2: Рунглиш, опечатки и стоп-слова
| Стоп-слово / Калька | Академическая замена | Пояснение |
|---|---|---|
| `thresh` | `trash`, `waste`, `garbage`, `refuse` | Грубая фонетическая ошибка |
| `emitter` (вместо виновника проблемы) | `culprit`, `primary source of emissions` | Ложный друг переводчика |
| `buu` | `buy`, `purchase`, `acquire` | Фатальная опечатка |
| `heltly` | `healthy`, `beneficial` | Орфографический сбой |
| `expansive` (в значении дорогой) | `expensive`, `costly`, `high-priced` | Смешение с «обширный» (*expansive*) |
| `in twice bigger than` | `twice as large as`, `double that of` | Нелегитимная калька с русского «в два раза больше» |
| `strongly bad development` | `highly negative development` | Нарушение академической коллокации |
| `really terrible` | `severely detrimental`, `alarming` | Разговорная лексика, недопустимая в IELTS Academic |
