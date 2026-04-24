package com.example.quiz_time

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        btnStart.setOnClickListener {
            val intent = Intent(this, Quiz_questions::class.java)
            startActivity(intent)
        }
    }
}
