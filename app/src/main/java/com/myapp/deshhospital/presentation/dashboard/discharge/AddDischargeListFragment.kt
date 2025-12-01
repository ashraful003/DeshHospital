package com.myapp.deshhospital.presentation.dashboard.discharge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentAddDischargeListBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddDischargeListFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentAddDischargeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_discharge_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.disContinueButton.setOnClickListener {
            findNavController().navigate(R.id.action_addDischargeListFragment_to_dischargeListFragment)
        }
        return binding.root
    }
}