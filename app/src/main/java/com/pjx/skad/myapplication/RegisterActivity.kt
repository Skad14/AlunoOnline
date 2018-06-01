package com.pjx.skad.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener {
            validateRegister()
        }
    }

    private fun validateRegister() {

        if (registerEmail.text.isEmpty()) {
            txtRegisterEmail.isErrorEnabled = true
            txtRegisterEmail.error = getString(R.string.email_blank)
        } else {
            txtRegisterEmail.isErrorEnabled = false
        }

        if (registerPassword.text.isEmpty()) {
            txtRegisterPassword.isErrorEnabled = true
            txtRegisterPassword.error = getString(R.string.password_blank)
        } else {
            txtRegisterPassword.isErrorEnabled = false
        }

        if (registerConfPassword.text.isEmpty()) {
            txtRegisterConfPassword.isErrorEnabled = true
            txtRegisterConfPassword.error = getString(R.string.password_conf_blank)
        } else {
            txtRegisterConfPassword.isErrorEnabled = false
        }

        if(!txtRegisterEmail.isErrorEnabled && !txtRegisterPassword.isErrorEnabled && !txtRegisterConfPassword.isErrorEnabled) {
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}
