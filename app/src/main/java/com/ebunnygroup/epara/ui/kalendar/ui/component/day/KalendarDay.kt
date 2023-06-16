package com.ebunnygroup.epara.ui.kalendar.ui.component.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebunnygroup.epara.ui.kalendar.KalendarEvent
import com.ebunnygroup.epara.ui.kalendar.KalendarEvents
import com.ebunnygroup.epara.ui.kalendar.color.KalendarColor
import com.ebunnygroup.epara.ui.kalendar.ui.component.day.modifier.circleLayout
import com.ebunnygroup.epara.ui.kalendar.ui.component.day.modifier.dayBackgroundColor
import com.ebunnygroup.epara.ui.kalendar.ui.component.indicator.KalendarIndicator
import com.ebunnygroup.epara.ui.kalendar.ui.firey.KalendarSelectedDayRange
import com.ebunnygroup.epara.ui.kalendar.util.MultiplePreviews
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

/**
 * A composable representing a single day in the Kalendar.
 *
 * @param date The date corresponding to the day.
 * @param kalendarColors The colors used for styling the Kalendar.
 * @param onDayClick The callback function invoked when the day is clicked.
 * @param selectedRange The selected date range in the Kalendar.
 * @param modifier The modifier to be applied to the composable.
 * @param selectedDate The currently selected date.
 * @param kalendarEvents The events associated with the Kalendar.
 * @param kalendarDayKonfig The configuration for the Kalendar day.
 */
@Composable
fun KalendarDay(
    date: LocalDate,
    kalendarColors: KalendarColor,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit,
    selectedRange: KalendarSelectedDayRange?,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = date,
    positiveSelection: Boolean = true,
    kalendarEvents: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
) {
    val selected = selectedDate == date
    val currentDay = Clock.System.todayIn(TimeZone.currentSystemDefault()) == date

    Column(
        modifier = modifier
            .border(
                border = getBorder(currentDay, kalendarDayKonfig.borderColor, selected),
                shape = RoundedCornerShape(37)
            )
            .clip(shape = RoundedCornerShape(37))
            .clickable { onDayClick(date, kalendarEvents.events) }
            .dayBackgroundColor(
                selected,
                positiveSelection,
                kalendarColors.dayBackgroundColor,
                date,
                selectedRange
            )
            .circleLayout()
            .wrapContentSize()
            .defaultMinSize(kalendarDayKonfig.size),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            fontSize = kalendarDayKonfig.textSize,
            color = if (selected) kalendarDayKonfig.selectedTextColor else kalendarDayKonfig.textColor,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold
        )
        Row {
            kalendarEvents.events
                .filter { it.date == date }
                .take(5)
                .forEachIndexed { index, _ ->
                    Row {
                        KalendarIndicator(
                            modifier = Modifier,
                            index = index,
                            size = kalendarDayKonfig.size,
                            color = kalendarColors.headerTextColor,
                            divider = 5
                        )
                    }
                }
        }
    }
}

/**
 * Returns the border stroke based on the current day, color, and selected state.
 *
 * @param currentDay Whether the day is the current day.
 * @param color The color of the border.
 * @param selected Whether the day is selected.
 *
 * @return The border stroke to be applied.
 */
private fun getBorder(currentDay: Boolean, color: Color, selected: Boolean): BorderStroke {
    val emptyBorder = BorderStroke(0.dp, Color.Transparent)
    return if (currentDay && selected.not()) {
        BorderStroke(1.dp, color)
    } else {
        emptyBorder
    }
}

@MultiplePreviews
@Composable
private fun KalendarDayPreview() {
    val date = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val previous =
        Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(1, DateTimeUnit.DAY)
    val events = (0..7).map {
        KalendarEvent(
            date = date,
            eventName = it.toString(),
        )
    }
    Row {
        KalendarDay(
            date = date,
            kalendarColors = KalendarColor.previewDefault(),
            onDayClick = { _, _ -> },
            selectedDate = date,
            positiveSelection = true,
            kalendarEvents = KalendarEvents(events),
            selectedRange = null
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(
            date = date.plus(1, DateTimeUnit.DAY),
            kalendarColors = KalendarColor.previewDefault(),
            onDayClick = { _, _ -> },
            selectedDate = date.plus(1, DateTimeUnit.DAY),
            positiveSelection = false,
            kalendarEvents = KalendarEvents(events),
            selectedRange = null
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(
            date = date,
            kalendarColors = KalendarColor.previewDefault(),
            onDayClick = { _, _ -> },
            selectedDate = previous,
            kalendarEvents = KalendarEvents(events),
            selectedRange = null
        )
    }
}
