package com.pjx.skad.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            validateLogin()
        }

        registerText.setOnClickListener {
            var registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        recoverText.setOnClickListener {
            var recoverIntent = Intent(this, RecoverActivity::class.java)
            startActivity(recoverIntent)
        }
    }

    private fun validateLogin() {

        if (inputEmail.text.isEmpty()) {
            txtInputEmail.isErrorEnabled = true
            txtInputEmail.error = getString(R.string.email_blank)
        } else {
            txtInputEmail.isErrorEnabled = false
        }

        if (inputPassword.text.isEmpty()) {
            txtInputPassword.isErrorEnabled = true
            txtInputPassword.error = getString(R.string.password_blank)
        } else {
            txtInputPassword.isErrorEnabled = false
        }

        if(!txtInputEmail.isErrorEnabled && !txtInputPassword.isErrorEnabled) {
            var newsIntent = Intent(this, NewsActivity::class.java)
            startActivity(newsIntent)
        }
    }
}