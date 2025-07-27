package com.chandra.practice.be_ontime.others.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.others.model.SettingsItem

class SettingsAdapter(private val items: List<SettingsItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SettingsItem.Header -> VIEW_TYPE_HEADER
            is SettingsItem.Entry -> VIEW_TYPE_ENTRY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                                                )
            VIEW_TYPE_ENTRY -> EntryViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_entry, parent, false)
                                              )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is SettingsItem.Header -> (holder as HeaderViewHolder).bind(item.title)
            is SettingsItem.Entry -> (holder as EntryViewHolder).bind(item.text)
        }
    }

    override fun getItemCount() = items.size

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind(title: String) {
            titleTextView.text = title
        }
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val entryTextView: TextView = itemView.findViewById(R.id.entryTextView)

        fun bind(text: String) {
            entryTextView.text = text
        }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ENTRY = 1
    }
}