package com.gyugyu.mpandroidchartpractice

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.gyugyu.mpandroidchartpractice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAX = 50F
    }

    private lateinit var binding: ActivityMainBinding
    private var currentOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    private lateinit var chart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //define
        chart = binding.linechartMain.apply {
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
                //set right padding of viewport
                //for put a last vertex on center of viewport
                spaceMax = MAX / 2
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

            //add a empty data
            data = LineData()
        }

        //change orientation of screen
        binding.run {
            imagebuttonOrientationMain.setOnClickListener {
                changeScreenMode()
            }
            imagebuttonAddVertexMain.setOnClickListener {
                addEntry(Math.random() * 10 - 5)
            }
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

    private fun addEntry(num: Double) {
        var data: LineData? = chart.data
        if (data == null) {
            data = LineData()
            chart.data = data
        }

        var set = data.getDataSetByIndex(0)
        // set.addEntry(...); // can be called as well
        if (set == null) {
            set = createSet()
            data.addDataSet(set)
        }

        data.run {
            //add random entry
            addEntry(Entry(set.entryCount.toFloat(), num.toFloat()), 0)
            notifyDataChanged()
        }

        refreshChart()
    }

    private fun refreshChart() {
        // let the chart know it's data has changed
        chart.run {
            notifyDataSetChanged()
            setVisibleXRangeMaximum(MAX)
            setVisibleXRangeMinimum(MAX)
            // this automatically refreshes the chart (calls invalidate())
            moveViewToX(data.entryCount.toFloat())
        }
    }

    private fun createSet(): LineDataSet {
        //init data set
        return LineDataSet(null, "Real-time Line Data").apply {
            //customize appearance of line
            color = Color.BLACK
            label = null
            setDrawCircles(false)
            setDrawValues(false)
        }
    }
}