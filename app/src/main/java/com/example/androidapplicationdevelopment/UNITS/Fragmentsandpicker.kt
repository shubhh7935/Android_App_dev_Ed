package com.example.androidapplicationdevelopment.UNITS

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidapplicationdevelopment.DynamicFragment
import com.example.androidapplicationdevelopment.R
import java.util.*

class Fragmentsandpicker : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentsandpicker)

        val btnAddFragment = findViewById<Button>(R.id.btnAddFragment)
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        val btnTimePicker = findViewById<Button>(R.id.btnTimePicker)


        // 📌 Add dynamic fragment on button click
        btnAddFragment.setOnClickListener {
            val fragment: Fragment = DynamicFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.dynamicFragmentContainer, fragment)
                .commit()
        }

        // 📅 Show Date Picker
        btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
                Toast.makeText(this, "Selected Date: $d/${m + 1}/$y", Toast.LENGTH_SHORT).show()
            }, year, month, day)

            datePickerDialog.show()
        }

        // 🕒 Show Time Picker
        btnTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, { _, h, m ->
                Toast.makeText(this, "Selected Time: $h:$m", Toast.LENGTH_SHORT).show()
            }, hour, minute, true)

            timePickerDialog.show()
        }
    }
}
