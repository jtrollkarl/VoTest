package com.moducode.votest.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.moducode.votest.R

abstract class SingleFragmentActivity: AppCompatActivity() {

    protected abstract fun getFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setupFragment()
    }

    private fun setupFragment(){
        if (supportFragmentManager.findFragmentById(R.id.frame_container) == null){
            supportFragmentManager.beginTransaction().replace(R.id.frame_container, getFragment()).commit()
        }
    }

}