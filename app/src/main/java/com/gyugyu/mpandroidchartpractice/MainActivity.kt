package com.gyugyu.mpandroidchartpractice

import android.content.pm.ActivityInfo
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.gyugyu.mpandroidchartpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //define
        val chart = binding.linechartMain
        val values = mutableListOf<Entry>()

        //init random values for vertices
        for (i in 0 until 10) {
            val value = Math.random().toFloat() * 10
            values.add(Entry(i.toFloat(), value))
        }

        //init data set
        val set = LineDataSet(values, "DataSet 1")
        val dataSets = mutableListOf<ILineDataSet>().apply { this.add(set) }
        val data = LineData(dataSets)

        //customize appearance of chart
        set.run {
            color = Color.BLACK
            setCircleColor(Color.BLACK)
        }

        //set data to chart
        chart.data = data

        binding.imagebuttonOrientationMain.setOnClickListener {
            changeScreenMode()
        }
    }

    private fun changeScreenMode() {
        requestedOrientation =
            if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        currentOrientation = requestedOrientation
    }
}