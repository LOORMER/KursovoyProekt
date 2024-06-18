package com.example.kursach

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class addTovar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_tovar)
        val idAddTovarText: EditText = findViewById(R.id.idAddTovarText)
        val nameAddTovarText: EditText = findViewById(R.id.nameAddTovarText)
        val ratingAddTovarText: EditText = findViewById(R.id.ratingAddTovarText)
        val commentAddTovarText: EditText = findViewById(R.id.commentsAddTovarText)

        val acceptAddTovarBtn: Button = findViewById(R.id.acceptAddTovarBtn)
        val cancelAddTovarBtn: Button = findViewById(R.id.cancelAddTovarBtn)

        val db = FirebaseDatabase.getInstance().getReference()


        cancelAddTovarBtn.setOnClickListener {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)

        }
        acceptAddTovarBtn.setOnClickListener {
            if(idAddTovarText.text.toString().isEmpty()|| nameAddTovarText.text.toString().isEmpty()|| ratingAddTovarText.text.toString().isEmpty()|| commentAddTovarText.text.toString().isEmpty()){ Toast.makeText(this, "ЗАПОЛНЯЙТЕ ПОЛЯ, ВЫ ЗАДОЛБАЛИ УЖЕ ТЕСТЕРЫ", Toast.LENGTH_SHORT)}
            else{
                db.child("Tovar").child(idAddTovarText.text.toString()).child("id").setValue(idAddTovarText.text.toString().toInt())
                db.child("Tovar").child(idAddTovarText.text.toString()).child("name").setValue(nameAddTovarText.text.toString())
                db.child("Tovar").child(idAddTovarText.text.toString()).child("rating").setValue(ratingAddTovarText.text.toString().toInt())
                db.child("Tovar").child(idAddTovarText.text.toString()).child("comments").setValue(commentAddTovarText.text.toString())
                db.child("Tovar").child(idAddTovarText.text.toString()).child("byTicket").setValue(false)

                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}