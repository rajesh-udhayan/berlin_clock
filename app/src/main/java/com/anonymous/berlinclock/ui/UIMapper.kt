package com.anonymous.berlinclock.ui

import com.anonymous.berlinclock.domain.model.BerlinClock
import com.anonymous.berlinclock.ui.model.BerlinClockUIModel
import com.anonymous.berlinclock.ui.model.LampState
import com.anonymous.berlinclock.utils.*
import javax.inject.Inject

class UIMapper @Inject constructor() {

    fun map(domain: BerlinClock): BerlinClockUIModel {
        val secondsLampState =
            if (domain.secondsLampOn) LampState.YELLOW_ENABLED else LampState.YELLOW_DISABLED

        val topHoursLampState = getStatesForHoursLamp(domain.topHoursLampCount)

        val bottomHoursLampState = getStatesForHoursLamp(domain.bottomHoursLampCount)

        val topMinutesLampState = getStateForTopMinutesLamps(domain.topMinutesLampCount)

        val bottomMinutesLampState = getStatesForBottomMinutesLamp(domain.bottomMinutesLampCount)

        return BerlinClockUIModel(
            time = domain.time,
            secondsLampState = secondsLampState,
            topMinutesLampState = topMinutesLampState,
            bottomMinutesLampState = bottomMinutesLampState,
            topHoursLampState = topHoursLampState,
            bottomHoursLampState = bottomHoursLampState
        )
    }

    private fun getStatesForHoursLamp(
        hoursLampCount: Int
    ): MutableList<LampState> {
        val lampStates = MutableList(TOTAL_TOP_HOURS_LAMP_COUNT) { LampState.RED_DISABLED }
        for (i in 0 until hoursLampCount) {
            lampStates[i] = LampState.RED_ENABLED
        }
        for (i in hoursLampCount until TOTAL_TOP_HOURS_LAMP_COUNT) {
            lampStates[i] = LampState.RED_DISABLED
        }
        return lampStates
    }

    private fun getStateForTopMinutesLamps(
        topMinutesLampsCount: Int
    ): MutableList<LampState> {
        val topMinutesLampState = MutableList(TOTAL_TOP_MINUTES_LAMP_COUNT) { LampState.RED_DISABLED }
        for (i in 1..topMinutesLampsCount) {
            topMinutesLampState[i - 1] =
                if (i % 3 == 0) LampState.RED_ENABLED else LampState.YELLOW_ENABLED
        }
        for (i in topMinutesLampsCount+1 .. TOTAL_TOP_MINUTES_LAMP_COUNT) {
            topMinutesLampState[i-1] =
                if (i % 3 == 0) LampState.RED_DISABLED else LampState.YELLOW_DISABLED
        }
        return topMinutesLampState
    }

    private fun getStatesForBottomMinutesLamp(
        bottomMinutesLampCount: Int
    ): MutableList<LampState> {
        val bottomMinutesLampState =
            MutableList(TOTAL_BOTTOM_MINUTES_LAMP_COUNT) { LampState.YELLOW_DISABLED }
        for (i in 0 until bottomMinutesLampCount) {
            bottomMinutesLampState[i] = LampState.YELLOW_ENABLED
        }
        for (i in bottomMinutesLampCount until TOTAL_BOTTOM_MINUTES_LAMP_COUNT) {
            bottomMinutesLampState[i] = LampState.YELLOW_DISABLED
        }
        return bottomMinutesLampState
    }
}