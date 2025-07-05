package com.example.gamemath

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    lateinit var current : TextView
    lateinit var best : TextView
    lateinit var exit : Button
    lateinit var again : Button
    lateinit var helper : FileHelper
    lateinit var his : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        current = findViewById(R.id.current)
        best = findViewById(R.id.best)
        exit = findViewById(R.id.exit)
        again = findViewById(R.id.again)
        his = findViewById(R.id.his)
        helper = FileHelper()

        var scores = helper.read(this,"Scored.dat")
        var correct = helper.read(this,"Corrected.dat")
        var wrong = helper.read(this,"Wrongs.dat")


        val currentScore = intent.getStringExtra("current")?.toIntOrNull() ?: 0
        val correctAnswer = intent.getStringExtra("Correct")?.toIntOrNull() ?: 0

        val wrongAnswer = intent.getStringExtra("Wrong")?.toIntOrNull() ?: 0
        current.text = "Your current score is $currentScore"
        scores.add(currentScore)
        correct.add(correctAnswer)
        wrong.add(wrongAnswer)
        helper.write(correct, this, "Corrected.dat")
        helper.write(wrong, this, "Wrongs.dat")

        val bestScore = scores.maxOrNull() ?: 0
        best.text = "Your best score is $bestScore"
        helper.write(scores, this, "Scored.dat")


        again.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        his.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }



    }
}