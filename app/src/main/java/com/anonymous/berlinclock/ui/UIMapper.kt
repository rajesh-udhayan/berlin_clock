package com.anonymous.berlinclock.ui

import com.anonymous.berlinclock.domain.BerlinClockGenerator
import com.anonymous.berlinclock.domain.model.BerlinClock
import com.anonymous.berlinclock.ui.model.BerlinClockUIModel
import javax.inject.Inject

class UIMapper @Inject constructor(private val generator: BerlinClockGenerator) {

    fun map(domain: BerlinClock): BerlinClockUIModel {
        return BerlinClockUIModel(
            secondsLampState = generator.getLampStateForSeconds(domain.seconds.toInt()),
            topMinutesLampState = generator.getLampStateForTopMinutes(domain.minutes.toInt()),
            bottomMinutesLampState = generator.getLampStateForBottomMinutes(domain.minutes.toInt()),
            topHoursLampState = generator.getLampStateForTopHours(domain.hours.toInt()),
            bottomHoursLampState = generator.getLampStateForBottomHours(domain.hours.toInt())
        )
    }

}