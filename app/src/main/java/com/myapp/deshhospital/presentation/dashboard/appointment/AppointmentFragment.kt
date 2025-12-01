package com.myapp.deshhospital.presentation.dashboard.appointment

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
import com.myapp.deshhospital.databinding.FragmentAppointmentBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import javax.inject.Inject

@AndroidEntryPoint
class AppointmentFragment : Fragment() {
@Inject
lateinit var activityUtil: DHActivityUtil
private lateinit var binding:FragmentAppointmentBinding
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_appointment, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        isEnableAppointmentButton(false)
        val identityStream = RxTextView.textChanges(binding.idCardEt)
            .skipInitialValue()
            .map { identity->
                identity.isEmpty()
            }
        identityStream.subscribe {
            binding.idCardEt.error = if (it) getString(R.string.error_identity) else null
        }
        val dateStream = RxTextView.textChanges(binding.dateEt)
            .skipInitialValue()
            .map { date ->
                date.isEmpty()
            }
        dateStream.subscribe {
            binding.dateEt.error = if (it) getString(R.string.error_date) else null
        }
        val timeStream = RxTextView.textChanges(binding.timeEt)
            .skipInitialValue()
            .map { time ->
                time.isEmpty()
            }
        timeStream.subscribe {
            binding.timeEt.error = if (it) getString(R.string.error_time) else null
        }

        val invalidFiledStream = Observable.combineLatest(
            identityStream,
            dateStream,
            timeStream
        ){identityInvalid:Boolean, dateInvalid:Boolean,timeInvalid:Boolean ->
            !identityInvalid && !dateInvalid && !timeInvalid
        }
        invalidFiledStream.subscribe { isValid->
            isEnableAppointmentButton(isValid)
        }
        return binding.root
    }

    private fun isEnableAppointmentButton(isEnable: Boolean) {
        if (isEnable){
            binding.appointmentButton.isEnabled = true
            binding.appointmentButton.backgroundTintList = ContextCompat.getColorStateList(requireActivity(),R.color.colorPrimary)
        }else{
            binding.appointmentButton.isEnabled = false
            binding.appointmentButton.backgroundTintList = ContextCompat.getColorStateList(requireActivity(),R.color.blue_500)
        }
    }
}