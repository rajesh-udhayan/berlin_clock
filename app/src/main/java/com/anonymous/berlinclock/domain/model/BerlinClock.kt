package com.anonymous.berlinclock.domain.model

data class BerlinClock(val time: String){
    val hours = time.split(":")[0]
    val minutes = time.split(":")[1]
    val seconds = time.split(":")[2]
}
