package com.example.gamemath

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    lateinit var question : TextView
    lateinit var answer   : TextView
    lateinit var arrow_back : ImageView
    lateinit var ope : TextView
    lateinit var OK : Button
    lateinit var Next : Button
    lateinit var end : Button
    var scores: Int = 0
    var life : Int = 3
    lateinit var score : TextView
    lateinit var lifes : TextView
    lateinit var timer : CountDownTimer
    private val Start_time : Long = 20000
    var time_left : Long = Start_time
    lateinit var tm : TextView
    var submited : Boolean? = null
    var correct : Int = 0
    var un_correct : Int = 0


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ope = findViewById(R.id.textView)
        var op : String = intent.getStringExtra("op").toString()
        when(op){
            "add" -> ope.text = "Addition"
            "sub" -> ope.text = "Substruction"
            "mul" -> ope.text = "Multiplication"
        }

        arrow_back = findViewById(R.id.imageView)
        question = findViewById(R.id.textView3)
        answer = findViewById(R.id.editTextText)
        OK = findViewById(R.id.OK)
        Next = findViewById(R.id.Next)
        score = findViewById(R.id.sc)
        lifes = findViewById(R.id.lf)
        tm = findViewById(R.id.time)
        end = findViewById(R.id.end)

        lifes.text = "3"


         submited = false





        var result : Int =create_question(op)


        OK.setOnClickListener {
          if(answer.text.isEmpty()) {
              Toast.makeText(this, "You should enter an answer", Toast.LENGTH_SHORT).show()
          }else {



              try {
                  if(submited as Boolean) {
                      Toast.makeText(this,"You have already submitted an answer", Toast.LENGTH_SHORT).show()
                  }else {
                  if(answer.text.toString().toInt() == result) {
                      pause_timer()
                      scores += 100

                      question.text = "Congratulations, your answer is correct"
                      score.text = "" + scores
                      correct++
                  }else {
                      pause_timer()
                      life--
                      scores -= 50
                      lifes.text = "" + life
                      score.text = "" + scores
                      question.text = "Sorry, your answer is wrong"
                      un_correct ++
                  }

                  submited = true
              } }catch (e: NumberFormatException) {
                  Toast.makeText(this, "You should enter a number", Toast.LENGTH_SHORT).show()
              }

          }
        }
        Next.setOnClickListener {
            if(submited as Boolean) {
                if(life > 0) {
                answer.text = ""
                    pause_timer()
                    reset_timer()
                result = create_question(op)
                submited = false


                }else {
                    Toast.makeText(this , "Game Over", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity3::class.java)
                    intent.putExtra("current", scores.toString())
                    intent.putExtra("Correct",correct.toString())
                    intent.putExtra("Wrong",un_correct.toString())
                    intent.putExtra("Operation",op)
                    startActivity(intent)
                    finish()
                }
            }else{
                Toast.makeText(this, "You should submit an answer first", Toast.LENGTH_SHORT).show()
            }
        }

        end.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("current", scores.toString())
            intent.putExtra("Correct",correct.toString())
            intent.putExtra("Wrong",un_correct.toString())

            startActivity(intent)
            finish()
        }

        arrow_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun start_timer(){
        timer = object : CountDownTimer(time_left, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time_left = millisUntilFinished
                update_text()

            }

            override fun onFinish() {
                pause_timer()
                reset_timer()
                update_text()

                life --
                lifes.text = "" + life

                question.text = "Sorry , you ran out of time"
                submited = true
            }

        }.start()
    }

    fun create_question(op : String) : Int {
        var num1 = (1..100).random()
        var num2 = (1..100).random()
        start_timer()
        when(op){
            "add" -> {
                question.text = "$num1 + $num2 = "
                return num1 + num2
            }
            "sub" -> {
                question.text = "$num1 - $num2 = "
                return num1 - num2
            }
            "mul" -> {
                question.text = "$num1 * $num2 = "
                return num1 * num2
            }
        }
        return 0
    }
    fun update_text(){
        tm.text = (time_left/1000).toString()
    }
    fun reset_timer(){
        time_left = Start_time
        update_text()
    }
    fun pause_timer(){
        timer.cancel()
    }
    }
