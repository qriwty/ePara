package com.ebunnygroup.epara.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ebunnygroup.epara.ui.common.ScreenContent
import com.ebunnygroup.epara.ui.kalendar.Kalendar
import com.ebunnygroup.epara.ui.kalendar.KalendarEvent
import com.ebunnygroup.epara.ui.kalendar.KalendarEvents
import com.ebunnygroup.epara.ui.kalendar.KalendarType
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@Composable
fun DashboardScreen(screenName: String, previousScreen: String?, onNextScreenClick: () -> Unit) {
    ScreenContent(screenName, previousScreen, onNextScreenClick)

    Kalendar(
        currentDay = Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ), 
        kalendarType = KalendarType.Oceanic,
        events = KalendarEvents(
            listOf(
                KalendarEvent(
                    date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                    eventName = "Test Event"
                )
            )
        )
    )


}

@Preview
@Composable
fun DashboardScreenPreview() {
//    DashboardScreen()
}