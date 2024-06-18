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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Инициализация полей ввода и кнопки регистрации
        val mailTextReg : EditText = findViewById(R.id.mailTextReg)
        val passwordTextReg : EditText = findViewById(R.id.passwordTextReg)
        val passwordAcceptTextReg : EditText = findViewById(R.id.passwordAceptTextReg)
        val acceptBtnReg: Button = findViewById(R.id.acceptBtnReg)

        // Установка обработчика нажатия на кнопку регистрации
        acceptBtnReg.setOnClickListener {
            // Получение введенных пользователем данных
            val email = mailTextReg.text.toString()
            val password = passwordTextReg.text.toString()
            val passwordConfirm = passwordAcceptTextReg.text.toString()

            // Проверка введенных данных и регистрация пользователя
            when {
                // Если какое-либо из полей пустое, показываем сообщение
                email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() ->
                    showToast("Нельзя оставлять поля пустыми")
                // Если пароли не совпадают, показываем сообщение
                password != passwordConfirm ->
                    showToast("Пароли не сходятся, повторите попытку")
                // Если все данные введены корректно, регистрируем пользователя
                else ->
                    registerUser(email, password)
            }
        }
    }
    // Функция для отображения всплывающих сообщений
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    // Функция для регистрации пользователя
    private fun registerUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Поздравляю ваш аккаунт создан")
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                } else // Если при регистрации возникла ошибка, обрабатываем ее
                    handleRegistrationError(task.exception)
            }
    }
    // Функция для обработки ошибок регистрации
    private fun handleRegistrationError(exception: Exception?) {
        try {
            throw exception!!
        } catch (e: FirebaseAuthWeakPasswordException) {
            showToast("Слабый пароль")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            showToast("Неверные учетные данные")
        } catch (e: FirebaseAuthUserCollisionException) {
            showToast("Пользователь уже существует")
        } catch (e: Exception) {
            showToast("Ошибка регистрации")
        }
    }

}