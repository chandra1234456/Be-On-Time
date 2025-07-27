package com.chandra.practice.be_ontime.others.model


import com.google.gson.annotations.SerializedName

data class NotificationModel(
    @SerializedName("notificationTitle")
    val notificationTitle: String,
    @SerializedName("notificationDate")
    val notificationDate: String
)
