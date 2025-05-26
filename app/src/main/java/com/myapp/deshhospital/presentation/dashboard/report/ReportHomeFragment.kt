package com.myapp.deshhospital.presentation.dashboard.report
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
import com.myapp.deshhospital.databinding.FragmentReportHomeBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class ReportHomeFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentReportHomeBinding
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_report_home, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val patientIdStream = RxTextView.textChanges(binding.identityCardEt)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        patientIdStream.subscribe {
            binding.identityCardEt.error = if (it) getString(R.string.invalid_identity) else null
            if (it) {
                binding.btnPatientSearch.isEnabled = false
                binding.btnPatientSearch.backgroundTintList = ContextCompat.getColorStateList(requireActivity(), R.color.blue_500)
            } else {
                binding.identityCardEt.error = null
                binding.btnPatientSearch.isEnabled = true
                binding.btnPatientSearch.backgroundTintList = ContextCompat.getColorStateList(requireActivity(), R.color.colorPrimary)
            }
        }
        binding.btnPatientSearch.setOnClickListener {
            findNavController().navigate(R.id.action_reportHomeFragment_to_reportListFragment)
        }

        return binding.root
    }
}