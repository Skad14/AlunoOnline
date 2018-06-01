package com.pjx.skad.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import kotlinx.android.synthetic.main.activity_recover.*
import kotlinx.android.synthetic.main.activity_register.*
import android.widget.EditText

class RecoverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        recoverButton.setOnClickListener {
            validateRegister()
        }
    }

    private fun validateRegister() {

        if (recoverEmail.text.isEmpty()) {
            txtRecoverEmail.isErrorEnabled = true
            txtRecoverEmail.error = getString(R.string.email_blank)
        } else {
            txtRecoverEmail.isErrorEnabled = false
        }

        if (recoverPassword.text.isEmpty()) {
            txtRecoverPassword.isErrorEnabled = true
            txtRecoverPassword.error = getString(R.string.password_blank)
        } else {
            txtRecoverPassword.isErrorEnabled = false
        }

        if(!txtRecoverEmail.isErrorEnabled && !txtRecoverPassword.isErrorEnabled) {
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}