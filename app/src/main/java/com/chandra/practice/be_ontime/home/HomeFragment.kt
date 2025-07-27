package com.chandra.practice.be_ontime.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.databinding.FragmentHomeBinding
import com.chandra.practice.be_ontime.util.ViewPagerAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    private lateinit var homeBinding : FragmentHomeBinding
    private var isFabMenuOpen = false
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
       /* homeBinding.toggleGroup.addOnButtonCheckedListener { _ , checkedId , isChecked ->
            if (isChecked) {
                val selectedColor =
                    ContextCompat.getColor(requireContext() , R.color.toggle_button_selected)
                val unselectedColor =
                    ContextCompat.getColor(requireContext() , R.color.toggle_button_unselected)

                homeBinding.btnOnTime.setBackgroundColor(
                        if (checkedId == R.id.btnOnTime) selectedColor else unselectedColor
                                                        )
                homeBinding.btnDelayed.setBackgroundColor(
                        if (checkedId == R.id.btnDelayed) selectedColor else unselectedColor
                                                         )
            }
        }
        homeBinding.notifications.setOnClickListener {
            findNavController().navigate(R.id.notificationFragment)
        }
        homeBinding.settings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }*/

        return homeBinding.root
    }


    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        val mainFab = view.findViewById<ExtendedFloatingActionButton>(R.id.add_fab)
        val fab1 = view.findViewById<FloatingActionButton>(R.id.fabOption1)
        val fab2 = view.findViewById<FloatingActionButton>(R.id.fabOption2)

        mainFab.setOnClickListener {
            isFabMenuOpen = ! isFabMenuOpen
            toggleFab(fab1 , isFabMenuOpen)
            toggleFab(fab2 , isFabMenuOpen)
            mainFab.text = if (isFabMenuOpen) "Close" else "Actions"
        }

        fab1.setOnClickListener {
            Toast.makeText(requireContext() , "Schedule clicked" , Toast.LENGTH_SHORT).show()
        }

        fab2.setOnClickListener {
            Toast.makeText(requireContext() , "Note clicked" , Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.createNoteFragment)
        }

        val adapter = ViewPagerAdapter(requireActivity())
        homeBinding.viewPager.adapter = adapter

        TabLayoutMediator(homeBinding.tabLayout, homeBinding.viewPager) { tab, position ->
            tab.text = if (position == 0) "Schedule" else "Note"
        }.attach()

        homeBinding.notifications.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
        }


    }


    private fun toggleFab(fab : FloatingActionButton , show : Boolean) {
        if (show) {
            fab.visibility = View.VISIBLE
            fab.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(200)
                    .start()
        } else {
            fab.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction { fab.visibility = View.GONE }
                    .start()
        }
    }


}