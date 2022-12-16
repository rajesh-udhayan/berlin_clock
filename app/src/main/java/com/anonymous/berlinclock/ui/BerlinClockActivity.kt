package com.anonymous.berlinclock.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.anonymous.berlinclock.ui.theme.BerlinClockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BerlinClockActivity : ComponentActivity() {

    private lateinit var timer: CountDownTimer
    private val viewModel: BerlinClockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val berlinClockState by viewModel.berlinClockState.collectAsState()
            BerlinClockTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BerlinClockView(berlinClockState)
                }
            }
        }
    }
}