package com.anonymous.berlinclock.model

import com.anonymous.berlinclock.domain.LampState

data class BerlinClockState(
    val secondsLampState: LampState,
    val topMinutesLampState: List<LampState>,
    val bottomMinutesLampState: List<LampState>,
    val topHoursLampState: List<LampState>,
    val bottomHoursLampState: List<LampState>
) {
    companion object {
        fun initialState(): BerlinClockState {
            return BerlinClockState(LampState.OFF,
                MutableList(11) { LampState.OFF },
                MutableList(4) { LampState.OFF },
                MutableList(4) { LampState.OFF },
                MutableList(4) { LampState.OFF })
        }
    }
}
