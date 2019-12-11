package com.example.android.codelabs.lifecycles

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

/**
 * A ViewModel used for the {@link ChronoActivity3}.
 */
class LiveDataTimerViewModel : ViewModel() {

    private val mElapsedTime = MutableLiveData<Long>()
    private var mInitialTime: Long = 0
    private var timer: Timer? = null

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        timer = Timer()

        // Update the elapsed time every second.
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000

                // setValue() cannot be called from a background thread so post to main thread.
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    fun getElapsedTime(): LiveData<Long> = mElapsedTime

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        const val ONE_SECOND = 1000L
    }
}