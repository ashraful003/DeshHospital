package com.myapp.deshhospital.presentation.dashboard.prescription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentPrescriptionListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.DoctorAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.PrescriptionAdapter
import com.myapp.deshhospital.presentation.model.Doctor
import com.myapp.deshhospital.presentation.model.Prescription
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PrescriptionListFragment : Fragment() {
    val actionAddPrescription = Navigation.createNavigateOnClickListener(R.id.action_prescriptionListFragment_to_addPrescriptionFragment)
@Inject
lateinit var activityUtil: DHActivityUtil
private lateinit var binding:FragmentPrescriptionListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_prescription_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Prescription>()
        item.add(Prescription("12/11/23","Dr.Ashraful", R.drawable.ic_pdf))
        item.add(Prescription("1212/24","Dr.Ashraful Alam", R.drawable.ic_pdf))

        val adapter = PrescriptionAdapter(item, requireContext())
        binding.doctorListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.doctorListRecycle.adapter = adapter
        adapter.onItemClick={
            val bundle = Bundle()
            bundle.putString("name",it.docName)
            findNavController().navigate(R.id.action_prescriptionListFragment_to_prescriptionDetailsFragment,bundle)
        }
        return binding.root
    }
}