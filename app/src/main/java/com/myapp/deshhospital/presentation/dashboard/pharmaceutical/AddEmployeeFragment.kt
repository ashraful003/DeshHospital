package com.myapp.deshhospital.presentation.dashboard.pharmaceutical

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentAddEmployeeBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AddEmployeeFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentAddEmployeeBinding
    private lateinit var viewModel:PharmaceuticalViewModel
    @SuppressLint("ClickableViewAccessibility", "CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_employee, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        isEnableContinueBTN(false)
        val fullNameStream = RxTextView.textChanges(binding.eFullNameEt)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        fullNameStream.subscribe {
            binding.eFullNameEt.error = if (it) getString(R.string.error_name) else null
        }

        val emailStream = RxTextView.textChanges(binding.eEmailEt)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            binding.eEmailEt.error = if (it) getString(R.string.error_email) else null
        }

        val postStream = RxTextView.textChanges(binding.ePostEt)
            .skipInitialValue()
            .map { post ->
                post.isEmpty()
            }
        postStream.subscribe {
            binding.ePostEt.error = if (it) getString(R.string.error_post) else null
        }

        val phoneStream = RxTextView.textChanges(binding.ePhoneNumberEt)
            .skipInitialValue()
            .map { phone ->
                phone.length < 11
            }
        phoneStream.subscribe {
            binding.ePhoneNumberEt.error = if (it) getString(R.string.error_number) else null
        }

        val locationStream = RxTextView.textChanges(binding.eLocationEt)
            .skipInitialValue()
            .map { location ->
                location.isEmpty()
            }
        locationStream.subscribe {
            binding.eLocationEt.error = if (it) getString(R.string.error_address) else null
        }

        val dobStream = RxTextView.textChanges(binding.eDobEt)
            .skipInitialValue()
            .map { dob ->
                dob.isEmpty()
            }
        dobStream.subscribe {
            binding.eDobEt.error = if (it) getString(R.string.error_dob) else null
        }
        binding.eDobEt.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action== MotionEvent.ACTION_UP){
                val datePicker = DatePickerDialog(requireContext())
                datePicker.setOnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year,month,dayOfMonth)
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    val formattedDate = dateFormat.format(selectedDate.time)
                    binding.eDobEt.setText(formattedDate)
                }
                datePicker.show()
                return@OnTouchListener true
            }
            false
        })

        val passwordStream = RxTextView.textChanges(binding.ePasswordEt)
            .skipInitialValue()
            .map { password ->
                password.isEmpty()
            }
        val confirmPasswordStream = io.reactivex.Observable.merge(
            RxTextView.textChanges(binding.ePasswordEt)
                .skipInitialValue()
                .map { password ->
                    password.toString() != binding.eConfirmPassEt.text.toString()
                },
            RxTextView.textChanges(binding.eConfirmPassEt)
                .skipInitialValue()
                .map { passConfirm ->
                    passConfirm.toString() != binding.ePasswordEt.text.toString()
                })
        val invalidFieldsStream = Observable.combineLatest(
            fullNameStream,
            emailStream,
            postStream,
            phoneStream,
            locationStream,
            dobStream,
            passwordStream,
            confirmPasswordStream
        ){nameInvalid:Boolean, emailInvalid:Boolean, postInvalid:Boolean, phoneInvalid:Boolean, locationInvalid:Boolean,dobInvalid:Boolean,passwordInvalid:Boolean, confirmPassInvalid:Boolean ->
            !nameInvalid && !emailInvalid && !postInvalid && !phoneInvalid && !locationInvalid && !dobInvalid && !passwordInvalid && !confirmPassInvalid
        }
        invalidFieldsStream.subscribe { isValid ->
            isEnableContinueBTN(isValid)
        }
        return binding.root
    }
    private fun isEnableContinueBTN(isEnable:Boolean){
        if (isEnable){
            binding.eContinueButton.isEnabled = true
            binding.eContinueButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.colorPrimary)
        }
        else{
            binding.eContinueButton.isEnabled = false
            binding.eContinueButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.blue_500)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PharmaceuticalViewModel::class.java)
    }
}