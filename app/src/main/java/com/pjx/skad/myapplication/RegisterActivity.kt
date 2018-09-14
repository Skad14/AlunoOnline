package com.pjx.skad.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener {
            createNewAccount()
        }

        logoRegister.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            logoRegister.setImageBitmap(bitmap)
            //logoRegister.alpha = 0f
        }
    }

    private fun createNewAccount() {
        val nome = registerName.text.toString()
        val matricula = registerMatricula.text.toString()
        val email = registerEmail.text.toString()
        val password = registerPassword.text.toString()
        val passwordConf = registerConfPassword.text.toString()

        if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(passwordConf) || !TextUtils.isEmpty(nome) || !TextUtils.isEmpty(matricula)) {
            if (password == passwordConf) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this@RegisterActivity, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                                uploadImageToFirebaseStorage()
                            } else {
                                Toast.makeText(this@RegisterActivity, "Erro de cadastro.",
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

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener {
                        saveUserToFirebaseDatabase(it.toString())
                    }
                }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, registerName.text.toString(), registerMatricula.text.toString(), profileImageUrl)

        ref.setValue(user)
                .addOnSuccessListener {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
    }
}
