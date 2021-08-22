package com.example.bigmusic.View

import java.text.NumberFormat
import kotlin.math.roundToInt

class ReturnK(toLong: Long) {
    fun formatNumber(value: Long): String {
        return when {
            value >= 1E9 -> "${(value.toFloat() / 1E9).roundToInt()}B"
            value >= 1E6 -> "${(value.toFloat() / 1E6).roundToInt()}M"
            value >= 1E3 -> "${(value.toFloat() / 1E3).roundToInt()}K"
            else -> NumberFormat.getInstance().format(value) }
    }

}