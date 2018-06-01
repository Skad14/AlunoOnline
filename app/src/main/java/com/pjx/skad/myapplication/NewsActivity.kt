package com.pjx.skad.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsButton.setOnClickListener {
            var profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }
    }
}
