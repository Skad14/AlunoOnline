package com.pjx.skad.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var email: String? = null
    private var password: String? = null
    private var passwordConf: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            createNewAccount()
        }
    }

    private fun createNewAccount() {
        email = registerEmail.text.toString()
        password = registerPassword.text.toString()
        passwordConf = registerConfPassword.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordConf)) {
            if (password == passwordConf) {
                mAuth!!.createUserWithEmailAndPassword(email!!, password!!)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this@RegisterActivity, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@RegisterActivity, "Erro de autenticação.",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }
            } else {
                Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Preencha todos os dados", Toast.LENGTH_SHORT).show()
        }
    }
}
