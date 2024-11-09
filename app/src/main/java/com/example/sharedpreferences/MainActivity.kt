package com.example.sharedpreferences



import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnRetrieve = findViewById<Button>(R.id.btnRetrieve)

        // Lưu dữ liệu vào SharedPreferences
        btnSave.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isNotEmpty()) {
                val sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("saved_text", inputText)
                    apply()
                }
                Toast.makeText(this, "Dữ liệu đã được lưu", Toast.LENGTH_SHORT).show()
                editText.text.clear()
            } else {
                Toast.makeText(this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show()
            }
        }

        // Lấy dữ liệu từ SharedPreferences
        btnRetrieve.setOnClickListener {
            val sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
            val savedText = sharedPref.getString("saved_text", null)
            if (savedText != null) {
                Toast.makeText(this, "Dữ liệu đã lưu: $savedText", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Không có dữ liệu nào được lưu", Toast.LENGTH_SHORT).show()
            }
        }
        val bai2 = findViewById<Button>(R.id.btnBai2)
        val bai3=findViewById<Button>(R.id.btnBai3)
        val bai4 = findViewById<Button>(R.id.btnBai4)
        val bai5 = findViewById<Button>(R.id.btnBai5)
        bai2.setOnClickListener{
            val a = Intent(this, Bai2Activity::class.java)
            startActivity(a)
        }
        bai3.setOnClickListener{
            val a = Intent(this, Bai3Activity::class.java)
            startActivity(a)
        }
        bai4.setOnClickListener{
            val a = Intent(this, Bai4Activity::class.java)
            startActivity(a)
        }
        bai5.setOnClickListener{
            val a = Intent(this, Bai5Activity::class.java)
            startActivity(a)
        }
    }
}
