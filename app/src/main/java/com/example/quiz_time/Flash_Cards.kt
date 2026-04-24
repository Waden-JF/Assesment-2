package com.example.quiz_time

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Flash_Cards : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_cards)

        val score = intent.getIntExtra("score", 0)
        val tvFinalScore: TextView = findViewById(R.id.tvFinalScore)
        val tvFeedback: TextView = findViewById(R.id.tvPersonalizedFeedback)
        val btnReview: Button = findViewById(R.id.btnReview)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        tvFinalScore.text = "Score: $score / 10"
        tvFeedback.text = when {
            score == 10 -> "Master Hacker! You're a true Master Hacker."
            score >= 7 -> "Great Job! You're a Master Hacker."
            score >= 5 -> "Not bad at all, Stay sharp"
            else -> "You can do better. Keep practicing!"
        }

        btnReview.setOnClickListener {
            val intent = Intent(this, Quiz_questions::class.java)
            startActivity(intent)
            finish()
        }

        btnRestart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
