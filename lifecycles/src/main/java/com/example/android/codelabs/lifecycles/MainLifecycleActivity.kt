package com.example.android.codelabs.lifecycles

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_lifecycles.lfc_livedata
import kotlinx.android.synthetic.main.layout_lifecycles.lfc_persist
import kotlinx.android.synthetic.main.layout_lifecycles.lfc_share_data
import kotlinx.android.synthetic.main.layout_lifecycles.lfc_subscribe
import kotlinx.android.synthetic.main.layout_lifecycles.lfc_viewmodel

class MainLifecycleActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_lifecycles)

        lfc_viewmodel.setOnClickListener(this)
        lfc_livedata.setOnClickListener(this)
        lfc_subscribe.setOnClickListener(this)
        lfc_share_data.setOnClickListener(this)
        lfc_persist.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.lfc_viewmodel -> startActivity(ChronoActivity2::class.java)
            R.id.lfc_livedata -> startActivity(ChronoActivity3::class.java)
            R.id.lfc_subscribe -> startActivity(LocationActivity::class.java)
            R.id.lfc_share_data -> startActivity(ShareDataActivity::class.java)
            R.id.lfc_persist -> startActivity(SavedStateActivity::class.java)
        }
    }

    private fun <T> startActivity(cls: Class<T>) {
        val intent = Intent(this, cls)
        this.startActivity(intent)
    }

}