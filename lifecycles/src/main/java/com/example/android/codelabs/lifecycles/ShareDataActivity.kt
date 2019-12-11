package com.example.android.codelabs.lifecycles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Shows two {@link FragmentShareData} fragments.
 */
class ShareDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_sharedata)
    }
}