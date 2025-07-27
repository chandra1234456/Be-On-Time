package com.chandra.practice.be_ontime.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.databinding.FragmentNoteBinding
import com.chandra.practice.be_ontime.note.adapter.NoteAdapter
import com.chandra.practice.be_ontime.note.model.Note
import com.chandra.practice.be_ontime.util.handleOnBackPressed

class NoteFragment : Fragment() {
    private lateinit var noteBinding : FragmentNoteBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        noteBinding = FragmentNoteBinding.inflate(layoutInflater)

        val list = arrayListOf(
                Note(false,"Hello","Helloo","NOW"),
                Note(false,"Hello","Helloo","NOW"),
                Note(false,"Hello","Helloo","NOW"),
                Note(false,"Hello","Helloo","NOW"),
                Note(false,"Hello","Helloo","NOW")
                              )
        noteBinding.notesRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        noteBinding.notesRecyclerview.adapter = NoteAdapter(list)
        handleOnBackPressed {
            findNavController().navigate(R.id.homeFragment)
        }
        return noteBinding.root
    }

}