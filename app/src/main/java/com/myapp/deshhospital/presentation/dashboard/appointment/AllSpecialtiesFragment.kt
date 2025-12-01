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
import com.myapp.deshhospital.databinding.FragmentAllSpecialtiesBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.DoctorAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.SpecialtiesAdapter
import com.myapp.deshhospital.presentation.model.Doctor
import com.myapp.deshhospital.presentation.model.Specialties
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllSpecialtiesFragment : Fragment() {
    val actionDoctor = Navigation.createNavigateOnClickListener(R.id.action_allSpecialtiesFragment_to_userViewDoctorFragment)
    val actionAddSpecialtiesField = Navigation.createNavigateOnClickListener(R.id.action_allSpecialtiesFragment_to_addSpecialtiesFieldFragment)
@Inject
lateinit var activityUtil: DHActivityUtil
private lateinit var binding:FragmentAllSpecialtiesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_specialties, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Specialties>()
        item.add(Specialties("Dentistry", R.drawable.ic_dentist))
        item.add(Specialties("Cardiology",  R.drawable.heart))

        val adapter = SpecialtiesAdapter(item, requireContext())
        binding.departListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.departListRecycle.adapter = adapter
        adapter.ItemClick={
            val bundle = Bundle()
            bundle.putString("name",it.name)
            findNavController().navigate(R.id.action_allSpecialtiesFragment_to_specialtiesDoctorFragment,bundle)
        }
        return binding.root
    }
}