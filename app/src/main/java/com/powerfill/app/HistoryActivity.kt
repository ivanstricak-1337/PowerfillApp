package com.powerfill.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    private lateinit var chart: LineChart
    private lateinit var recyclerView: RecyclerView
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        chart = findViewById(R.id.chart)
        recyclerView = findViewById(R.id.historyRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        vm = ViewModelProvider(this)[MainViewModel::class.java]

        vm.history.observe(this) { sessions ->
            // update recycler
            recyclerView.adapter = HistoryAdapter(sessions)

            // update chart
            val entries = sessions.mapIndexed { i, s -> Entry(i.toFloat(), s.energy.toFloat()) }
            val dataSet = LineDataSet(entries, "kWh").apply {
                color = 0xFF00E676.toInt()
                valueTextColor = 0xFFFFFFFF.toInt()
                lineWidth = 2f
                circleRadius = 3f
            }
            chart.data = LineData(dataSet)
            chart.invalidate()
        }
    }
}
