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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
    private val auth  = FirebaseAuth.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login2)
        // Инициализация полей ввода, кнопки входа и текстового поля для создания нового аккаунта
        val loginBtn : Button = findViewById(R.id.loginBtn)
        val createAccText : TextView = findViewById(R.id.newAccText)
        val emailText : EditText = findViewById(R.id.loginText)
        val passwordText : EditText = findViewById(R.id.passwordText)

        // Установка обработчика нажатия на текстовое поле для создания нового аккаунта
        createAccText.setOnClickListener{
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Установка обработчика нажатия на кнопку входа
        loginBtn.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            // Проверка введенных данных и вход в систему
            when {
                email.isEmpty() || password.isEmpty() ->
                    showToast("Поле должно быть заполненым")
                else ->
                    try {
                        loginUser(email, password)
                    } catch (e: Exception) {
                        showToast("Произошла ошибка при входе в систему")
                    }
            }
        }


    }
    // Функция для отображения всплывающих сообщений
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    // Функция для входа в систему
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                }
                else
                    handleRegistrationError(it.exception)
            }
    }
    // Функция для обработки ошибок входа
    private fun handleRegistrationError(exception: Exception?) {
        try {
            throw exception!!
        } catch (e: FirebaseAuthInvalidUserException) {
            showToast("Пользователь не найден")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            showToast("Неверные учетные данные")
        } catch (e: Exception) {
            showToast("Ошибка регистрации")
        }
    }
}