package com.example.room_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.room_exam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()

        binding.resultText.text = db.TodoDao().getAll().toString()

        binding.addButton.setOnClickListener {
            db.TodoDao().insert(Todo(binding.todoEdit.text.toString()))
            binding.resultText.text = db.TodoDao().getAll().toString()
        }

        db.TodoDao().getAll()
    }
}
