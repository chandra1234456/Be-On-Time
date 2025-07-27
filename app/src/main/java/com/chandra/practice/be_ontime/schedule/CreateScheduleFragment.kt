package com.chandra.practice.be_ontime.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chandra.practice.be_ontime.databinding.FragmentCreateScheduleBinding

class CreateScheduleFragment : Fragment() {
    private lateinit var createScheduleBinding : FragmentCreateScheduleBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        createScheduleBinding = FragmentCreateScheduleBinding.inflate(layoutInflater)
        return createScheduleBinding.root
    }

}