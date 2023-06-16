package com.ebunnygroup.epara.ui.kalendar

/**
 * Represents the type of a calendar.
 */
sealed interface KalendarType {
    /**
     * Firey calendar type.
     */
    object Firey : KalendarType

    /**
     * Oceanic calendar type.
     */
    object Oceanic : KalendarType
}
