package com.example.simpleandroidapp.utils

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object TimerUtils {
    private const val NAME = "LoggedInDuration"
    private var seconds = 0L
    val timeFlow by lazy {
        callbackFlow {
            val timer = Timer(NAME)
            val task = timerTask {
                seconds += 1
                trySend(seconds.toFormattedTime()).isSuccess
            }
            timer.scheduleAtFixedRate(task, 0, 1_000)
            awaitClose {
                timer.cancel()
            }
        }
    }

    fun resetTimer() {
        seconds = 0
    }

    private fun Long.toFormattedTime(): String {
        val duration = toDuration(DurationUnit.SECONDS)
        val formattedString = "%02d:%02d:%02d"
        return formattedString.format(
            duration.inWholeHours,
            duration.inWholeMinutes - (60 * duration.inWholeHours),
            duration.inWholeSeconds - (60 * duration.inWholeMinutes) -2
        )
    }
}
