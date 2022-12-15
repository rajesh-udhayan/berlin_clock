package com.anonymous.berlinclock.ui

import com.anonymous.berlinclock.domain.BerlinClockGenerator
import com.anonymous.berlinclock.domain.model.BerlinClock
import com.anonymous.berlinclock.ui.model.BerlinClockUIModel
import com.anonymous.berlinclock.ui.model.LampState
import javax.inject.Inject

class UIMapper @Inject constructor(private val generator: BerlinClockGenerator) {

    fun map(domain: BerlinClock): BerlinClockUIModel {
        return BerlinClockUIModel(
            time = domain.time,
            secondsLampState = getLampStateForSeconds(domain.seconds.toInt()),
            topMinutesLampState = getLampStateForTopMinutes(domain.minutes.toInt()),
            bottomMinutesLampState = getLampStateForBottomMinutes(domain.minutes.toInt()),
            topHoursLampState = getLampStateForTopHours(domain.hours.toInt()),
            bottomHoursLampState = getLampStateForBottomHours(domain.hours.toInt())
        )
    }

    private fun getLampStateForSeconds(seconds: Int): LampState =
        if (seconds % 2 == 0) LampState.YELLOW_ENABLED else LampState.YELLOW_DISABLED

    private fun getLampStateForTopMinutes(minutes: Int): List<LampState> {
        val lampState = MutableList(11) {
            if((it + 1) % 3 == 0) LampState.RED_DISABLED else LampState.YELLOW_DISABLED
        }
        val topMinutes = minutes / 5
        (1..topMinutes).forEach { i ->
            if (i % 3 == 0) {
                lampState[i - 1] = LampState.RED_ENABLED
            } else {
                lampState[i - 1] = LampState.YELLOW_ENABLED
            }
        }
        return lampState
    }

    private fun getLampStateForBottomMinutes(minutes: Int): List<LampState> {
        val lampState = MutableList(4) { LampState.YELLOW_DISABLED }
        val bottomMinutes = minutes % 5
        (1..bottomMinutes).forEach { i ->
            lampState[i - 1] = LampState.YELLOW_ENABLED
        }
        return lampState
    }

    private fun getLampStateForTopHours(hours: Int): List<LampState> {
        val lampState = MutableList(4) { LampState.RED_DISABLED }
        val topHours = hours / 5
        (1..topHours).forEach { i ->
            lampState[i - 1] = LampState.RED_ENABLED
        }
        return lampState
    }

    private fun getLampStateForBottomHours(hours: Int): List<LampState> {
        val lampState = MutableList(4) { LampState.RED_DISABLED }
        val bottomHours = hours % 5
        (1..bottomHours).forEach { i ->
            lampState[i - 1] = LampState.RED_ENABLED
        }
        return lampState
    }
}