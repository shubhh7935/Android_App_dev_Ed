package com.example.androidapplicationdevelopment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidapplicationdevelopment.R.id
import com.example.androidapplicationdevelopment.UNITS.Viewsandcomponents

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the button by its ID
        val startButton: Button = findViewById(id.startBtn)

        // Set a click listener on the button
        startButton.setOnClickListener {
            // Create an Intent to start the Viewsandcomponents activity
            val intent = Intent(this, Viewsandcomponents::class.java)
            // Start the activity
            startActivity(intent)
        }
    }
}