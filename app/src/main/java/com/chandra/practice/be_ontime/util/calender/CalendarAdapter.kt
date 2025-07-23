package com.chandra.practice.be_ontime.util.calender// CalendarAdapter.kt
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.R
import java.util.Calendar

class CalendarAdapter(
    private val weeks: List<List<String>>,
    private val onDateClick: (String) -> Unit
                     ) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var selectedDate: String? = null

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayViews: List<TextView> = listOf(
                view.findViewById(R.id.sun),
                view.findViewById(R.id.mon),
                view.findViewById(R.id.tue),
                view.findViewById(R.id.wed),
                view.findViewById(R.id.thu),
                view.findViewById(R.id.fri),
                view.findViewById(R.id.sat)
                                             )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_calendar_week, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val week = weeks[position]

        // Get today's day as String to compare
        val todayCalendar = Calendar.getInstance()
        val todayYear = todayCalendar.get(Calendar.YEAR)
        val todayMonth = todayCalendar.get(Calendar.MONTH)
        val todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH).toString()

        week.forEachIndexed { index, day ->
            val textView = holder.dayViews[index]
            textView.text = day

            // Style the cell
            when {
                day.isEmpty() -> {
                    textView.setTextColor(Color.TRANSPARENT)
                    textView.setBackgroundColor(Color.TRANSPARENT)
                    textView.isClickable = false
                }
                selectedDate == day -> {
                    textView.setTextColor(Color.WHITE)
                    textView.setBackgroundResource(R.drawable.selected_date_bg)
                    textView.isClickable = true
                }
                selectedYear == todayYear && selectedMonth == todayMonth && day == todayDay && selectedDate != day -> {
                    textView.setTextColor(Color.WHITE)
                    textView.setBackgroundResource(R.drawable.today_date_bg)
                    textView.isClickable = true
                }
                else -> {
                    textView.setTextColor(Color.BLACK)
                    textView.setBackgroundColor(Color.TRANSPARENT)
                    textView.isClickable = true
                }
            }


            textView.setOnClickListener {
                if (day.isNotEmpty()) {
                    selectedDate = day
                    notifyDataSetChanged()

                    val formattedDate = String.format(
                            "%04d-%02d-%02d",
                            selectedYear,
                            selectedMonth + 1,  // Months are 0-based
                            day.toInt()
                                                     )
                    onDateClick(formattedDate)
                }
            }
        }
    }

    override fun getItemCount() = weeks.size

    companion object {
        var selectedMonth = Calendar.getInstance().get(Calendar.MONTH)
        var selectedYear = Calendar.getInstance().get(Calendar.YEAR)
    }
}