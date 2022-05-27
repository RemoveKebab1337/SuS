package com.berlin.clock.model

data class ClockLamps(
    val secondsLamp: LampColor = LampColor.RED,
    val fiveHoursLamps: Array<LampColor> = Array(4){ _-> LampColor.WHITE},
    val oneHourLamps: Array<LampColor> = Array(4){ _-> LampColor.WHITE},
    val fiveMinutesLamps: Array<LampColor> = Array(11){ _-> LampColor.WHITE},
    val oneMinutesLamps: Array<LampColor> = Array(4){ _-> LampColor.WHITE},
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClockLamps

        if (secondsLamp != other.secondsLamp) return false
        if (!fiveHoursLamps.contentEquals(other.fiveHoursLamps)) return false
        if (!oneHourLamps.contentEquals(other.oneHourLamps)) return false
        if (!fiveMinutesLamps.contentEquals(other.fiveMinutesLamps)) return false
        if (!oneMinutesLamps.contentEquals(other.oneMinutesLamps)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = secondsLamp.hashCode()
        result = 31 * result + fiveHoursLamps.contentHashCode()
        result = 31 * result + oneHourLamps.contentHashCode()
        result = 31 * result + fiveMinutesLamps.contentHashCode()
        result = 31 * result + oneMinutesLamps.contentHashCode()
        return result
    }
}
