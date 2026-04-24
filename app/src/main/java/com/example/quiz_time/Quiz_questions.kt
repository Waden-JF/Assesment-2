package com.example.quiz_time

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Quiz_questions : AppCompatActivity() {

    private val flashCards = listOf(
        Hack("Put a lithium-ion battery in the freezer to charge it.", false, "This can actually harm the battery cells."),
        Hack("Use a binder clip to organize charging cables on a desk.", true, "Binder clips keep cables from slipping off the edge."),
        Hack("Microwaving a sponge for 1 minute kills 99% of bacteria.", true, "The heat effectively sanitizes the sponge."),
        Hack("Charge your phone to 100% every night to save battery life.", false, "Keeping it between 20%-80% is better for long term health"),
        Hack("Using a tennis ball to open a car door.", false, "This is a famous internet myth; it doesn't work."),
        Hack("Rub a walnut on scratched wood to hide the mark.", true, "The natural oils in the nut fill and hide the scratch."),
        Hack("Typing your PIN backwards at an ATM alerts the police.", false, "This is a famous myth; it doesn't work."),
        Hack("Use toothpaste to clear foggy car headlights.", true, "The abrasives in toothpaste polish the plastic surface."),
        Hack("Drinking coffee after alcohol helps sober you up.", false, "Caffeine makes you alert but doesn't help sober you up."),
        Hack("Put a wooden spoon over a boiling pot to stop it overflowing.", true, "The spoon breaks the surface tension of the bubbles.")
    )

    private var currentCardIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
        val tvProgress: TextView = findViewById(R.id.tvProgress)
        val tvFeedback: TextView = findViewById(R.id.tvFeedback)
        val btnFact: Button = findViewById(R.id.btnFact)
        val btnMyth: Button = findViewById(R.id.btnMyth)
        val btnNext: Button = findViewById(R.id.btnNext)

        fun loadCard() {
            val currentCard = flashCards[currentCardIndex]
            tvQuestion.text = currentCard.statement
            tvProgress.text = "Question ${currentCardIndex + 1}/${flashCards.size}"
            tvFeedback.visibility = View.INVISIBLE
            btnNext.isEnabled = false
            btnFact.isEnabled = true
            btnMyth.isEnabled = true
        }

        loadCard()

        val onClickListener = View.OnClickListener { view ->
            val currentCard = flashCards[currentCardIndex]
            val userChoseFact = view.id == R.id.btnFact
            val isCorrect = userChoseFact == currentCard.isHack

            if (isCorrect) {
                score++
                tvFeedback.text = "Correct! ${currentCard.explanation}"
            } else {
                tvFeedback.text = "Incorrect! ${currentCard.explanation}"
            }

            tvFeedback.visibility = View.VISIBLE
            btnNext.isEnabled = true
            btnFact.isEnabled = false
            btnMyth.isEnabled = false
        }

        btnFact.setOnClickListener(onClickListener)
        btnMyth.setOnClickListener(onClickListener)

        btnNext.setOnClickListener {
            if (currentCardIndex < flashCards.size - 1) {
                currentCardIndex++
                loadCard()
            } else {
                val intent = Intent(this, Flash_Cards::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
    }
}
