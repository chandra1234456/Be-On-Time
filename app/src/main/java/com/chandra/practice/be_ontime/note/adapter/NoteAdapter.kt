package com.chandra.practice.be_ontime.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.databinding.LayoutNoteItemsBinding
import com.chandra.practice.be_ontime.note.model.Note

class NoteAdapter(private val notesList : List<Note>) :
        RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(private var noteItemsBinding : LayoutNoteItemsBinding) :
            RecyclerView.ViewHolder(noteItemsBinding.root) {
        fun bind(note : Note) {
            noteItemsBinding.tvNoteTitle.text = note.noteTitle
            noteItemsBinding.tvNoteDescription.text = note.noteDescription
            noteItemsBinding.tvNoteDate.text = note.timeStamp
        }
    }

    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : NoteViewHolder {
        val binding =
            LayoutNoteItemsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return NoteViewHolder(binding)
    }


    override fun onBindViewHolder(holder : NoteViewHolder , position : Int) {
        holder.bind(notesList[position])
    }


    override fun getItemCount() = notesList.size
}