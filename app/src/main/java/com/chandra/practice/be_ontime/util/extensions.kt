package com.chandra.practice.be_ontime.util

import com.chandra.practice.be_ontime.util.calender.CalendarUtils
import java.util.Calendar
import java.util.Locale

fun generateMonthData(year: Int, month: Int): CalendarUtils.MonthData {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)

    val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())?.uppercase() ?: ""
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // Sunday = 1

    // Previous month fill (if month starts on Wednesday, you need 2 blank cells before day 1)
    val offsetStart = firstDayOfWeek - Calendar.SUNDAY // 0 if Sunday
    val prevMonthDays = List(offsetStart) { "" }

    val currentMonthDays = (1..daysInMonth).map { it.toString() }

    val totalCells = 6 * 7
    val nextMonthDays = List(totalCells - (prevMonthDays.size + currentMonthDays.size)) { "" }

    val allDays = prevMonthDays + currentMonthDays + nextMonthDays
    val weeks = allDays.chunked(7)

    return CalendarUtils.MonthData(monthName , year , weeks)
}
