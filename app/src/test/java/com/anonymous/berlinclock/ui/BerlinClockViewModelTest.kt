package com.anonymous.berlinclock.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.berlinclock.domain.BerlinClock
import com.anonymous.berlinclock.domain.LampState.*
import com.anonymous.berlinclock.model.BerlinClockState
import com.anonymous.berlinclock.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BerlinClockViewModelTest {

    private val berlinClock = BerlinClock()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BerlinClockViewModel

    @Before
    fun setUp(){
        viewModel = BerlinClockViewModel(berlinClock)
    }

    @Test
    fun `should initialize berlin clock when init function called`(){
        viewModel.initBerlinClock()

        val value = viewModel.berlinClockState.getOrAwaitValue()
        val expectedResult = BerlinClockState.initialState()

        assertThat(value).isEqualTo(expectedResult)
    }

    @Test
    fun return_value_from_livedata_equals_expected_berlin_clock_data() {
        val time = "12:10:01"

        viewModel.updateTime(time)
        val hoursOnTop = listOf(RED, RED, OFF, OFF)
        val hoursOnBottom = listOf(RED, RED, OFF, OFF)
        val minutesOnTop = listOf(YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)
        val minutesOnBottom = listOf(OFF, OFF, OFF, OFF)
        val expectedState = BerlinClockState(
            OFF,
            minutesOnTop,
            minutesOnBottom,
            hoursOnTop,
            hoursOnBottom
        )
        val value = viewModel.berlinClockState.getOrAwaitValue()

        assertThat(value).isEqualTo(expectedState)
    }
} 