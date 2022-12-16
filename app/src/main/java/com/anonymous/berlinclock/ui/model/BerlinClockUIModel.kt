package com.anonymous.berlinclock.ui.model

import com.anonymous.berlinclock.ui.model.LampState.RED_DISABLED
import com.anonymous.berlinclock.ui.model.LampState.YELLOW_DISABLED
import com.anonymous.berlinclock.utils.*

data class BerlinClockUIModel(
    val secondsLampState: LampState,
    val topMinutesLampState: List<LampState>,
    val bottomMinutesLampState: List<LampState>,
    val topHoursLampState: List<LampState>,
    val bottomHoursLampState: List<LampState>,
    val time: String
) {
    companion object {
        fun initialState(): BerlinClockUIModel {
            return BerlinClockUIModel(
                YELLOW_DISABLED,
                MutableList(TOTAL_TOP_MINUTES_LAMP_COUNT) { index ->
                    if (index == 2 || index == 5 || index == 8) {
                        RED_DISABLED
                    } else {
                        YELLOW_DISABLED
                    }
                },
                MutableList(TOTAL_BOTTOM_MINUTES_LAMP_COUNT) { YELLOW_DISABLED },
                MutableList(TOTAL_TOP_HOURS_LAMP_COUNT) { RED_DISABLED },
                MutableList(TOTAL_BOTTOM_HOURS_LAMP_COUNT) { RED_DISABLED },
                INITIAL_TIME
            )
        }
    }
}