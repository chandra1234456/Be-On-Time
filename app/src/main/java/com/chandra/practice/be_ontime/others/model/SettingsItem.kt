package com.chandra.practice.be_ontime.others.model

sealed class SettingsItem {
    data class Header(val title: String) : SettingsItem()
    data class Entry(val text: String) : SettingsItem()
}