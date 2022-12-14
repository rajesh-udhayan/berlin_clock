package com.anonymous.berlinclock.domain

import com.anonymous.berlinclock.domain.LampState.*
import com.anonymous.berlinclock.model.BerlinClockState
import javax.inject.Inject

class BerlinClock @Inject constructor(){

    fun getBerlinClockState(time: String): BerlinClockState {
        val timeSplit = time.split(":")
        val topHoursLampState = getLampStateForTopHours(timeSplit[0].toInt())
        val bottomHoursLampState = getLampStateForBottomHours(timeSplit[0].toInt())
        val topMinutesLampState = getLampStateForTopMinutes(timeSplit[1].toInt())
        val bottomMinutesLampState = getLampStateForBottomMinutes(timeSplit[1].toInt())
        val secondsLampState = getLampStateForSeconds(timeSplit[2].toInt())

        return BerlinClockState(
            secondsLampState,
            topMinutesLampState,
            bottomMinutesLampState,
            topHoursLampState,
            bottomHoursLampState
        )
    }

    private fun getLampStateForSeconds(seconds: Int): LampState =
        if (seconds % 2 == 0) YELLOW else OFF

    private fun getLampStateForTopMinutes(minutes: Int): List<LampState> {
        val lampState = MutableList(11) { OFF }
        val topMinutes = minutes / 5
        (1..topMinutes).forEach { i ->
            if (i % 3 == 0) {
                lampState[i - 1] = RED
            } else {
                lampState[i - 1] = YELLOW
            }
        }
        return lampState
    }

    private fun getLampStateForBottomMinutes(minutes: Int): List<LampState> {
        val lampState = MutableList(4) { OFF }
        val bottomMinutes = minutes % 5
        (1..bottomMinutes).forEach { i ->
            lampState[i - 1] = YELLOW
        }
        return lampState
    }

    private fun getLampStateForTopHours(hours: Int): List<LampState> {
        val lampState = MutableList(4) { OFF }
        val topHours = hours / 5
        (1..topHours).forEach { i ->
            lampState[i - 1] = RED
        }
        return lampState
    }

    private fun getLampStateForBottomHours(hours: Int): List<LampState> {
        val lampState = MutableList(4) { OFF }
        val bottomHours = hours % 5
        (1..bottomHours).forEach { i ->
            lampState[i - 1] = RED
        }
        return lampState
    }

}

enum class LampState {
    YELLOW,
    OFF,
    RED
}