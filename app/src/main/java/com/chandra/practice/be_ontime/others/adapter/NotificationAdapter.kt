package com.chandra.practice.be_ontime.others.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.databinding.LayoutNotificationItemsBinding
import com.chandra.practice.be_ontime.others.model.NotificationModel

class NotificationAdapter(private var notificationList : ArrayList<NotificationModel>,context : Context) :
        RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(private var notificationItemBinding : LayoutNotificationItemsBinding) :
            RecyclerView.ViewHolder(notificationItemBinding.root) {
        fun bind(notificationModel : NotificationModel) {
            notificationItemBinding.tvNotificationTitle.text = notificationModel.notificationTitle
            notificationItemBinding.tvNotificationDate.text = notificationModel.notificationDate
        }
    }


    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : NotificationViewHolder {
        val notificationItemBinding = LayoutNotificationItemsBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
                                                                            )
        return NotificationViewHolder(notificationItemBinding)
    }

    override fun getItemCount() = notificationList.size

    override fun onBindViewHolder(holder : NotificationViewHolder , position : Int) {
        holder.bind(notificationList[position])
    }
}