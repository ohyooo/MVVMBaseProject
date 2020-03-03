package com.ohyooo.demo.ui.splash

import android.app.Activity
import android.os.Bundle
import com.ohyooo.demo.ui.main.MainActivity
import org.jetbrains.anko.intentFor

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(intentFor<MainActivity>())
        finish()
    }
}
