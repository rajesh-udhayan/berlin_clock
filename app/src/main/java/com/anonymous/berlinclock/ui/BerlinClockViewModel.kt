package com.anonymous.berlinclock.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.berlinclock.domain.BerlinClockConverter
import com.anonymous.berlinclock.domain.model.BerlinClock
import com.anonymous.berlinclock.ui.model.BerlinClockUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BerlinClockViewModel @Inject constructor(private val uiMapper: UIMapper) : ViewModel() {

    private var mBerlinClockState = MutableStateFlow(BerlinClockUIModel.initialState())
    val berlinClockState: StateFlow<BerlinClockUIModel> = mBerlinClockState

    init {
        viewModelScope.launch {
            while (true){
                updateTime(LocalDateTime.now())
                delay(1000)
            }
        }
    }

    fun updateTime(time: LocalDateTime) {
        val berlinClock = BerlinClockConverter.convertTimeToBerlinClockValues(time)
        val uiModel = uiMapper.map(berlinClock)

        mBerlinClockState.value = uiModel
    }

}