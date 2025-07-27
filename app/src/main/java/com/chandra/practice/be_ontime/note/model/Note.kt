package com.chandra.practice.be_ontime.note.model


import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("isPinned")
    var isPinned: Boolean,
    @SerializedName("noteDescription")
    var noteDescription: String,
    @SerializedName("noteTitle")
    var noteTitle: String,
    @SerializedName("timeStamp")
    var timeStamp: String
)