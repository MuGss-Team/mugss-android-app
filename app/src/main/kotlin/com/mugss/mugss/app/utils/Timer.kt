package com.mugss.mugss.app.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Pass scope only with SuperVisorJob
 */
class Timer(
    private val scope: CoroutineScope,
    private val duration: Long,
    private val period: Long,
    private val onTick: (Long) -> Unit,
    private val onFinish: () -> Unit,
) {

    @Volatile
    private var timerJob = buildTimerJob(duration)

    @Volatile
    private var currentTime = duration

    fun start() {
        if (timerJob.isCancelled || timerJob.isActive || timerJob.isCompleted) {
            restart()
        } else {
            timerJob.start()
        }
    }

    fun restart() = scope.launch {
        if (timerJob.isActive) {
            timerJob.cancelAndJoin()
        }
        timerJob = buildTimerJob(duration)
        timerJob.start()
    }

    fun pause() {
        if (timerJob.isActive) {
            timerJob.cancel()
        }
    }

    fun resume() {
        if (!timerJob.isActive) {
            timerJob = buildTimerJob(currentTime)
            timerJob.start()
        }
    }

    fun reset() {
        if (timerJob.isActive) {
            timerJob.cancel()
        }
        timerJob = buildTimerJob(duration)
        currentTime = duration
    }

    private fun buildTimerJob(duration: Long) = scope.launch {
        flow {
            var currentTime = duration
            while (currentTime >= 0) {
                emit(currentTime)
                if (currentTime != 0L) delay(period)
                currentTime -= period
            }
        }.flowOn(Dispatchers.IO).collect { timePlayer ->
            currentTime = timePlayer
            onTick(timePlayer)
            if (timePlayer == 0L) {
                onFinish()
            }
        }
    }
}