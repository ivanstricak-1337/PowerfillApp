package com.powerfill.app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val dayPrice = findViewById<EditText>(R.id.dayPrice)
        val nightPrice = findViewById<EditText>(R.id.nightPrice)
        val dayStart = findViewById<EditText>(R.id.dayStart)
        val dayEnd = findViewById<EditText>(R.id.dayEnd)
        val nightStart = findViewById<EditText>(R.id.nightStart)
        val nightEnd = findViewById<EditText>(R.id.nightEnd)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        dayPrice.setText(Config.DAY_PRICE.toString())
        nightPrice.setText(Config.NIGHT_PRICE.toString())
        dayStart.setText(Config.DAY_START.toString())
        dayEnd.setText(Config.DAY_END.toString())
        nightStart.setText(Config.NIGHT_START.toString())
        nightEnd.setText(Config.NIGHT_END.toString())

        saveBtn.setOnClickListener {
            Config.DAY_PRICE = dayPrice.text.toString().toDoubleOrNull() ?: Config.DAY_PRICE
            Config.NIGHT_PRICE = nightPrice.text.toString().toDoubleOrNull() ?: Config.NIGHT_PRICE
            Config.DAY_START = dayStart.text.toString().toIntOrNull() ?: Config.DAY_START
            Config.DAY_END = dayEnd.text.toString().toIntOrNull() ?: Config.DAY_END
            Config.NIGHT_START = nightStart.text.toString().toIntOrNull() ?: Config.NIGHT_START
            Config.NIGHT_END = nightEnd.text.toString().toIntOrNull() ?: Config.NIGHT_END
            finish()
        }
    }
}
