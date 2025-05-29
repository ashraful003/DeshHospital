package com.myapp.deshhospital.presentation.dashboard.discharge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentDischargeHomeBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DischargeHomeFragment : Fragment() {
    @Inject
    lateinit var activityUtil:DHActivityUtil
    private lateinit var binding:FragmentDischargeHomeBinding
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_discharge_home, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val dischargeIdStream = RxTextView.textChanges(binding.identityCardEt)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        dischargeIdStream.subscribe {
            binding.identityCardEt.error = if (it) getString(R.string.invalid_identity) else null
            if (it) {
                binding.btnDischargeSearch.isEnabled = false
                binding.btnDischargeSearch.backgroundTintList = ContextCompat.getColorStateList(requireActivity(), R.color.blue_500)
            } else {
                binding.identityCardEt.error = null
                binding.btnDischargeSearch.isEnabled = true
                binding.btnDischargeSearch.backgroundTintList = ContextCompat.getColorStateList(requireActivity(), R.color.colorPrimary)
            }
        }
        binding.btnDischargeSearch.setOnClickListener {
            findNavController().navigate(R.id.action_dischargeHomeFragment_to_dischargeListFragment)
        }
        return binding.root
    }
}