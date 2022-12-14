package com.anonymous.berlinclock.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anonymous.berlinclock.domain.BerlinClock
import com.anonymous.berlinclock.model.BerlinClockState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BerlinClockViewModel @Inject constructor(private val berlinClock: BerlinClock): ViewModel() {

    private var mBerlinClockState = MutableLiveData<BerlinClockState>()
    val berlinClockState: LiveData<BerlinClockState> = mBerlinClockState

    fun initBerlinClock(){
        mBerlinClockState.postValue(BerlinClockState.initialState())
    }

    fun updateTime(time: String) {
        val result = berlinClock.getBerlinClockState(time)
        mBerlinClockState.postValue(result)
    }
    
}