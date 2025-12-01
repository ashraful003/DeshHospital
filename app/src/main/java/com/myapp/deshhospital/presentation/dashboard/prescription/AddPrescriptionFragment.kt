package com.myapp.deshhospital.presentation.dashboard.prescription

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentAddPrescriptionBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddPrescriptionFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentAddPrescriptionBinding
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_prescription, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val prescriptionStream = RxTextView.textChanges(binding.prescriptionEt)
            .skipInitialValue()
            .map { prescription ->
                prescription.isEmpty()
            }
        prescriptionStream.subscribe {
            if (it) {
                binding.btnPrescription.isEnabled = false
                binding.btnPrescription.backgroundTintList =
                    ContextCompat.getColorStateList(requireActivity(), R.color.blue_500)
            } else {
                binding.prescriptionEt.error = null
                binding.btnPrescription.isEnabled = true
                binding.btnPrescription.backgroundTintList =
                    ContextCompat.getColorStateList(requireActivity(), R.color.colorPrimary)
            }
        }

        return binding.root
    }
}