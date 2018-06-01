package com.pjx.skad.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    private lateinit var newsPager: ViewPager
    var imgPager: IntArray = intArrayOf(R.drawable.iesb_campus_norte, R.drawable.iesb_campus_oeste, R.drawable.iesb_campus_sul)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsButton.setOnClickListener {
            var profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        newsPager = findViewById(R.id.newsPager)
    }
}
