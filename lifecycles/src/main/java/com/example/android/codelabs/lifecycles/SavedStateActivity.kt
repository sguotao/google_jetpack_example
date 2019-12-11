package com.example.android.codelabs.lifecycles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.layout_saved_state.name_et
import kotlinx.android.synthetic.main.layout_saved_state.save_bt
import kotlinx.android.synthetic.main.layout_saved_state.saved_vm_tv

/**
 * Shows a simple form with a button and displays the value of a property in a ViewModel.
 */
class SavedStateActivity : AppCompatActivity() {

    private var mSavedStateViewModel: SavedStateViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_saved_state)

        // Obtain the ViewModel
        val factory = SavedStateViewModelFactory(this.application, this, savedInstanceState)
        mSavedStateViewModel =
            ViewModelProviders.of(this, factory).get(SavedStateViewModel::class.java)

        // Show the ViewModel property's value in a TextView
        mSavedStateViewModel?.getName()?.observe(this,
            Observer<String> {
                saved_vm_tv.text = getString(R.string.saved_in_vm, it)
            })

        // Save button
        save_bt.setOnClickListener {
            val name = name_et.text.toString()
            mSavedStateViewModel?.setName(name)
        }
    }
}