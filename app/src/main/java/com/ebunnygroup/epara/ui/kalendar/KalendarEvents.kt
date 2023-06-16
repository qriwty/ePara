package com.ebunnygroup.epara.ui.kalendar

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

/**
 * Represents a calendar event.
 *
 * @param date The date of the event.
 * @param eventName The name or title of the event.
 * @param eventDescription The description or additional details of the event (optional).
 */
@Immutable
data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null
)

/**
 * Represents a collection of calendar events.
 *
 * @param events The list of calendar events.
 */
@Immutable
data class KalendarEvents(
    val events: List<KalendarEvent> = emptyList()
)
