package com.myapp.deshhospital.presentation.dashboard.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    val actionDoctor = Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_doctorFragment)
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.model = this

        return binding.root
    }
}