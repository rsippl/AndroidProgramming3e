package com.bignerdranch.android.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        true_button.setOnClickListener {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }

        false_button.setOnClickListener {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }
    }
}
