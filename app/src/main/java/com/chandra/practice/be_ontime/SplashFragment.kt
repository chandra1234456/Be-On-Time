package com.chandra.practice.be_ontime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.chandra.practice.be_ontime.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
     private lateinit var splashBinding : FragmentSplashBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        splashBinding = FragmentSplashBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(R.id.onBoarding)
        }
        return splashBinding.root
    }

}