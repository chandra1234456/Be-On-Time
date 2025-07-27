package com.chandra.practice.be_ontime.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : Fragment() {
    private lateinit var onBoardingBinding : FragmentOnBoardingBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        onBoardingBinding = FragmentOnBoardingBinding.inflate(layoutInflater)
        onBoardingBinding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        return onBoardingBinding.root
    }

}