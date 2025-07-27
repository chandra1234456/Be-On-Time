package com.chandra.practice.be_ontime.util.calender

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.util.setSingleClickListener
import java.util.Calendar

class CalendarAdapter(
    private var weeks: List<List<String>>,
    var selectedMonth: Int,
    var selectedYear: Int,
    private val onDateClick: (String) -> Unit
                     ) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var selectedDay: Int? = null
    private var previousSelectedPosition: Pair<Int, Int>? = null

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayViews: List<TextView> = listOf("sun", "mon", "tue", "wed", "thu", "fri", "sat").map {
            val id = view.resources.getIdentifier(it, "id", view.context.packageName)
            view.findViewById(id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_week, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val week = weeks[position]
        val today = Calendar.getInstance()
        val todayDay = today.get(Calendar.DAY_OF_MONTH).toString()
        val todayMonth = today.get(Calendar.MONTH)
        val todayYear = today.get(Calendar.YEAR)

        week.forEachIndexed { index, day ->
            val textView = holder.dayViews[index]
            textView.text = day

            when {
                day.isEmpty() -> {
                    textView.setTextColor(Color.GRAY)
                    textView.isClickable = false
                    textView.background = null
                }
                selectedDay?.toString() == day -> {
                    textView.setTextColor(Color.WHITE)
                    textView.setBackgroundResource(R.drawable.selected_date_bg)
                }
                selectedMonth == todayMonth && selectedYear == todayYear && day == todayDay -> {
                    textView.setTextColor(Color.WHITE)
                    textView.setBackgroundResource(R.drawable.today_date_bg)
                }
                else -> {
                    textView.setTextColor(Color.WHITE)
                    textView.background = null
                }
            }

            textView.setSingleClickListener {
                if (day.isNotEmpty()) {
                    previousSelectedPosition?.let { (weekPos, _) -> notifyItemChanged(weekPos) }
                    selectedDay = day.toIntOrNull()
                    previousSelectedPosition = position to index
                    notifyItemChanged(position)

                    day.toIntOrNull()?.let { dayInt ->
                        onDateClick("$selectedYear-${selectedMonth + 1}-$dayInt")
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = weeks.size

    fun updateData(weeks: List<List<String>>, selectedMonth: Int, selectedYear: Int) {
        this.weeks = weeks
        this.selectedMonth = selectedMonth
        this.selectedYear = selectedYear
        this.selectedDay = null
        this.previousSelectedPosition = null
        notifyDataSetChanged()
    }
}
