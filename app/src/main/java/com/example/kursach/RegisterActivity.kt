package com.example.kursach

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        val mailTextReg : EditText = findViewById(R.id.mailTextReg)
        val passwordTextReg : EditText = findViewById(R.id.passwordTextReg)
        val passwordAceptTextReg : EditText = findViewById(R.id.passwordAceptTextReg)
        val acceptBtnReg: Button = findViewById(R.id.acceptBtnReg)
        val authRe = FirebaseAuth.getInstance()
        acceptBtnReg.setOnClickListener {
            if(mailTextReg.text.toString().isEmpty()|| passwordTextReg.text.toString().isEmpty()|| passwordAceptTextReg.text.toString().isEmpty()){
                Toast.makeText(this, "Нельзя оставлять поля пустыми", Toast.LENGTH_SHORT).show()
            }
            else{
                if(passwordTextReg.text.toString()!=passwordAceptTextReg.text.toString()){
                    Toast.makeText(this, "Пароли не сходятся, повторите попытку", Toast.LENGTH_SHORT).show()

                }
                else{
                    authRe.createUserWithEmailAndPassword(mailTextReg.text.toString(), passwordTextReg.text.toString())
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(this, "Поздравляю ваш аккаунт создан", Toast.LENGTH_SHORT).show()
                                val intent = Intent(applicationContext, HomeActivity::class.java)
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(this, "Произошла какая то ошибка", Toast.LENGTH_SHORT).show()

                            }
                        }
                }
            }
        }
    }
}