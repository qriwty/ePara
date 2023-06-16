package com.ebunnygroup.epara.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ebunnygroup.epara.ui.common.ScreenContent
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
fun DashboardScreen(screenName: String, previousScreen: String?, onNextScreenClick: () -> Unit) {
    ScreenContent(screenName, previousScreen, onNextScreenClick)

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
        )
    )
}

@Preview
@Composable
fun DashboardScreenPreview() {
//    DashboardScreen()
}