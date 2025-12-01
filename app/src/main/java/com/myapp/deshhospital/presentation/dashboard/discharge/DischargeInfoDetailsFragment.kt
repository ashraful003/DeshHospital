package com.myapp.deshhospital.presentation.dashboard.discharge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentDischargeInfoDetailsBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DischargeInfoDetailsFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentDischargeInfoDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_discharge_info_details, container, false)
        binding.model = this
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}