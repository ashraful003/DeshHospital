package com.myapp.deshhospital.presentation.dashboard.admission

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
import com.myapp.deshhospital.databinding.FragmentAdmissionListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.AdmissionAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.DischargeAdapter
import com.myapp.deshhospital.presentation.model.Admission
import com.myapp.deshhospital.presentation.model.Discharge
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AdmissionListFragment : Fragment() {
    val actionAddPatient = Navigation.createNavigateOnClickListener(R.id.action_admissionListFragment_to_addPatientFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding: FragmentAdmissionListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_admission_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Admission>()
        item.add(Admission("29/05/25","A01856220"))
        item.add(Admission("12/06/25","6004945082"))

        val adapter = AdmissionAdapter(item, requireContext())
        binding.newPatientListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.newPatientListRecycle.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("name",it.addIdentity)
            findNavController().navigate(R.id.action_admissionListFragment_to_admissionPatientDetailsFragment,bundle)
        }
        return binding.root
    }
}