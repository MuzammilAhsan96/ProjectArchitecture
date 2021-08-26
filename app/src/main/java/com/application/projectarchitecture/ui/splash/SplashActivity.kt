package com.application.projectarchitecture.ui.splash

import android.os.Bundle
import com.application.projectarchitecture.R
import com.application.projectarchitecture.base.BaseActivity
import com.application.projectarchitecture.utils.AppConstant
import com.application.projectarchitecture.utils.PreferenceKeeper
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {


    override fun layoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateActivity()
    }

    private fun navigateActivity() {
        GlobalScope.launch { // context of the parent, main runBlocking coroutine
            delay(AppConstant.SPLASH_DELAY)
            gotoScreen()
        }
    }

    private fun gotoScreen() {

        tvVersion?.text = ""

        val isLogin = PreferenceKeeper.getInstance().isLogin
        //finish()
    }
}