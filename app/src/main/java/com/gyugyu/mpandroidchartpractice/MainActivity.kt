package com.gyugyu.mpandroidchartpractice

import android.content.pm.ActivityInfo
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.components.XAxis
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
        val chart = binding.linechartMain.apply {
            isDragEnabled = false
            description = null
            setBackgroundColor(getColor(R.color.colorBackground))
            setScaleEnabled(false)
            setTouchEnabled(false)

            xAxis.run {
                isEnabled = true
                position = XAxis.XAxisPosition.BOTTOM_INSIDE
                axisLineColor = Color.BLACK
                setDrawAxisLine(true)
                setDrawGridLines(false)
            }

            axisLeft.run {
                isEnabled = true
                textColor = Color.BLACK
                axisLineColor = Color.BLACK
                labelCount = 0
                setDrawGridLines(false)
                setDrawAxisLine(true)
            }

            axisRight.isEnabled = false
        }

        val values = mutableListOf<Entry>()

        //init random values for vertices
        for (i in 0 until 10) {
            val value = Math.random().toFloat() * 10 - 5
            values.add(Entry(i.toFloat(), value))
        }

        //init data set
        val set = LineDataSet(values, "Dummy").apply {
            //customize appearance of chart
            color = Color.BLACK
            setDrawCircles(false)
            setDrawValues(false)
            label = null
            color
        }
        val dataSets = mutableListOf<ILineDataSet>().apply { add(set) }

        //set data to chart
        chart.data = LineData(dataSets)

        //change orientation of screen
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