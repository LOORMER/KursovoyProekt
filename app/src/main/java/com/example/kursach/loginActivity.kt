package com.example.kursach

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login2)
        val aceptBtn : Button = findViewById(R.id.loginBtn)
        val createText : TextView = findViewById(R.id.newAccText)
        val loginText : EditText = findViewById(R.id.loginText)
        val passText : EditText = findViewById(R.id.passwordText)
        val auth  = FirebaseAuth.getInstance()
        createText.setOnClickListener{
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)

        }

        aceptBtn.setOnClickListener {
            if(loginText.text.toString().isEmpty() || passText.text.toString().isEmpty()){

                Toast.makeText(this, "Поле должно быть заполненым", Toast.LENGTH_SHORT).show()

            }
            else{
                auth.signInWithEmailAndPassword(loginText.text.toString(), passText.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this, "Логин или пароль не верны", Toast.LENGTH_SHORT).show()

                        }

                    }
            }
        }
    }
}