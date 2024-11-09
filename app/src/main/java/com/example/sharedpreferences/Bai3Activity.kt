package com.example.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Bai3Activity : AppCompatActivity() {

    private lateinit var tvOpenCount: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai3)

        // Ánh xạ TextView
        tvOpenCount = findViewById(R.id.tv_open_count)

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)

        // Lấy số lần mở hiện tại từ SharedPreferences
        val openCount = incrementOpenCount()

        // Hiển thị số lần mở ứng dụng
        tvOpenCount.text = "Bạn đã mở ứng dụng $openCount lần"
    }

    private fun incrementOpenCount(): Int {
        // Lấy số lần mở hiện tại
        var openCount = sharedPreferences.getInt("OPEN_COUNT", 0)

        // Tăng số lần mở lên 1
        openCount++

        // Lưu lại số lần mở mới vào SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putInt("OPEN_COUNT", openCount)
        editor.apply()

        return openCount
    }
}
