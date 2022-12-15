package com.anonymous.berlinclock.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anonymous.berlinclock.R
import com.anonymous.berlinclock.ui.model.BerlinClockUIModel

@Composable
fun BerlinClockView(berlinClockState: BerlinClockUIModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SecondsView(berlinClockState)
            TopHoursView(berlinClockState)
            BottomHoursView(berlinClockState)
            TopMinutesView(berlinClockState)
            BottomMinutesView(berlinClockState)
            TimeTextView(berlinClockState)
        }
    }
}

@Composable
private fun ColumnScope.TimeTextView(berlinClockState: BerlinClockUIModel) {
    Spacer(modifier = Modifier.Companion.weight(1f))
    Text(
        modifier = Modifier.testTag("timeText"),
        text = berlinClockState.time,
        fontSize = 30.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun BottomMinutesView(berlinClockState: BerlinClockUIModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        berlinClockState.bottomMinutesLampState.forEachIndexed { index, lampState ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .background(color = lampState.color, shape = RoundedCornerShape(4.dp))
                    .testTag("bottomMinutesLamp${index + 1}")
            )
        }
    }
}

@Composable
private fun TopMinutesView(berlinClockState: BerlinClockUIModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        berlinClockState.topMinutesLampState.forEachIndexed { index, lampState ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .background(color = lampState.color, shape = RoundedCornerShape(4.dp))
                    .testTag("topMinutesLamp${index + 1}")
            )
        }
    }
}

@Composable
private fun BottomHoursView(berlinClockState: BerlinClockUIModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        berlinClockState.bottomHoursLampState.forEachIndexed { index, lampState ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .background(color = lampState.color, shape = RoundedCornerShape(4.dp))
                    .testTag("bottomHourLamp${index + 1}")
            )
        }
    }
}

@Composable
private fun TopHoursView(berlinClockState: BerlinClockUIModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        berlinClockState.topHoursLampState.forEachIndexed { index, lampState ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .background(color = lampState.color, shape = RoundedCornerShape(4.dp))
                    .testTag("topHourLamp${index + 1}")
            )
        }
    }
}

@Composable
private fun SecondsView(berlinClockState: BerlinClockUIModel) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .testTag("secondsLamp")
            .background(color = berlinClockState.secondsLampState.color, shape = CircleShape)
    )
}