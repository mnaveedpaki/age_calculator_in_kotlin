package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var tvSelectedDate : TextView? = null
    var tvSelectedDateInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)
        val btnDatePicker = findViewById(R.id.btnDatePicker) as Button
        btnDatePicker.setOnClickListener{
            clickDatePicker(it)
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clickDatePicker(it: View?) {

        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val  day = myCalander.get(Calendar.DAY_OF_MONTH)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
           var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${selectedMonth+1}/$selectedYear";

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val seletedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateInMinutes - seletedDateInMinutes
                tvSelectedDateInMinutes?.text = differenceInMinutes.toString()
            },
                year,
                month,
                day)

            dpd.datePicker.setMaxDate(Date().time - 86400000)
            dpd.show()
        }

    }
}