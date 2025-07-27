package com.chandra.practice.be_ontime.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chandra.practice.be_ontime.schedule.ScheduleFragment
import com.chandra.practice.be_ontime.note.NoteFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ScheduleFragment()
            else -> NoteFragment()
        }
    }
}
