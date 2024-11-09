package com.example.sharedpreferences


import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var btnLogout: Button
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tvWelcome = findViewById(R.id.tv_welcome)
        btnLogout = findViewById(R.id.btn_logout)
        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE)

        tvWelcome.text = "Chào mừng bạn đã đăng nhập!"

        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("IS_LOGGED_IN", false)
        editor.apply()

        startActivity(Intent(this, Bai4Activity::class.java))
        finish()
    }
}
