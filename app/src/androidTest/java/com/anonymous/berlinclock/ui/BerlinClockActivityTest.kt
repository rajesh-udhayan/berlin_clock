package com.anonymous.berlinclock.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.anonymous.berlinclock.model.BerlinClockState
import com.anonymous.berlinclock.ui.theme.BerlinClockTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class BerlinClockActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BerlinClockTheme {
                BerlinClockView(BerlinClockState.initialState())
            }
        }
    }

    @Test
    fun shouldDisplayAppTitle() {
        with(composeTestRule) {
            val title = onNodeWithText("BerlinClock")

            title.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplaySecondsLamp(){
        with(composeTestRule){
            val secondsButton = onNodeWithTag("secondsLamp")

            secondsButton.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayHourLamps(){
        with(composeTestRule){
            val topHour1 = onNodeWithTag("topHourLamp1")
            val topHour2 = onNodeWithTag("topHourLamp2")
            val topHour3 = onNodeWithTag("topHourLamp3")
            val topHour4 = onNodeWithTag("topHourLamp4")
            val bottomHour1 = onNodeWithTag("bottomHourLamp1")
            val bottomHour2 = onNodeWithTag("bottomHourLamp2")
            val bottomHour3 = onNodeWithTag("bottomHourLamp3")
            val bottomHour4 = onNodeWithTag("bottomHourLamp4")

            topHour1.assertIsDisplayed()
            topHour2.assertIsDisplayed()
            topHour3.assertIsDisplayed()
            topHour4.assertIsDisplayed()
            bottomHour1.assertIsDisplayed()
            bottomHour2.assertIsDisplayed()
            bottomHour3.assertIsDisplayed()
            bottomHour4.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayMinutesLamps(){
        with(composeTestRule){
            val topMinutes1 = onNodeWithTag("topMinutesLamp1")
            val topMinutes2 = onNodeWithTag("topMinutesLamp2")
            val topMinutes3 = onNodeWithTag("topMinutesLamp3")
            val topMinutes4 = onNodeWithTag("topMinutesLamp4")
            val topMinutes5 = onNodeWithTag("topMinutesLamp4")
            val topMinutes6 = onNodeWithTag("topMinutesLamp4")
            val topMinutes7 = onNodeWithTag("topMinutesLamp4")
            val topMinutes8 = onNodeWithTag("topMinutesLamp4")
            val topMinutes9 = onNodeWithTag("topMinutesLamp4")
            val topMinutes10 = onNodeWithTag("topMinutesLamp4")
            val topMinutes11 = onNodeWithTag("topMinutesLamp4")
            val bottomMinutes1 = onNodeWithTag("bottomMinutesLamp1")
            val bottomMinutes2 = onNodeWithTag("bottomMinutesLamp2")
            val bottomMinutes3 = onNodeWithTag("bottomMinutesLamp3")
            val bottomMinutes4 = onNodeWithTag("bottomMinutesLamp4")

            topMinutes1.assertIsDisplayed()
            topMinutes2.assertIsDisplayed()
            topMinutes3.assertIsDisplayed()
            topMinutes4.assertIsDisplayed()
            topMinutes5.assertIsDisplayed()
            topMinutes6.assertIsDisplayed()
            topMinutes7.assertIsDisplayed()
            topMinutes8.assertIsDisplayed()
            topMinutes9.assertIsDisplayed()
            topMinutes10.assertIsDisplayed()
            topMinutes11.assertIsDisplayed()
            bottomMinutes1.assertIsDisplayed()
            bottomMinutes2.assertIsDisplayed()
            bottomMinutes3.assertIsDisplayed()
            bottomMinutes4.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayTimeText(){
        with(composeTestRule){
            val timeText = onNodeWithTag("timeText")

            timeText.assertIsDisplayed()
        }
    }
}