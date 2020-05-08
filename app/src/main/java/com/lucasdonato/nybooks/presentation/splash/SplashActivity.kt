package com.lucasdonato.nybooks.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lucasdonato.nybooks.R
import com.lucasdonato.nybooks.mechanism.Constants.SPLASH_DISPLAY_TIME
import com.lucasdonato.nybooks.presentation.books.view.BooksActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(BooksActivity.getStartIntent(this))
        }, SPLASH_DISPLAY_TIME.toLong())
    }
}
