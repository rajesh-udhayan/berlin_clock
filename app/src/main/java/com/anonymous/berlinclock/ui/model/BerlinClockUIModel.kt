package com.anonymous.berlinclock.ui.model

import androidx.compose.ui.graphics.Color
import com.anonymous.berlinclock.ui.model.LampState.*
import com.anonymous.berlinclock.ui.theme.redDisabled
import com.anonymous.berlinclock.ui.theme.redEnabled
import com.anonymous.berlinclock.ui.theme.yellowDisabled
import com.anonymous.berlinclock.ui.theme.yellowEnabled
import com.anonymous.berlinclock.utils.BOTTOM_HOURS_LAMP_COUNT
import com.anonymous.berlinclock.utils.BOTTOM_MINUTES_LAMP_COUNT
import com.anonymous.berlinclock.utils.TOP_HOURS_LAMP_COUNT
import com.anonymous.berlinclock.utils.TOP_MINUTES_LAMP_COUNT

data class BerlinClockUIModel(
    val secondsLampState: LampState,
    val topMinutesLampState: List<LampState>,
    val bottomMinutesLampState: List<LampState>,
    val topHoursLampState: List<LampState>,
    val bottomHoursLampState: List<LampState>
) {
    companion object {
        fun initialState(): BerlinClockUIModel {
            return BerlinClockUIModel(
                OFF,
                MutableList(TOP_MINUTES_LAMP_COUNT) { OFF },
                MutableList(BOTTOM_MINUTES_LAMP_COUNT) { OFF },
                MutableList(TOP_HOURS_LAMP_COUNT) { OFF },
                MutableList(BOTTOM_HOURS_LAMP_COUNT) { OFF })
        }
    }

    fun getSecondsLampColor(): Color {
        return if (secondsLampState != OFF) yellowEnabled else yellowDisabled
    }

    fun getTopHoursLampColor(index: Int): Color {
        return if (topHoursLampState[index] != OFF) redEnabled else redDisabled
    }

    fun getBottomHoursLampColor(index: Int): Color {
        return if (bottomHoursLampState[index] != OFF) redEnabled else redDisabled
    }

    fun getTopMinutesLampColor(index: Int): Color {
        return if (index == 2 || index == 5 || index == 8) {
            if (topMinutesLampState[index] != OFF) redEnabled else redDisabled
        } else {
            if (topMinutesLampState[index] != OFF) yellowEnabled else yellowDisabled
        }
    }

    fun getBottomMinutesLampColor(index: Int): Color {
        return if (bottomMinutesLampState[index] != OFF) redEnabled else redDisabled
    }
}

enum class LampState {
    YELLOW,
    OFF,
    RED
}
