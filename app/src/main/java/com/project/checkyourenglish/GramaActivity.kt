package com.project.checkyourenglish

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GramaActivity : AppCompatActivity() {
    val sentences: Array<Sentence> = arrayOf(
        Sentence("Where (you / be) ……………………all this time? I’ve been looking everywhere for you!", "have you been"),
        Sentence("They (watch) ……… a comedy when suddenly the light went out.", "were watching"),
        Sentence("Mike (want / buy) …………… a boat so he’s saving up some money", "wants to buy"),
        Sentence("Why (he / pack) ………………his suitcase now? Is he going away?", "is he packing"),
        Sentence("This bag is not mine, it’s (they) ……… .", "theirs"),
        Sentence("While the kids (play) ……… in the living room, their mum was baking cookies for them.", "were playing")
    )

    var correctAnswere = StringBuilder("Poprawne odpowiedzi:")

    var wrongAnswere = StringBuilder("Złe dopowiedzi:")

    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grama)

        val nick: TextView = findViewById(R.id.nick)
        val intent: Intent = intent
        val nickValue: String? = intent.getStringExtra("NICK")
        nick.text = nickValue
        showSentence(index)
        index++

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener(backActivity)
        val nextButton: Button = findViewById(R.id.nextButton)
        nextButton.setOnClickListener(nextSentenceButton)
        val finishButton: Button = findViewById(R.id.finishButton)
        finishButton.setOnClickListener(finishActivity)
    }

    val backActivity = View.OnClickListener { view: View ->
        val backIntent: Intent = Intent()
        backIntent.putExtra(
            "GRAMA_BACK",
            "Your back from grama activity your answers have not been saved."
        )
        setResult(RESULT_OK, backIntent)
        finish()
    }

    val finishActivity = View.OnClickListener { view: View ->
        var answere: EditText = findViewById(R.id.translateValue)
        if (index == sentences.size && !answere.text.toString().equals("")) {
            val finishWordActivity = Intent(this, FinishActivity::class.java)
            checkAnswere(sentences.size - 1, answere.text.toString())
            finishWordActivity.putExtra("CORRECT_ANSWERE", correctAnswere.toString())
            finishWordActivity.putExtra("WRONG_ANSWERE", wrongAnswere.toString())
            startActivity(finishWordActivity)
        } else {
            Toast.makeText(this, "Complete all sentences.", Toast.LENGTH_SHORT).show()
        }
    }

    val nextSentenceButton = View.OnClickListener { view: View ->
        nextSentence()
    }

    fun nextSentence() {
        var answere: EditText = findViewById(R.id.translateValue)
        if (answere.text.toString().equals("")) {
            Toast.makeText(this, "Enter your answer", Toast.LENGTH_SHORT).show()
        } else if (index == sentences.size) {
            Toast.makeText(this, "This is the last word please click Finish", Toast.LENGTH_SHORT)
                .show()
        } else {
            checkAnswere(index - 1, answere.text.toString())
            showSentence(index)
            index++
        }
    }

    fun checkAnswere(questionIndex: Int, answereFromUser: String) {
        if (answereFromUser == sentences[questionIndex].coreectrAnswere) {
            correctAnswere.append("\n ${sentences[questionIndex].sentence} -> ${sentences[questionIndex].coreectrAnswere}")
        } else {
            wrongAnswere.append("\n ${sentences[questionIndex].sentence} -> ${sentences[questionIndex].coreectrAnswere} Your answere: ${answereFromUser}")
        }
    }

    fun showSentence(index: Int) {
        var sentence: TextView = findViewById(R.id.sentence)
        var answere: EditText = findViewById(R.id.translateValue)
        sentence.text = sentences[index].sentence
        answere.text.clear()
    }
}

data class Sentence(
    val sentence: String,
    val coreectrAnswere: String
)