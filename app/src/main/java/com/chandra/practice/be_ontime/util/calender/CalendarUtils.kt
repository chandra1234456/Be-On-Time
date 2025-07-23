package com.chandra.practice.be_ontime.util.calender
// CalendarUtils.kt
import java.util.*

object CalendarUtils {

    data class MonthData(
        val monthName: String,
        val year: Int,
        val weeks: List<List<String>>
                        )

    fun generateMonthData(year: Int, month: Int): MonthData {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)

        val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())?.uppercase() ?: ""
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val offsetStart = firstDayOfWeek - Calendar.SUNDAY
        val prevMonthDays = List(offsetStart) { "" }

        val currentMonthDays = (1..daysInMonth).map { it.toString() }
        val totalCells = 6 * 7
        val nextMonthDays = List(totalCells - (prevMonthDays.size + currentMonthDays.size)) { "" }

        val allDays = prevMonthDays + currentMonthDays + nextMonthDays
        val weeks = allDays.chunked(7)

        return MonthData(monthName, year, weeks)
    }

    fun getSelectableYears(): IntRange {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return (currentYear - 10)..(currentYear + 50)
    }
}
