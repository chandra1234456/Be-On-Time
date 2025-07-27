package com.chandra.practice.be_ontime.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.databinding.FragmentCreateNoteBinding
import com.chandra.practice.be_ontime.util.handleOnBackPressed

class CreateNoteFragment : Fragment() {
    private lateinit var createNoteBinding : FragmentCreateNoteBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        createNoteBinding = FragmentCreateNoteBinding.inflate(layoutInflater)

        createNoteBinding.saveNote.setOnClickListener {
            val noteTitle = createNoteBinding.tvTitle.text.toString()
            val noteDescription = createNoteBinding.tvDescription.text.toString()
            createNoteBinding.tvDisplay.text = "$noteTitle : $noteDescription"
        }
        handleOnBackPressed {
            findNavController().navigate(R.id.noteFragment)
        }
        return createNoteBinding.root
    }

}