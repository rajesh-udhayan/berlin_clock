package com.anonymous.berlinclock.domain

import com.anonymous.berlinclock.domain.model.BerlinClock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class BerlinClockConverter @Inject constructor() {

    companion object {
        fun convertTimeToBerlinClockValues(time: LocalDateTime): BerlinClock {
            val hours = time.hour
            val minutes = time.minute
            val seconds = time.second

            return BerlinClock(
                secondsLampOn = seconds % 2 == 0,
                topHoursLampCount = hours.floorDiv(5),
                bottomHoursLampCount = hours % 5,
                topMinutesLampCount = minutes.floorDiv(5),
                bottomMinutesLampCount = minutes % 5,
                time = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            )
        }
    }
}