package com.project.checkyourenglish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton: Button = findViewById(R.id.play)
        startButton.setOnClickListener(start)
    }

    val start = View.OnClickListener { view: View ->
        val chooseSpinner: Spinner = findViewById(R.id.type)
        val chooseValue: String = chooseSpinner.selectedItem.toString()
        val nick: EditText = findViewById(R.id.nameValue)
        val nickValue: String = nick.text.toString()
        if (nickValue != "") {
            when (chooseValue) {
                "Choose" -> {
                    Toast.makeText(this, "Complete form!", Toast.LENGTH_SHORT).show()
                }
                "Translate the word" -> {
                    translateWordActivity(nickValue)
                }
                "Complete the sentence" -> {
                    gramaActivity(nickValue)
                }
            }
        } else {
            Toast.makeText(this, "Complete form!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val backValue: TextView = findViewById(R.id.backMessage)
        var message: String? = ""
        when (requestCode) {
            1 -> {
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        message = data.getStringExtra("WORD_BACK")
                    }
                }
            }
            2 -> {
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        message = data.getStringExtra("GRAMA_BACK")
                    }
                }
            }
        }
        backValue.text = message
    }

    fun translateWordActivity(nickValue: String) {
        val translateWordActivity = Intent(this, TranslateWordActivity::class.java)
        translateWordActivity.putExtra("NICK", nickValue)
        startActivityForResult(translateWordActivity, 1)
    }

    fun gramaActivity(nickValue: String) {
        val gramaActivity = Intent(this, GramaActivity::class.java)
        gramaActivity.putExtra("NICK", nickValue)
        startActivityForResult(gramaActivity, 2)
    }
}