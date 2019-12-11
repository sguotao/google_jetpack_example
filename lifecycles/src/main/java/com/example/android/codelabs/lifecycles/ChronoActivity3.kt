package com.example.android.codelabs.lifecycles

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.chrono_activity_3.timer_textview

class ChronoActivity3 : AppCompatActivity() {

    private var mLiveDataTimerViewModel: LiveDataTimerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chrono_activity_3)
        mLiveDataTimerViewModel = ViewModelProvider(this).get(LiveDataTimerViewModel::class.java)
        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long> {
            val newText = resources.getString(
                R.string.seconds, it
            )
            timer_textview.text = newText
            Log.d("ChronoActivity3", "Updating timer");
        }
        mLiveDataTimerViewModel?.getElapsedTime()?.observe(this, elapsedTimeObserver)
    }

}