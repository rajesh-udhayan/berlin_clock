package com.anonymous.berlinclock

import com.anonymous.berlinclock.domain.BerlinClock
import com.anonymous.berlinclock.domain.LampState.*
import com.anonymous.berlinclock.model.BerlinClockState
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class BerlinClockTest {
    private lateinit var berlinClock: BerlinClock

    @Before
    fun setUp(){
        berlinClock = BerlinClock()
    }

    @Test
    fun `should return yellow state for 0 second`(){
        val clockState = berlinClock.getBerlinClockState("00:00:00")

        assertThat(clockState.secondsLampState).isEqualTo(YELLOW)
    }

    @Test
    fun `should return off state for 1 seconds`(){
        val clockState = berlinClock.getBerlinClockState("00:00:01")

        assertThat(clockState.secondsLampState).isEqualTo(OFF)
    }

    @Test
    fun `should return yellow state for 2 seconds`(){
        val clockState = berlinClock.getBerlinClockState("00:00:02")

        assertThat(clockState.secondsLampState).isEqualTo(YELLOW)
    }

    @Test
    fun `should return off state for 3 seconds`(){
        val clockState = berlinClock.getBerlinClockState("00:00:03")

        assertThat(clockState.secondsLampState).isEqualTo(OFF)
    }

    @Test
    fun `should return off for all lamps when minute is 0`(){
        val clockState = berlinClock.getBerlinClockState("00:00:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return first bottom lamp as yellow and others as off when minute is 1`(){
        val clockState = berlinClock.getBerlinClockState("00:01:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(YELLOW,OFF,OFF,OFF))
    }

    @Test
    fun `should return first two bottom lamp as yellow and others as off when minute is 2`(){
        val clockState = berlinClock.getBerlinClockState("00:02:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(YELLOW,YELLOW,OFF,OFF))
    }

    @Test
    fun `should return first three bottom lamp as yellow and others as off when minute is 3`(){
        val clockState = berlinClock.getBerlinClockState("00:03:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(YELLOW,YELLOW,YELLOW,OFF))
    }

    @Test
    fun `should return all bottom lamp as yellow and others as off when minute is 4`(){
        val clockState = berlinClock.getBerlinClockState("00:04:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(YELLOW,YELLOW,YELLOW,YELLOW))
    }

    @Test
    fun `should return all bottom lamp as off when minute is 5`(){
        val clockState = berlinClock.getBerlinClockState("00:05:03")

        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return first top lamp as yellow and all bottom lamp as off when minute is 5`(){
        val clockState = berlinClock.getBerlinClockState("00:05:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(YELLOW,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return first top and bottom lamp as yellow when minute is 6`(){
        val clockState = berlinClock.getBerlinClockState("00:06:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(YELLOW,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(YELLOW,OFF,OFF,OFF))
    }

    @Test
    fun `should return first two top and all bottom lamp as yellow when minute is 14`(){
        val clockState = berlinClock.getBerlinClockState("00:14:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(YELLOW,YELLOW,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(YELLOW,YELLOW,YELLOW,YELLOW))
    }

    @Test
    fun `should return first two top as yellow and third as red all bottom lamp as off when minute is 15`(){
        val clockState = berlinClock.getBerlinClockState("00:15:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(YELLOW,YELLOW,RED,OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return expected lamp state when minute is 45`(){
        val clockState = berlinClock.getBerlinClockState("00:45:03")

        assertThat(clockState.topMinutesLampState).isEqualTo(listOf(YELLOW,YELLOW,RED,YELLOW,YELLOW,RED,YELLOW,YELLOW,RED,OFF,OFF))
        assertThat(clockState.bottomMinutesLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return all lamp as off when hour is 0`(){
        val clockState = berlinClock.getBerlinClockState("00:45:03")

        assertThat(clockState.topHoursLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomHoursLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return first bottom lamp as red and others as off when hour is 1`(){
        val clockState = berlinClock.getBerlinClockState("01:45:03")

        assertThat(clockState.topHoursLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomHoursLampState).isEqualTo(listOf(RED,OFF,OFF,OFF))
    }

    @Test
    fun `should return first two bottom lamp as red and others as off when hour is 2`(){
        val clockState = berlinClock.getBerlinClockState("02:45:03")

        assertThat(clockState.topHoursLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomHoursLampState).isEqualTo(listOf(RED,RED,OFF,OFF))
    }

    @Test
    fun `should return all bottom lamp as red and others as off when hour is 4`(){
        val clockState = berlinClock.getBerlinClockState("04:45:03")

        assertThat(clockState.topHoursLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
        assertThat(clockState.bottomHoursLampState).isEqualTo(listOf(RED,RED,RED,RED))
    }

    @Test
    fun `should return all bottom lamp as off when hour is 5`(){
        val clockState = berlinClock.getBerlinClockState("05:45:03")

        assertThat(clockState.bottomHoursLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return first top lamp as red and all bottom lamp as off when hour is 5`(){
        val clockState = berlinClock.getBerlinClockState("05:45:03")

        assertThat(clockState.topHoursLampState).isEqualTo(listOf(RED,OFF,OFF,OFF))
        assertThat(clockState.bottomHoursLampState).isEqualTo(listOf(OFF,OFF,OFF,OFF))
    }

    @Test
    fun `should return first top and bottom lamp as red when hour is 6`(){
        val clockState = berlinClock.getBerlinClockState("06:45:03")

        assertThat(clockState.topHoursLampState).isEqualTo(listOf(RED,OFF,OFF,OFF))
        assertThat(clockState.bottomHoursLampState).isEqualTo(listOf(RED,OFF,OFF,OFF))
    }

    @Test
    fun `should return expected lamp state for given time`() {
        val actualState = berlinClock.getBerlinClockState("13:31:06")

        val secondsLampState = YELLOW
        val topMinutesLampState =
            listOf(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, OFF, OFF, OFF, OFF, OFF)
        val bottomMinutesLampState = listOf(YELLOW, OFF, OFF, OFF)
        val topHoursLampState = listOf(RED, RED, OFF, OFF)
        val bottomHoursLampState = listOf(RED, RED, RED, OFF)
        val expectedState = BerlinClockState(
            secondsLampState,
            topMinutesLampState,
            bottomMinutesLampState,
            topHoursLampState,
            bottomHoursLampState
        )

        assertThat(actualState).isEqualTo(expectedState)
    }
}