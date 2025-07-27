package com.chandra.practice.be_ontime.schedule

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.util.calender.CalendarAdapter
import com.chandra.practice.be_ontime.util.calender.CalendarUtils
import java.text.DateFormatSymbols
import java.util.Calendar

class ScheduleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var monthSpinner: Spinner
    private lateinit var yearSpinner: Spinner
    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
                             ): View? {
        return inflater.inflate(R.layout.fragment_schedule , container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        monthSpinner = view.findViewById(R.id.monthSpinner)
        yearSpinner = view.findViewById(R.id.yearSpinner)
        monthYearText = view.findViewById(R.id.monthYearText)
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView)

        val months = DateFormatSymbols().months.take(12)
        val years = CalendarUtils.getSelectableYears().toList()

        val calendar = Calendar.getInstance()
        val defaultMonth = calendar.get(Calendar.MONTH)
        val defaultYear = calendar.get(Calendar.YEAR)

        // Set up spinners
        monthSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, months)
        yearSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, years)

        monthSpinner.setSelection(defaultMonth, false)
        val defaultYearIndex = years.indexOf(defaultYear).takeIf { it >= 0 } ?: 0
        yearSpinner.setSelection(defaultYearIndex, false)

        // Calendar adapter
        calendarRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        val calendarAdapter = CalendarAdapter(emptyList(), defaultMonth, defaultYear) { selectedDate ->
            Toast.makeText(requireContext(), "Selected: $selectedDate", Toast.LENGTH_SHORT).show()
        }
        calendarRecyclerView.adapter = calendarAdapter

        val updateCalendar = {
            val selectedMonth = monthSpinner.selectedItemPosition
            val selectedYear = years[yearSpinner.selectedItemPosition]
            val data = CalendarUtils.generateMonthData(selectedYear, selectedMonth)

            monthYearText.text = "${data.monthName} ${data.year}"

            calendarAdapter.updateData(
                    weeks = data.weeks,
                    selectedMonth = selectedMonth,
                    selectedYear = selectedYear
                                      )
        }

        // Set spinner listeners separately
        monthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateCalendar()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateCalendar()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        updateCalendar() // Initial calendar update

        // Scroll listener to shrink/expand
        calendarRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                if (dy > 10) shrinkCalendar()
                else if (dy < -10) expandCalendar()
            }
        })
    }

    private fun shrinkCalendar() {
        calendarRecyclerView.post {
            val layoutParams = calendarRecyclerView.layoutParams
            val originalHeight = calendarRecyclerView.height
            val targetHeight = 120 // Adjust as needed

            val animator = ValueAnimator.ofInt(originalHeight, targetHeight)
            animator.duration = 300
            animator.addUpdateListener {
                layoutParams.height = it.animatedValue as Int
                calendarRecyclerView.layoutParams = layoutParams
            }
            animator.start()
        }
    }

    private fun expandCalendar() {
        calendarRecyclerView.post {
            val layoutParams = calendarRecyclerView.layoutParams
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            calendarRecyclerView.layoutParams = layoutParams
        }
    }
}
