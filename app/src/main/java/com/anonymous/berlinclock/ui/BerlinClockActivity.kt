package com.anonymous.berlinclock.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anonymous.berlinclock.R
import com.anonymous.berlinclock.domain.LampState
import com.anonymous.berlinclock.model.BerlinClockState
import com.anonymous.berlinclock.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

private val currentTime = mutableStateOf("00:00:00")

@AndroidEntryPoint
class BerlinClockActivity : ComponentActivity() {

    private lateinit var timer: CountDownTimer
    private val viewModel: BerlinClockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTimer()
        setContent {
            BerlinClockTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainView()
                }
            }
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(COUNT_DOWN_MILLIS, COUNT_DOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long){
                val mTime: String = SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).format(
                    Date()
                )
                viewModel.updateTime(mTime)
                currentTime.value = mTime
            }
            override fun onFinish() {
                start()
            }
        }
        timer.start()
    }

    companion object {
        const val COUNT_DOWN_MILLIS = 600000L
        const val COUNT_DOWN_INTERVAL = 1000L
        const val TIME_FORMAT = "HH:mm:ss"
    }
}

    @Composable
    fun MainView(viewModel: BerlinClockViewModel = hiltViewModel()) {
        val berlinClockState by viewModel.berlinClockState.observeAsState(BerlinClockState.initialState())
        BerlinClockView(berlinClockState)
    }

    @Composable
    fun BerlinClockView(berlinClockState: BerlinClockState) {
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

                val secondsLampOn = berlinClockState.secondsLampState != LampState.OFF
                val color = if (secondsLampOn) yellowEnabled else yellowDisabled
                //Seconds view
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .testTag("secondsLamp")
                        .background(color = color, shape = CircleShape)
                )
                //Top Hours view
                val topHours = berlinClockState.topHoursLampState
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    topHours.forEachIndexed { i, lamp ->
                        val topHoursLampOn = lamp != LampState.OFF
                        val lampColor = if (topHoursLampOn) redEnabled else redDisabled
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .background(color = lampColor, shape = RoundedCornerShape(4.dp))
                                .testTag("topHourLamp${i + 1}")
                        )
                    }
                }
                //Bottom hours view
                val bottomHours = berlinClockState.bottomHoursLampState
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    bottomHours.forEachIndexed { i, lamp ->
                        val bottomHoursLampOn = lamp != LampState.OFF
                        val lampColor = if (bottomHoursLampOn) redEnabled else redDisabled
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .background(color = lampColor, shape = RoundedCornerShape(4.dp))
                                .testTag("bottomHourLamp${i + 1}")
                        )
                    }
                }
                // Top Minutes view
                val topMinutes = berlinClockState.topMinutesLampState
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    topMinutes.forEachIndexed { i, lamp ->
                        val lampColor: Color
                        if (i == 2 || i == 5 || i == 8){
                            lampColor = if (lamp != LampState.OFF) redEnabled else redDisabled
                        } else {
                            lampColor = if (lamp != LampState.OFF) yellowEnabled else yellowDisabled
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .background(color = lampColor, shape = RoundedCornerShape(4.dp))
                                .testTag("topMinutesLamp${i + 1}")
                        )
                    }
                }
                // Bottom Minutes view
                val bottomMinutes = berlinClockState.bottomMinutesLampState
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    bottomMinutes.forEachIndexed { i, lamp ->
                        val lampColor: Color = if (lamp != LampState.OFF) redEnabled else redDisabled
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .background(color = lampColor, shape = RoundedCornerShape(4.dp))
                                .testTag("bottomMinutesLamp${i + 1}")
                        )
                    }
                }
                //Time text
                Spacer(modifier = Modifier.weight(1f))
                val mTime by currentTime
                Text(
                    modifier = Modifier.testTag("timeText"),
                    text = mTime,
                    fontSize = 30.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }