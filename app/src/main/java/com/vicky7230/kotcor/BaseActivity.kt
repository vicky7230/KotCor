package com.vicky7230.kotcor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vicky7230.kotcor.event.EventObserver

open class BaseActivity : AppCompatActivity() {

    var viewModel: BaseViewModel? = null

    fun setUpBaseActivity(viewModel: BaseViewModel) {
        this.viewModel = viewModel
        this.viewModel?.triggerEvent?.observe(this, EventObserver {
            when (it) {
                "LOG_OUT" -> Toast.makeText(
                    this@BaseActivity,
                    "Log out the user.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}