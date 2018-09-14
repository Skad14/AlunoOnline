package com.pjx.skad.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

         profileLogo.setOnClickListener {
             val intent = Intent(Intent.ACTION_PICK)
             intent.type = "image/*"
             startActivityForResult(intent, 0)
         }
        profileSaveButton.setOnClickListener {
            performRegister()
            var newsIntent = Intent(this, NewsActivity::class.java)
            startActivity(newsIntent)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            profileLogo.setImageBitmap(bitmap)

        }
    }

    private fun performRegister() {
        val nome = profileName.text.toString()
        val matricula = profileMatricula.text.toString()
        val telefone = profilePhone.text.toString()

        if (nome.isEmpty() || matricula.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Preencha os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        /*FirebaseAuth.getInstance().
                {
                    uploadImageToFirebaseStorage()
                }*/
    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    ref.downloadUrl
                            .addOnSuccessListener {
                        //saveUserToFireDatabase(it.toString())
                    }
                }
    }

    /*private fun saveUserToFireDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, profileName.text.toString(), pri profileImageUrl)

        ref.setValue(user)
                .addOnSuccessListener {
                    val intent = Intent(this, NewsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
    }*/
}