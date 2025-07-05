package com.example.gamemath

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var Add : Button
    lateinit var Sub : Button
    lateinit var Mul : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Add = findViewById(R.id.add)
        Sub = findViewById(R.id.sub)
        Mul = findViewById(R.id.mul)

        Add.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("op", "add")
            startActivity(intent)
        }
        Sub.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("op", "sub")
            startActivity(intent)
        }
        Mul.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("op","mul")
            startActivity(intent)
        }


    }
}