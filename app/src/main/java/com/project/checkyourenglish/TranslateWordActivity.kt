package com.project.checkyourenglish

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TranslateWordActivity : AppCompatActivity() {
    val words: Array<Word> = arrayOf(
        Word("car", "samowchód"),
        Word("red", "czerwony"),
        Word("washing machine", "pralka"),
        Word("translator", "tłumacz"),
        Word("wallet", "portfel"),
        Word("armchair", "fotel"),
        Word("backpack", "plecak"),
        Word("blue", "niebieski")
    )

    var correctAnswere = StringBuilder("Poprawne odpowiedzi:")

    var wrongAnswere = StringBuilder("Złe dopowiedzi:")

    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate_word)

        val nick: TextView = findViewById(R.id.nick)
        val intent: Intent = intent
        val nickValue: String? = intent.getStringExtra("NICK")
        nick.text = nickValue
        showWord(index)
        index++

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener(backActivity)
        val nextButton: Button = findViewById(R.id.nextButton)
        nextButton.setOnClickListener(nextWordButton)
        val finishButton : Button = findViewById(R.id.finishButton)
        finishButton.setOnClickListener(finishActivity)
    }

    val finishActivity = View.OnClickListener { view: View ->
        var answere: EditText = findViewById(R.id.translateValue)
        if (index == words.size && !answere.text.toString().equals("")) {
            val finishWordActivity = Intent(this, FinishActivity::class.java)
            checkAnswere(words.size - 1, answere.text.toString())
            finishWordActivity.putExtra("CORRECT_ANSWERE", correctAnswere.toString())
            finishWordActivity.putExtra("WRONG_ANSWERE", wrongAnswere.toString())
            startActivity(finishWordActivity)
        } else {
            Toast.makeText(this, "You didn't translate all the words.", Toast.LENGTH_SHORT).show()
        }
    }

    val backActivity = View.OnClickListener { view: View ->
        val backIntent: Intent = Intent()
        backIntent.putExtra("WORD_BACK", "Your back from translate word your answers have not been saved.")
        setResult(RESULT_OK, backIntent)
        finish()
    }

    val nextWordButton = View.OnClickListener { view: View ->
        nextWord()
    }

    fun nextWord() {
        var answere: EditText = findViewById(R.id.translateValue)
        if (answere.text.toString().equals("")) {
            Toast.makeText(this, "Enter your answer", Toast.LENGTH_SHORT).show()
        } else if (index == words.size) {
            Toast.makeText(this, "This is the last word please click Finish", Toast.LENGTH_SHORT).show()
        } else {
            checkAnswere(index - 1, answere.text.toString())
            showWord(index)
            index++
        }
    }

    fun checkAnswere(questionIndex: Int, answereFromUser: String) {
        if (answereFromUser == words[questionIndex].wordInEnglish) {
            correctAnswere.append("\n ${words[questionIndex].wordInPolish} -> ${words[questionIndex].wordInEnglish}")
        } else {
            wrongAnswere.append("\n ${words[questionIndex].wordInPolish} -> ${words[questionIndex].wordInEnglish} Your answere: ${answereFromUser}")
        }
    }

    fun showWord(index: Int) {
        var word: TextView = findViewById(R.id.sentence)
        var answere: EditText = findViewById(R.id.translateValue)
        word.text = words[index].wordInPolish
        answere.text.clear()
    }
}

data class Word(
    val wordInEnglish: String,
    val wordInPolish: String
)