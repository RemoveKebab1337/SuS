package com.berlin.clock.controller


import com.berlin.clock.controller.utils.LampColorGenerator
import com.berlin.clock.model.ClockLamps

class BerlinClockController(
    private val hours: Int,
    private val minutes: Int,
    private val seconds: Int
) {
    fun getBerlinClockLamps(): ClockLamps {
        val secondsLamp = LampColorGenerator.generateSecondsLamp(seconds)
        val fiveMinutesLamps = LampColorGenerator.generateFiveMinutesLamps(minutes)
        val oneMinutesLamps = LampColorGenerator.generateOneMinuteLamps(minutes)
        val fiveHoursLamps = LampColorGenerator.generateFiveHoursLamps(hours)
        val oneHourLamps = LampColorGenerator.generateOneHourLamps(hours)
        return ClockLamps(
            secondsLamp = secondsLamp,
            fiveMinutesLamps = fiveMinutesLamps,
            oneMinutesLamps = oneMinutesLamps,
            fiveHoursLamps = fiveHoursLamps,
            oneHourLamps = oneHourLamps
        )
    }
}