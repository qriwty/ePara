package com.ebunnygroup.epara.ui.kalendar.ui.component.header

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Configuration data class for styling the Kalendar header text.
 * @param kalendarTextColor The color of the header text.
 * @param kalendarTextSize The size of the header text.
 */
@Immutable
data class KalendarTextKonfig(
    val kalendarTextColor: Color,
    val kalendarTextSize: TextUnit
) {
    companion object {
        /**
         * Creates a default configuration with the specified text color.
         * @param color The color of the header text.
         * @return The default configuration.
         */
        internal fun default(color: Color) = KalendarTextKonfig(
            kalendarTextColor = color,
            kalendarTextSize = 24.sp
        )

        /**
         * Creates a preview default configuration for previewing purposes.
         * @return The preview default configuration.
         */
        @SuppressWarnings("MagicNumber")
        internal fun previewDefault() = KalendarTextKonfig(
            kalendarTextSize = 24.sp,
            kalendarTextColor = Color(0xFFD2827A)
        )
    }
}
