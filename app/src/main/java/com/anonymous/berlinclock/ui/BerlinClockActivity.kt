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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.anonymous.berlinclock.ui.model.BerlinClockUIModel
import com.anonymous.berlinclock.ui.model.LampState
import com.anonymous.berlinclock.ui.theme.*
import com.anonymous.berlinclock.utils.BOTTOM_HOURS_LAMP_COUNT
import com.anonymous.berlinclock.utils.BOTTOM_MINUTES_LAMP_COUNT
import com.anonymous.berlinclock.utils.TOP_HOURS_LAMP_COUNT
import com.anonymous.berlinclock.utils.TOP_MINUTES_LAMP_COUNT
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
            val berlinClockState: BerlinClockUIModel = viewModel.berlinClockState.collectAsState().value
            BerlinClockTheme {
                Surface(color = MaterialTheme.colors.background) {
                   BerlinClockView(berlinClockState)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startTimer()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
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