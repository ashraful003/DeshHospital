package com.myapp.deshhospital.presentation.dashboard.pharmaceutical

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import com.myapp.deshhospital.databinding.FragmentAddMedicineBinding
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AddMedicineFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding : FragmentAddMedicineBinding
    private lateinit var viewModel: PharmaceuticalViewModel
    var imageURL: String? = null
    var uri: Uri? = null
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_medicine, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        isEnableSaveButton(false)
        val nameStream = RxTextView.textChanges(binding.nameEt)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }

        val companyStream = RxTextView.textChanges(binding.companyEt)
            .skipInitialValue()
            .map { company ->
                company.isEmpty()
            }

        val detailsStream = RxTextView.textChanges(binding.detailsEt)
            .skipInitialValue()
            .map { details ->
                details.isEmpty()
            }

        val priceStream = RxTextView.textChanges(binding.priceEt)
            .skipInitialValue()
            .map { price ->
                price.isEmpty()
            }

        val expireDateStream = RxTextView.textChanges(binding.expireDateEt)
            .skipInitialValue()
            .map { expire ->
                expire.isEmpty()
            }

        binding.expireDateEt.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val datePickerDialog = DatePickerDialog(requireContext())
                datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                    val formattedDate = dateFormat.format(selectedDate.time)
                    binding.expireDateEt.setText(formattedDate)
                }
                datePickerDialog.show()
                return@OnTouchListener true
            }
            false
        })

        val invalidFiledStream = Observable.combineLatest(
            nameStream,
            companyStream,
            detailsStream,
            priceStream,
            expireDateStream
        ) { nameInvalid: Boolean, companyInvalid: Boolean, detailsInvalid: Boolean, priceInvalid: Boolean, expiredateInvalid: Boolean, ->
            !nameInvalid && !companyInvalid && !detailsInvalid && !priceInvalid && !expiredateInvalid
        }
        invalidFiledStream.subscribe { isValid ->
            isEnableSaveButton(isValid)
        }

        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.medicineUploadImage.setImageURI(uri)
            } else {
                Toast.makeText(activity, getText(R.string.no_image), Toast.LENGTH_SHORT).show()
            }
        }
        binding.medicineUploadImage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }
        binding.btnSave.setOnClickListener {
           findNavController().navigate(R.id.action_addMedicineFragment_to_medicineListFragment)
        }
        return binding.root
    }
    private fun isEnableSaveButton(isEnable: Boolean) {
        if (isEnable) {
            binding.btnSave.isEnabled = true
            binding.btnSave.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
        } else {
            binding.btnSave.isEnabled = false
            binding.btnSave.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.blue_500)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PharmaceuticalViewModel::class.java)
    }
}