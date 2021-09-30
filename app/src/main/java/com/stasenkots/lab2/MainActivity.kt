package com.stasenkots.lab2

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

const val ANSWER_TEXT = "ANSWER_TEXT"
const val QUESTION_TEXT = "QUESTION_TEXT"

class MainActivity: AppCompatActivity() {

    private var question: EditText? = null
    private var submit: Button? = null
    private var lastAnswer: TextView? = null

    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK -> renderResultOk(result)
            RESULT_CANCELED -> renderResultCanceled()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        question = findViewById(R.id.inputQuestion)
        lastAnswer = findViewById(R.id.textAnswer)
        submit = findViewById(R.id.buttonSubmitQuestion)


        submit?.setOnClickListener {

            val questionText = question?.text.toString()

            val intent = Intent(
                this@MainActivity,
                QuestionActivity::class.java
            ).apply {
                 putExtra(QUESTION_TEXT,questionText)
            }
            someActivityResultLauncher.launch(intent)
        }
    }

    private fun renderResultOk(result: ActivityResult) {
        val data = result.data
        val answerString = data?.getStringExtra(ANSWER_TEXT).orEmpty()
        lastAnswer?.text = answerString
    }

    private fun renderResultCanceled() {
        lastAnswer?.text = getString(R.string.operation_was_canceled)
    }
}