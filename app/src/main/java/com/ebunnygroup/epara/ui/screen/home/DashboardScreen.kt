package com.ebunnygroup.epara.ui.screen.home

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebunnygroup.epara.ui.kalendar.Kalendar
import com.ebunnygroup.epara.ui.kalendar.KalendarEvent
import com.ebunnygroup.epara.ui.kalendar.KalendarEvents
import com.ebunnygroup.epara.ui.kalendar.KalendarType
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


data class Lesson(
    val name: String,
    val start_time: String,
    val end_time: String,
    val teacher: String,
    val lesson_type: String
)

@Composable
fun loadJsonFile(context: Context, fileName: String): String? {
    val jsonString: String

    try {
        val inputStream = context.assets.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        jsonString = bufferedReader.use { it.readText() }
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    return jsonString
}

fun getWeekendDatesInRange(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    var currentDate = startDate

    while (currentDate <= endDate) {
        if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
            dates.add(currentDate)
        }
        currentDate = currentDate.plus(1, DateTimeUnit.DAY)
    }

    return dates
}

val startDate = LocalDate.parse("2023-06-01")
val endDate = LocalDate.parse("2023-07-01")
val weekendDates = getWeekendDatesInRange(startDate, endDate)

@Composable
fun DashboardScreen() {
    val selectedDay = remember {
        mutableStateOf(Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ))
    }

    val jsonString = loadJsonFile(context = LocalContext.current, fileName = "timetable.json")
    val gson = Gson()
    val lessonDataType = object : TypeToken<Map<String, List<Lesson>>>() {}.type
    val lessonDataMap: Map<String, List<Lesson>> = gson.fromJson(jsonString, lessonDataType)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Kalendar(
            currentDay = Clock.System.todayIn(
                TimeZone.currentSystemDefault()
            ),
            exceptionDays = weekendDates,
            kalendarType = KalendarType.Oceanic,
            events = KalendarEvents(
                listOf(
                    KalendarEvent(
                        date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                        eventName = "Test Event"
                    ),
                    KalendarEvent(
                        date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                        eventName = "Test Event"
                    ),
                    KalendarEvent(
                        date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                        eventName = "Test Event"
                    ),
                    KalendarEvent(
                        date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                        eventName = "Test Event"
                    ),
                    KalendarEvent(
                        date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                        eventName = "Test Event"
                    ),
                )
            ),
            onDayClick = { date, _ ->
                selectedDay.value = date
            }
        )

        Box(Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = "Date: ${selectedDay.value}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )

                lessonDataMap[selectedDay.value.toString()]?.forEach {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Lesson Time: ${it.start_time} - ${it.end_time}",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Text(
                            text = "Lesson Type: ${it.lesson_type}",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Text(
                            text = "Lesson Name: ${it.name}",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Text(
                            text = "Teacher: ${it.teacher}",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }

                }

            }
        }
    }

}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}