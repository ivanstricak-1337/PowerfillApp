package com.powerfill.app
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[MainViewModel::class.java]

        val power = findViewById<TextView>(R.id.power)
        val energy = findViewById<TextView>(R.id.energy)
        val cost = findViewById<TextView>(R.id.cost)
        val status = findViewById<TextView>(R.id.status)
        val btnStart = findViewById<Button>(R.id.start)
        val btnStop = findViewById<Button>(R.id.stop)
        val btnSettings = findViewById<Button>(R.id.settings)
        val btnHistory = findViewById<Button>(R.id.history)

        vm.power.observe(this) { power.animateNumber(0.0,it," kW") }
        vm.energy.observe(this) { energy.animateNumber(0.0,it," kWh") }
        vm.cost.observe(this) { cost.animateNumber(0.0,it," €") }
        vm.status.observe(this) { status.text = it }

        btnStart.setOnClickListener { vm.start("YOUR_CHARGER_ID") }
        btnStop.setOnClickListener { vm.stop("YOUR_SESSION_ID") }
        btnSettings.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }
        btnHistory.setOnClickListener { startActivity(Intent(this, HistoryActivity::class.java)) }
    }
}
