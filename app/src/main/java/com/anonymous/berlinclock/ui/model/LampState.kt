package com.anonymous.berlinclock.ui.model

import androidx.compose.ui.graphics.Color

enum class LampState(val color: Color) {
    YELLOW_ENABLED(Color(0xFFFFFF33)),
    YELLOW_DISABLED(Color(0xFF666633)),
    RED_ENABLED(Color(0xFFF33300)),
    RED_DISABLED(Color(0xFF633330)),
}