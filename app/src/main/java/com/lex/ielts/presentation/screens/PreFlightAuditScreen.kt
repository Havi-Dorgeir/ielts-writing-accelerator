package com.lex.ielts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AuditStep(
    val id: Int,
    val title: String,
    val description: String,
    val checkTarget: String
)

/**
 * Экран «5-Minute Pre-Flight Debugger» (Предполетный аудит эссе).
 * Симулирует последние 5 минут перед нажатием кнопки Submit на реальном экзамене IELTS.
 */
@Composable
fun PreFlightAuditScreen(
    onAuditComplete: () -> Unit
) {
    val steps = remember {
        listOf(
            AuditStep(
                id = 1,
                title = "Pass 1: Инспекция Сказуемых (Verbs Audit)",
                description = "Проверено ли каждое предложение на наличие сказуемого? Нет ли пропущенных 'to be' (is/are) перед предикативами?",
                checkTarget = "0 безглагольных предложений"
            ),
            AuditStep(
                id = 2,
                title = "Pass 2: Модальные Связки и Инфинитивы",
                description = "Проверены ли все модальные глаголы (can, cannot, should, must)? Удалены ли паразитные 'to' и случайные окончания '-s' (can seem, a wish to get)?",
                checkTarget = "Bare infinitive после модалов и 'to + V1' у инфинитива"
            ),
            AuditStep(
                id = 3,
                title = "Pass 3: Фильтрация Стоп-Слов & Опечаток",
                description = "Вычищены ли рунглиш-кальки (thresh, emitter, in twice bigger) и опечатки (smartphonw, buu, expansive)?",
                checkTarget = "0 слов из персонального Stop-List"
            ),
            AuditStep(
                id = 4,
                title = "Pass 4: Валидация Структуры (Task 1/2)",
                description = "Task 1: Overview свободен от цифр и процентов? Task 2: В каждом Body Paragraph ровно 4 предложения PEEL?",
                checkTarget = "100% соответствие формуле структуры"
            )
        )
    }

    var checkedSteps by remember { mutableStateOf(setOf<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text(
            text = "ПРЕДПОЛЕТНЫЙ 5-МИНУТНЫЙ ДЕБАГГЕР",
            color = Color(0xFF00E676),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Обряд очищения логических цепей перед сдачей эссе экзаменаторам.",
            color = Color.LightGray,
            fontSize = 13.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(steps) { step ->
                val isChecked = checkedSteps.contains(step.id)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isChecked) Color(0xFF1B2E20) else Color(0xFF1E1E1E)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                checkedSteps = if (checked) checkedSteps + step.id else checkedSteps - step.id
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF00E676),
                                uncheckedColor = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = step.title, color = Color.White, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = step.description, color = Color.LightGray, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Критерий: ${step.checkTarget}", color = Color(0xFF80D8FF), fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (checkedSteps.size == steps.size) {
                    onAuditComplete()
                }
            },
            enabled = checkedSteps.size == steps.size,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00E676),
                disabledContainerColor = Color(0xFF333333)
            )
        ) {
            Text(
                if (checkedSteps.size == steps.size) "ПОЛНЫЙ ПРЕДПОЛЕТНЫЙ АУДИТ ПРОЙДЕН" else "ВЫПОЛНИТЕ ВСЕ 4 ПУНКТА АУДИТА",
                color = if (checkedSteps.size == steps.size) Color.Black else Color.Gray
            )
        }
    }
}
