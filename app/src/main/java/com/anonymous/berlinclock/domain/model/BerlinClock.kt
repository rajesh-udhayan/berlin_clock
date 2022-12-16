package com.anonymous.berlinclock.domain.model

data class BerlinClock(
    val secondsLampOn:Boolean,
    val topHoursLampCount: Int,
    val bottomHoursLampCount: Int,
    val topMinutesLampCount: Int,
    val bottomMinutesLampCount: Int,
    val time: String
)
