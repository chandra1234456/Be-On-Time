package com.chandra.practice.be_ontime.schedule.model


import com.google.gson.annotations.SerializedName

data class ScheduleEventItem(
    @SerializedName("dayNumber")
    var dayNumber: String,
    @SerializedName("notes")
    var notes: String,
    @SerializedName("place")
    var place: String,
    @SerializedName("time")
    var time: String,
    @SerializedName("title")
    var title: String
)