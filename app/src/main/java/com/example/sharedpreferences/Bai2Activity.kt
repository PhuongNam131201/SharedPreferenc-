package com.example.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class Bai2Activity : AppCompatActivity() {

    private lateinit var darkModeSwitch: Switch
    private lateinit var radioGroupTextSize: RadioGroup
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitty_bai2)

        // Ánh xạ các thành phần UI
        darkModeSwitch = findViewById(R.id.switch_dark_mode)
        radioGroupTextSize = findViewById(R.id.radio_group_text_size)

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("UserSettings", MODE_PRIVATE)

        // Đọc và khôi phục cài đặt
        loadSettings()

        // Lắng nghe sự thay đổi của switch dark mode
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveDarkModeSetting(isChecked)
            setDarkMode(isChecked)
        }

        // Lắng nghe sự thay đổi của RadioGroup
        radioGroupTextSize.setOnCheckedChangeListener { _, checkedId ->
            saveTextSizeSetting(checkedId)
        }
    }

    private fun loadSettings() {
        // Khôi phục trạng thái chế độ tối
        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)
        darkModeSwitch.isChecked = isDarkMode
        setDarkMode(isDarkMode)

        // Khôi phục kích thước chữ
        val textSizeId = sharedPreferences.getInt("TEXT_SIZE", R.id.radio_text_size_medium)
        radioGroupTextSize.check(textSizeId)
    }

    private fun saveDarkModeSetting(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("DARK_MODE", isDarkMode)
        editor.apply()
    }

    private fun saveTextSizeSetting(checkedId: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("TEXT_SIZE", checkedId)
        editor.apply()
    }

    private fun setDarkMode(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
