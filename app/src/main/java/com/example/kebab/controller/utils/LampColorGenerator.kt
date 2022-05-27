package com.berlin.clock.controller.utils

import com.berlin.clock.model.LampColor

object LampColorGenerator {
    fun generateSecondsLamp(seconds: Int): LampColor {
        return if (seconds % 2 == 0) LampColor.RED else LampColor.WHITE
    }
    fun generateFiveMinutesLamps(minutes: Int): Array<LampColor> {
        var fiveMinutesLamps: Array<LampColor> = Array(11) { _ -> LampColor.WHITE }
        for (i: Int in 1..(minutes / 5)) {
            if (i % 3 == 0 && (i-1) != 0)
                fiveMinutesLamps[i-1] = LampColor.RED
            else
                fiveMinutesLamps[i-1] = LampColor.YELLOW
        }
        return fiveMinutesLamps
    }

    fun generateOneMinuteLamps(minutes: Int): Array<LampColor> {
        var oneMinuteLamps:Array<LampColor> = Array(4){ _-> LampColor.WHITE}
        for (i: Int in 1..(minutes % 5)) {
            oneMinuteLamps[i-1] = LampColor.YELLOW
        }
        return oneMinuteLamps
    }

    fun generateFiveHoursLamps(hours: Int): Array<LampColor> {
        var fiveHoursLamps:Array<LampColor> = Array(4){ _-> LampColor.WHITE}
        for (i: Int in 1..(hours / 5)) {
            fiveHoursLamps[i-1] = LampColor.RED
        }
        return fiveHoursLamps
    }

    fun generateOneHourLamps(hours: Int): Array<LampColor> {
        var oneHourLamps:Array<LampColor> = Array(4){ _-> LampColor.WHITE}
        for (i: Int in 1..(hours % 5)) {
            oneHourLamps[i-1] = LampColor.RED
        }
        return oneHourLamps
    }
}