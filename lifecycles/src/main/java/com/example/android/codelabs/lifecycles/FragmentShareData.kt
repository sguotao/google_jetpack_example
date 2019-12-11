package com.example.android.codelabs.lifecycles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Shows a SeekBar that is synced with a value in a ViewModel.
 */
class FragmentShareData : Fragment() {

    private var mSeekBar: SeekBar? = null
    private var mSeekBarViewModel: SeekBarViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_share_data, container, false)
        mSeekBar = root.findViewById(R.id.seekBar)
        mSeekBarViewModel = ViewModelProvider(requireActivity()).get(
            SeekBarViewModel::class.java
        )
        subscribeSeekBar()
        return root
    }

    private fun subscribeSeekBar() {
        // Update the ViewModel when the SeekBar is changed.
        mSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Log.d("sguotao", "Progress changed!")
                    mSeekBarViewModel?.seekbarValue?.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        // Update the SeekBar when the ViewModel is changed.
        mSeekBarViewModel?.seekbarValue?.observe(requireActivity(), object : Observer<Int> {
            override fun onChanged(value: Int?) {
                mSeekBar?.progress = value ?: 0
            }
        })
    }

    class SeekBarViewModel : ViewModel() {
        val seekbarValue = MutableLiveData<Int>()
    }
}
