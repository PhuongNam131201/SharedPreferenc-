package com.example.sharedpreferences

import androidx.appcompat.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Bai5Activity : AppCompatActivity() {

    private lateinit var etTask: EditText
    private lateinit var btnAdd: Button
    private lateinit var lvTasks: ListView
    private lateinit var tasks: MutableList<Task>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai5)

        etTask = findViewById(R.id.et_task)
        btnAdd = findViewById(R.id.btn_add)
        lvTasks = findViewById(R.id.lv_tasks)

        sharedPreferences = getSharedPreferences("TaskPreferences", MODE_PRIVATE)

        tasks = loadTasks()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks.map { it.title }.toMutableList())
        lvTasks.adapter = adapter

        btnAdd.setOnClickListener {
            val taskTitle = etTask.text.toString()
            if (taskTitle.isNotEmpty()) {
                val task = Task(taskTitle)
                tasks.add(task)
                adapter.add(task.title)
                etTask.text.clear()
                saveTasks()
            }
        }

        lvTasks.setOnItemLongClickListener { _, _, position, _ ->
            val task = tasks[position]
            showEditDeleteDialog(task, position)
            true
        }
    }

    private fun showEditDeleteDialog(task: Task, position: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Chỉnh sửa hoặc xóa tác vụ")
            .setMessage("Bạn muốn làm gì với tác vụ này?")
            .setPositiveButton("Chỉnh sửa") { _, _ ->
                showEditTaskDialog(task, position)
            }
            .setNegativeButton("Xóa") { _, _ ->
                tasks.removeAt(position)
                adapter.remove(task.title)
                saveTasks()
            }
            .create()
        dialog.show()
    }

    private fun showEditTaskDialog(task: Task, position: Int) {
        val input = EditText(this)
        input.setText(task.title)
        AlertDialog.Builder(this)
            .setTitle("Chỉnh sửa Tác Vụ")
            .setView(input)
            .setPositiveButton("Lưu") { _, _ ->
                val newTitle = input.text.toString()
                if (newTitle.isNotEmpty()) {
                    tasks[position].title = newTitle
                    adapter.remove(adapter.getItem(position))
                    adapter.insert(newTitle, position)
                    saveTasks()
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    private fun saveTasks() {
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(tasks)
        editor.putString("TASK_LIST", json)
        editor.apply()
    }

    private fun loadTasks(): MutableList<Task> {
        val json = sharedPreferences.getString("TASK_LIST", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Task>>() {}.type
        return Gson().fromJson(json, type)
    }
}
