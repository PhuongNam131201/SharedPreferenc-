package com.example.sharedpreferences


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Bai4Activity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kiểm tra trạng thái đăng nhập
        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("IS_LOGGED_IN", false)) {
            goToMainActivity()
        }

        setContentView(R.layout.activity_bai4)

        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == "user" && password == "password") {
                saveLoginState()
                goToMainActivity()
            } else {
                Toast.makeText(this, "Tên người dùng hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveLoginState() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("IS_LOGGED_IN", true)
        editor.apply()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
