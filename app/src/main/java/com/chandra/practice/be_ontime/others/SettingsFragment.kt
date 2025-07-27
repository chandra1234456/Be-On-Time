package com.chandra.practice.be_ontime.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.be_ontime.R
import com.chandra.practice.be_ontime.databinding.FragmentSettingsBinding
import com.chandra.practice.be_ontime.others.adapter.SettingsAdapter
import com.chandra.practice.be_ontime.others.model.SettingsItem
import com.chandra.practice.be_ontime.util.handleOnBackPressed

class SettingsFragment : Fragment() {
    private lateinit var settingsBinding : FragmentSettingsBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        settingsBinding = FragmentSettingsBinding.inflate(layoutInflater)
        val items = listOf(
                SettingsItem.Header("Notification") ,
                SettingsItem.Entry("Audio") ,
                SettingsItem.Entry("Notification bar") ,
                SettingsItem.Header("Extras") ,
                SettingsItem.Entry("Help") ,
                SettingsItem.Entry("About")
                          )

        settingsBinding.settingsRecyclerView.adapter = SettingsAdapter(items)
        settingsBinding.settingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        settingsBinding.settingsBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        handleOnBackPressed {
            findNavController().navigate(R.id.homeFragment)
        }
        return settingsBinding.root
    }

}