package com.vicky7230.kotcor

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        setUpBaseActivity(mainViewModel)

        mainViewModel.loading.observe(this, Observer {
            if (it)
                progress_bar.visibility = View.VISIBLE
            else
                progress_bar.visibility = View.GONE
        })

        mainViewModel.error.observe(this, Observer {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        })

        mainViewModel.posts?.observe(this, Observer {
            //Toast.makeText(this@MainActivity, "UI EVENT", Toast.LENGTH_LONG).show()
            //Timber.d(it.toString())
        })

        mainViewModel.getPosts()
    }
}
