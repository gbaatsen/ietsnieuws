package com.baatsen.ietsnieuws.presentation.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.baatsen.ietsnieuws.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment()).commit()
    }
}