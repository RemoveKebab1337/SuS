package com.berlin.clock.view

import android.content.Context
import android.view.View
import com.berlin.clock.model.ClockLamps
import com.berlin.clock.model.LampColor
import com.example.kebab.R

object BerlinClockViewer {

    fun viewClock(clockLamps: ClockLamps, context: Context, parentView: View) {
        viewSecondsLamp(clockLamps.secondsLamp, context, parentView)
        viewOneMinuteLamps(clockLamps.oneMinutesLamps, context, parentView)
        viewFiveMinutesLamps(clockLamps.fiveMinutesLamps, context, parentView)
        viewOneHourLamps(clockLamps.oneHourLamps, context, parentView)
        viewFiveHoursLamps(clockLamps.fiveHoursLamps, context, parentView)
    }

    private fun viewFiveHoursLamps(
        fiveHoursLamps: Array<LampColor>,
        context: Context,
        parentView: View
    ) {
        val fiveMinutesLampTemplateID = "vi_five_hours_lamp_"
        illuminateLamps(fiveMinutesLampTemplateID, fiveHoursLamps, context, parentView)
    }

    private fun viewOneHourLamps(
        onHoursLamps: Array<LampColor>,
        context: Context,
        parentView: View
    ) {
        val fiveMinutesLampTemplateID = "vi_one_hour_lamp_"
        illuminateLamps(fiveMinutesLampTemplateID, onHoursLamps, context, parentView)
    }

    private fun viewFiveMinutesLamps(
        fiveMinutesLamps: Array<LampColor>,
        context: Context,
        parentView: View
    ) {
        val fiveMinutesLampTemplateID = "vi_five_minutes_lamp_"
        illuminateLamps(fiveMinutesLampTemplateID, fiveMinutesLamps, context, parentView)
    }

    private fun viewOneMinuteLamps(
        oneMinutesLamps: Array<LampColor>,
        context: Context,
        parentView: View
    ) {
        val oneMinuteLampTemplateID = "vi_one_minute_lamp_"
        illuminateLamps(oneMinuteLampTemplateID, oneMinutesLamps, context, parentView)
    }

    private fun viewSecondsLamp(secondsLampColor: LampColor, context: Context, parentView: View) {
        val lamp: View = parentView.findViewById(R.id.vi_seconds_lamp)
        if (secondsLampColor == LampColor.WHITE)
            lamp.background = context.getDrawable(R.drawable.circle_white)
        else
            lamp.background = context.getDrawable(R.drawable.circle_red)
    }

    private fun illuminateLamps(
        fiveMinutesLampTemplateID: String,
        lamps: Array<LampColor>,
        context: Context,
        parentView: View
    ) {
        val lastIndex = lamps.size - 1
        for (index: Int in 0..lastIndex) {
            val lamp = getLamp(fiveMinutesLampTemplateID + index, context, parentView)
            when (lamps[index]) {
                LampColor.WHITE -> {
                    when (index) {
                        0 -> lamp.background = context.getDrawable(R.drawable.left_rectangle_white)
                        lastIndex -> lamp.background =
                            context.getDrawable(R.drawable.right_rectangle_white)
                        else -> lamp.background = context.getDrawable(R.drawable.rectangle_white)
                    }
                }
                LampColor.YELLOW -> {
                    when (index) {
                        0 -> lamp.background = context.getDrawable(R.drawable.left_rectangle_yellow)
                        lastIndex -> lamp.background =
                            context.getDrawable(R.drawable.right_rectangle_yellow)
                        else -> lamp.background = context.getDrawable(R.drawable.rectangle_yellow)
                    }
                }
                LampColor.RED -> {
                    when (index) {
                        0 -> lamp.background = context.getDrawable(R.drawable.left_rectangle_red)
                        lastIndex -> lamp.background =
                            context.getDrawable(R.drawable.right_rectangle_red)
                        else -> lamp.background = context.getDrawable(R.drawable.rectangle_red)
                    }
                }
            }
        }
    }

    private fun getLamp(id: String, context: Context, parentView: View): View {
        val lampId =
            parentView.resources.getIdentifier(id, "id", context.packageName)
        return parentView.findViewById(lampId)
    }
}