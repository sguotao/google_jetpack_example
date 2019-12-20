package com.example

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android.codelabs.lifecycles.MainLifecycleActivity
import com.example.android.codelabs.navigation.NavigationActivity
import com.example.android.codelabs.paging.ui.SearchRepositoriesActivity
import com.example.background.SelectImageActivity
import kotlinx.android.synthetic.main.layout_main.arch_data_binding
import kotlinx.android.synthetic.main.layout_main.arch_lifecycle
import kotlinx.android.synthetic.main.layout_main.arch_live_data
import kotlinx.android.synthetic.main.layout_main.arch_navigation
import kotlinx.android.synthetic.main.layout_main.arch_paging
import kotlinx.android.synthetic.main.layout_main.arch_room
import kotlinx.android.synthetic.main.layout_main.arch_view_model
import kotlinx.android.synthetic.main.layout_main.arch_work_manager

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        arch_lifecycle.setOnClickListener(this)
        arch_live_data.setOnClickListener(this)
        arch_view_model.setOnClickListener(this)
        arch_room.setOnClickListener(this)
        arch_work_manager.setOnClickListener(this)
        arch_paging.setOnClickListener(this)
        arch_data_binding.setOnClickListener(this)
        arch_navigation.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.arch_lifecycle -> startActivity(MainLifecycleActivity::class.java)
            R.id.arch_live_data -> startActivity(MainLifecycleActivity::class.java)
            R.id.arch_view_model -> startActivity(MainLifecycleActivity::class.java)
            R.id.arch_room -> TODO()
            R.id.arch_work_manager -> startActivity(SelectImageActivity::class.java)
            R.id.arch_paging -> startActivity(SearchRepositoriesActivity::class.java)
            R.id.arch_data_binding -> TODO()
            R.id.arch_navigation -> startActivity(NavigationActivity::class.java)
        }
    }

    private fun <T> startActivity(cls: Class<T>) {
        val intent = Intent(this, cls)
        this.startActivity(intent)
    }

}