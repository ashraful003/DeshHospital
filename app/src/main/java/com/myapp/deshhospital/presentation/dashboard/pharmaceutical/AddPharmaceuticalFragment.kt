package com.myapp.deshhospital.presentation.dashboard.pharmaceutical

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentAddPharmaceuticalBinding
import com.myapp.deshhospital.presentation.login.LoginViewModel
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AddPharmaceuticalFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentAddPharmaceuticalBinding
    private lateinit var viewModel: PharmaceuticalViewModel
    var uri: Uri? = null
    @SuppressLint("CheckResult", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_pharmaceutical, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        isEnableSignUpButton(false)
        val nameStream = RxTextView.textChanges(binding.pharmaceuticalNameEt)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        nameStream.subscribe {
            binding.pharmaceuticalNameEt.error = if (it) getString(R.string.error_name) else null
        }
        val licenceStream = RxTextView.textChanges(binding.pharmaceuticalLicenceEt)
            .skipInitialValue()
            .map { email ->
                email.isEmpty()
            }
        licenceStream.subscribe {
            binding.pharmaceuticalLicenceEt.error = if (it) getString(R.string.error_licence) else null
        }

        val dobStream = RxTextView.textChanges(binding.pharmaceuticalJoinDateEt)
            .skipInitialValue()
            .map { dob ->
                dob.isEmpty()
            }
        dobStream.subscribe {
            binding.pharmaceuticalJoinDateEt.error = if (it) getString(R.string.error_join) else null
        }
        binding.pharmaceuticalJoinDateEt.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val datePickerDialog = DatePickerDialog(requireContext())
                datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedDate = android.icu.util.Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val dateFormat = android.icu.text.SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    val formattedDate = dateFormat.format(selectedDate.time)
                    binding.pharmaceuticalJoinDateEt.setText(formattedDate)
                }
                datePickerDialog.show()
                return@OnTouchListener true
            }
            false
        })


        val phoneStream = RxTextView.textChanges(binding.pharmaceuticalPhoneEt)
            .skipInitialValue()
            .map { phone ->
                phone.length < 11
            }
        phoneStream.subscribe {
            binding.pharmaceuticalPhoneEt.error = if (it) getString(R.string.error_number) else null
        }

        val emailStream = RxTextView.textChanges(binding.pharmaceuticalEmailEt)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            binding.pharmaceuticalEmailEt.error = if (it) getString(R.string.error_email) else null
        }

        val locationStream = RxTextView.textChanges(binding.pharmaceuticalAddressEt)
            .skipInitialValue()
            .map { location ->
                location.isEmpty()
            }
        locationStream.subscribe {
            binding.pharmaceuticalAddressEt.error = if (it) getString(R.string.error_address) else null
        }
        val invalidFiledStream = io.reactivex.Observable.combineLatest(
            nameStream,
            licenceStream,
            dobStream,
            phoneStream,
            emailStream,
            locationStream
        ) { nameInvalid: Boolean, licenceInvalid: Boolean, dobInvalid: Boolean, phoneInvalid:Boolean, emailInvalid:Boolean, locationInvalid: Boolean ->
            !nameInvalid && !licenceInvalid && !dobInvalid && !phoneInvalid && !emailInvalid && !locationInvalid
        }
        invalidFiledStream.subscribe { isValid ->
            isEnableSignUpButton(isValid)
        }

        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.pharmaceuticalUploadImage.setImageURI(uri)
            } else {
                Toast.makeText(activity, getText(R.string.no_image), Toast.LENGTH_SHORT).show()
            }
        }
        binding.pharmaceuticalUploadImage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_addPharmaceuticalFragment_to_pharmaceuticalListFragment)
        }

        return binding.root
    }
    private fun isEnableSignUpButton(isEnable: Boolean) {
        if (isEnable == true) {
            binding.btnContinue.isEnabled = true
            binding.btnContinue.backgroundTintList =
                ContextCompat.getColorStateList(requireActivity(), R.color.colorPrimary)
        } else {
            binding.btnContinue.isEnabled = false
            binding.btnContinue.backgroundTintList =
                ContextCompat.getColorStateList(requireActivity(), R.color.blue_500)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PharmaceuticalViewModel::class.java)
    }
}