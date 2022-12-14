package com.anonymous.berlinclock.ui

import androidx.lifecycle.ViewModel
import com.anonymous.berlinclock.domain.model.BerlinClock
import com.anonymous.berlinclock.ui.model.BerlinClockUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BerlinClockViewModel @Inject constructor(private val uiMapper: UIMapper) : ViewModel() {

    private var mBerlinClockState = MutableStateFlow(BerlinClockUIModel.initialState())
    val berlinClockState: StateFlow<BerlinClockUIModel> = mBerlinClockState

    fun updateTime(time: String) {
        val berlinClock = BerlinClock(time)
        val uiModel = uiMapper.map(berlinClock)

        mBerlinClockState.value = uiModel
    }

}