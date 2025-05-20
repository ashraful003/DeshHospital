package com.myapp.deshhospital.presentation.login

import android.app.DatePickerDialog
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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentLoginCreateBinding
import com.myapp.deshhospital.util.DHActivityUtil
import com.myapp.deshhospital.util.SharePreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class LoginCreateFragment : Fragment() {
    val actionSignIn = Navigation.createNavigateOnClickListener(R.id.action_loginCreateFragment_to_loginInputFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding: FragmentLoginCreateBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login_create, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        isEnableSignUpButton(false)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val nameStream = RxTextView.textChanges(binding.fullNameEt)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        nameStream.subscribe {
            binding.fullNameEt.error = if (it) getString(R.string.error_name) else null
        }
        val emailStream = RxTextView.textChanges(binding.emailEt)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            binding.emailEt.error = if (it) getString(R.string.error_email) else null
        }
        val phoneStream = RxTextView.textChanges(binding.phoneNumberEt)
            .skipInitialValue()
            .map { phone ->
                phone.length < 11
            }
        phoneStream.subscribe {
            binding.phoneNumberEt.error = if (it) getString(R.string.error_number) else null
        }
        val locationStream = RxTextView.textChanges(binding.locationEt)
            .skipInitialValue()
            .map { location ->
                location.isEmpty()
            }
        locationStream.subscribe {
            binding.locationEt.error = if (it) getString(R.string.error_address) else null
        }
        val dobStream = RxTextView.textChanges(binding.dobEt)
            .skipInitialValue()
            .map { dob ->
                dob.isEmpty()
            }
        dobStream.subscribe {
            binding.dobEt.error = if (it) getString(R.string.error_dob) else null
        }
        binding.dobEt.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val datePickerDialog = DatePickerDialog(requireContext())
                datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedDate = android.icu.util.Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val dateFormat = android.icu.text.SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    val formattedDate = dateFormat.format(selectedDate.time)
                    binding.dobEt.setText(formattedDate)
                }
                datePickerDialog.show()
                return@OnTouchListener true
            }
            false
        })
        val passwordStream = RxTextView.textChanges(binding.passwordEt)
            .skipInitialValue()
            .map { password ->
                password.isEmpty()
            }
        val passwordConfirmStream = io.reactivex.Observable.merge(
            RxTextView.textChanges(binding.passwordEt)
                .skipInitialValue()
                .map { password ->
                    password.toString() != binding.confirmPassEt.text.toString()
                },
            RxTextView.textChanges(binding.confirmPassEt)
                .skipInitialValue()
                .map { passConfirm ->
                    passConfirm.toString() != binding.passwordEt.text.toString()
                })
        val invalidFiledStream = io.reactivex.Observable.combineLatest(
            nameStream,
            emailStream,
            phoneStream,
            locationStream,
            dobStream,
            passwordStream,
            passwordConfirmStream
        ) { nameInvalid: Boolean, emailInvalid: Boolean, phoneInvalid: Boolean, locationInvalid: Boolean, dobInvalid: Boolean, passwordInvalid: Boolean, passwordConfirmInvalid: Boolean ->
            !nameInvalid && !emailInvalid && !phoneInvalid && !locationInvalid && !dobInvalid && !passwordInvalid && !passwordConfirmInvalid
        }
        invalidFiledStream.subscribe { isValid ->
            isEnableSignUpButton(isValid)
        }
        return binding.root
    }

    private fun isEnableSignUpButton(isEnable: Boolean) {
        if (isEnable == true) {
            binding.continueButton.isEnabled = true
            binding.continueButton.backgroundTintList =
                ContextCompat.getColorStateList(requireActivity(), R.color.colorPrimary)
        } else {
            binding.continueButton.isEnabled = false
            binding.continueButton.backgroundTintList =
                ContextCompat.getColorStateList(requireActivity(), R.color.blue_500)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }
}