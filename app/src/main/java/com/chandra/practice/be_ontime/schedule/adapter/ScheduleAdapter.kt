package com.chandra.practice.be_ontime.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.databinding.LayoutScheduleItemsBinding
import com.chandra.practice.be_ontime.schedule.model.ScheduleEventItem

class ScheduleAdapter(private val scheduleList : List<ScheduleEventItem>) :
        RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(private val scheduleItemsBinding : LayoutScheduleItemsBinding) :
            RecyclerView.ViewHolder(scheduleItemsBinding.root) {
        fun bind(schedule : ScheduleEventItem) {
            scheduleItemsBinding.title.text = schedule.title
            scheduleItemsBinding.time.text = schedule.time
            scheduleItemsBinding.place.text = schedule.place
            scheduleItemsBinding.notes.text = schedule.notes
            scheduleItemsBinding.checkbox.setOnCheckedChangeListener { compoundButton , _ ->
                scheduleItemsBinding.eventCard.setBackgroundResource(
                        if (compoundButton.isChecked) R.drawable.bg_event_card_checked
                        else R.drawable.bg_event_card_unchecked
                                                                    )
            }

        }
    }


    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : ScheduleViewHolder {
        val binding =
            LayoutScheduleItemsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ScheduleViewHolder(binding)
    }


    override fun onBindViewHolder(holder : ScheduleViewHolder , position : Int) {
        holder.bind(scheduleList[position])
    }

    override fun getItemCount() = scheduleList.size
}