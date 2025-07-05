package com.example.gamemath

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity4 : AppCompatActivity() {
    lateinit var list : RecyclerView
    lateinit var adapter: RecyleAdapter
    lateinit var helper: FileHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        helper = FileHelper()
         var score : ArrayList<Int>
         var correct : ArrayList<Int>
         var wrong : ArrayList<Int>

        list = findViewById(R.id.recyclerView2)
        list.layoutManager = LinearLayoutManager(this)

         score = helper.read(this , "Scored.dat")
         correct = helper.read(this , "Corrected.dat")
         wrong = helper.read(this , "Wrongs.dat")
        Toast.makeText(this , "${correct[correct.size-1]} // ${wrong[wrong.size-1]}// ${score[score.size-1]}", Toast.LENGTH_SHORT).show()


        list = findViewById(R.id.recyclerView2)
        adapter = RecyleAdapter(score,correct,wrong,this)
        list.adapter = adapter




    }
}