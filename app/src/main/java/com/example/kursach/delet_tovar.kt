package com.example.kursach

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class delet_tovar : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delet_tovar)
        val acceptDelBtn : Button = findViewById(R.id.acceptDelBtn)
        val editDelete : EditText = findViewById(R.id.editDel)
        val cancel : Button = findViewById(R.id.canceling)
        val ref = FirebaseDatabase.getInstance().getReference()
        acceptDelBtn.setOnClickListener{
            ref.child("Hotels").child(editDelete.text.toString()).removeValue()
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }
        cancel.setOnClickListener {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}