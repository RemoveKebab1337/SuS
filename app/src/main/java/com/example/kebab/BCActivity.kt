package com.example.kebab


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import com.berlin.clock.controller.BerlinClockController
import com.berlin.clock.view.BerlinClockViewer
import java.text.SimpleDateFormat
import java.util.*

class BCActivity : AppCompatActivity() {
    private lateinit var timer:CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bc)
        val tvClock:TextView = findViewById(R.id.tv_text_clock)
        timer = createTimer(tvClock,window.decorView.findViewById(android.R.id.content))
        timer.start()
    }

    private fun createTimer(tvClock:TextView,parentView:View): CountDownTimer {
        return object: CountDownTimer(Long.MAX_VALUE, 1000){
            override fun onTick(millisUntilFinished: Long) {
                val currentDate = Date()
                val sdf = SimpleDateFormat("hh:mm:ss")
                val dateToText = sdf.format(currentDate)
                tvClock.text = dateToText
                val cal = Calendar.getInstance()
                cal.time = currentDate
                val lampLights = BerlinClockController(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND)).getBerlinClockLamps()
                BerlinClockViewer.viewClock(lampLights,baseContext,parentView)
            }
            override fun onFinish() {
                timer = createTimer(tvClock,parentView)
            }
        }
    }
}