package udacity.fwd.project1solution.converters

import androidx.databinding.InverseMethod

object DoubleConverter {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(
        value: Double?
    ): String {
        if (value == null || value == 0.0) return ""
        return value.toString()
    }

    @JvmStatic
    fun stringToDouble(
        value: String
    ): Double? {
        // Converts String to double.
        if (value.isBlank()) return null
        try {
            return value.toDouble()
        } catch (ex: NumberFormatException) {
            return null
        }
    }
}