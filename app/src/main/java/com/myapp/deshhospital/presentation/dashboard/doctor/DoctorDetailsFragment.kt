package com.myapp.deshhospital.presentation.dashboard.doctor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentDoctorDetailsBinding

class DoctorDetailsFragment : Fragment() {
private lateinit var binding:FragmentDoctorDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_doctor_details, container, false)
        binding.model=this
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }
}