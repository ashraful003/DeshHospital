package com.myapp.deshhospital.presentation.dashboard.appointment

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
import com.myapp.deshhospital.databinding.FragmentSpecialtiesDoctorBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.DoctorAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.SpecialtiesAdapter
import com.myapp.deshhospital.presentation.model.Doctor
import com.myapp.deshhospital.presentation.model.Specialties
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SpecialtiesDoctorFragment : Fragment() {
    val actionAddDoctor = Navigation.createNavigateOnClickListener(R.id.action_specialtiesDoctorFragment_to_addDoctorFragment)
@Inject
lateinit var activityUtil: DHActivityUtil
private lateinit var binding:FragmentSpecialtiesDoctorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_specialties_doctor, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Doctor>()
        item.add(Doctor("Ashraful Alam","Cardiology","MBBS(DMC),BCS(Health),FCPS", R.drawable.ic_human))
        item.add(Doctor("Md Ashraful Alam","Cardiology","MBBS(DMC),BCS(Health),FCPS",  R.drawable.doctor))

        val adapter = DoctorAdapter(item, requireContext())
        binding.doctorListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.doctorListRecycle.adapter = adapter
        adapter.onItemClick={
            val bundle = Bundle()
            bundle.putString("name",it.name)
            findNavController().navigate(R.id.action_specialtiesDoctorFragment_to_appointmentFragment,bundle)
        }
        return binding.root
    }
}