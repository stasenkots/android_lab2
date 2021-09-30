package com.stasenkots.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity: AppCompatActivity() {
    private var question: TextView? = null
    private var submit: Button? = null
    private var cancel: Button? = null
    private var answer: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        question = findViewById(R.id.textQuestion)
        answer = findViewById(R.id.inputAnswer)
        submit = findViewById(R.id.buttonSubmitAnswer)
        cancel = findViewById(R.id.buttonCancel)

        question?.text = intent.extras?.getString(QUESTION_TEXT)

        submit?.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra(ANSWER_TEXT, answer?.text.toString())
            }
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        cancel?.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}