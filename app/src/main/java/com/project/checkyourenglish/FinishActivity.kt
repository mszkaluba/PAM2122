package com.project.checkyourenglish

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FinishActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        val inntent: Intent = intent
        val correctAnswere: TextView = findViewById(R.id.correctAnswers)
        val wrongAnswere: TextView = findViewById(R.id.wrongAnswers)
        val correctAnsewreValue: String? = inntent.getStringExtra("CORRECT_ANSWERE")
        val wrongAnsewreValue: String? = inntent.getStringExtra("WRONG_ANSWERE")
        correctAnswere.text = correctAnsewreValue
        wrongAnswere.text = wrongAnsewreValue
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener(backActivity)
    }

    val backActivity = View.OnClickListener { view: View ->
        val backIntent: Intent = Intent(this, MainActivity::class.java)
        startActivity(backIntent)
    }
}