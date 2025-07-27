package com.chandra.practice.be_ontime.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.databinding.FragmentNotificationBinding
import com.chandra.practice.be_ontime.others.adapter.NotificationAdapter
import com.chandra.practice.be_ontime.others.model.NotificationModel
import com.chandra.practice.be_ontime.util.handleOnBackPressed


class NotificationFragment : Fragment() {
    private lateinit var notificationBinding : FragmentNotificationBinding
    private lateinit var notificationAdapter : NotificationAdapter
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        notificationBinding = FragmentNotificationBinding.inflate(layoutInflater)
        notificationBinding.notificationRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        notificationBinding.notificationRecyclerView.setHasFixedSize(true)
        val notificationList = ArrayList<NotificationModel>()
        notificationList.add(NotificationModel("Notification Title" , "28 Sep 2021 10:00 AM"))
        notificationList.add(NotificationModel("Notification Title" , "28 Sep 2021 10:00 AM"))
        notificationList.add(NotificationModel("Notification Title" , "28 Sep 2021 10:00 AM"))
        notificationAdapter = NotificationAdapter(notificationList , requireContext())
        notificationBinding.notificationRecyclerView.adapter = notificationAdapter
        notificationBinding.notificationBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        handleOnBackPressed {
            findNavController().navigate(R.id.homeFragment)
        }
        return notificationBinding.root
    }
}