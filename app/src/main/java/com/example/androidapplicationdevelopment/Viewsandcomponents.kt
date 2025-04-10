package com.example.androidapplicationdevelopment

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.androidapplicationdevelopment.R

class Viewsandcomponents : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    var isProgressVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewsandcomponents)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.myToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Unit 1: Views & Components"

        // RatingBar
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val ratingButton = findViewById<Button>(R.id.btnRating)
        ratingButton.setOnClickListener {
            val rating = ratingBar.rating
            Toast.makeText(this, "You rated: $rating stars", Toast.LENGTH_SHORT).show()
        }

        // ProgressBar Toggle
        progressBar = findViewById(R.id.progressBar)
        val progressBtn = findViewById<Button>(R.id.btnProgress)
        progressBtn.setOnClickListener {
            isProgressVisible = !isProgressVisible
            progressBar.visibility = if (isProgressVisible) ProgressBar.VISIBLE else ProgressBar.GONE
        }

        // Custom Toast
        val btnToast = findViewById<Button>(R.id.btnCustomToast)
        btnToast.setOnClickListener {
            val inflater = layoutInflater
            val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.customToastContainer))

            val toast = Toast(applicationContext)
            toast.duration = Toast.LENGTH_LONG
            toast.view = layout
            toast.show()
        }
    }
}
