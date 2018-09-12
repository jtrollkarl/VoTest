package com.moducode.votest


import android.support.v4.app.Fragment
import com.moducode.votest.ui.SingleFragmentActivity
import com.moducode.votest.ui.fragment.WeatherFragment



import javax.inject.Inject

class MainActivity : SingleFragmentActivity() {

    override fun getFragment(): Fragment = WeatherFragment()
}
