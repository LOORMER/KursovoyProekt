package com.example.kursach

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val authMain = FirebaseAuth.getInstance()
        if(authMain.currentUser!= null){
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }


        val vhodBtn : Button = findViewById(R.id.vhodBtn)
        vhodBtn.setOnClickListener{
            val intent = Intent(applicationContext, loginActivity::class.java)
            startActivity(intent)
        }
        val registrBtn : Button = findViewById(R.id.registrBtn)
        registrBtn.setOnClickListener{
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
