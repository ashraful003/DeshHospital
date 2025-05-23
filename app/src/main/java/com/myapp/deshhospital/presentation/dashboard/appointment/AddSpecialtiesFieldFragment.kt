package com.myapp.deshhospital.presentation.dashboard.appointment

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
import com.myapp.deshhospital.databinding.FragmentAddSpecialtiesFieldBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddSpecialtiesFieldFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentAddSpecialtiesFieldBinding
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_specialties_field, container, false)
        binding.model = this
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val fieldNameStream = RxTextView.textChanges(binding.fieldNameEt)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        fieldNameStream.subscribe {
            binding.fieldNameEt.error = if (it) getString(R.string.error_field_name) else null
            if (it) {
                binding.btnContinue.isEnabled = false
                binding.btnContinue.backgroundTintList =
                    ContextCompat.getColorStateList(requireActivity(), R.color.blue_500)
            } else {
                binding.fieldNameEt.error = null
                binding.btnContinue.isEnabled = true
                binding.btnContinue.backgroundTintList =
                    ContextCompat.getColorStateList(requireActivity(), R.color.colorPrimary)
            }
        }
        return binding.root
    }
}