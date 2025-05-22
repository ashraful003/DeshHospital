package com.myapp.deshhospital.presentation.dashboard.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentDoctorBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.DoctorAdapter
import com.myapp.deshhospital.presentation.model.Doctor
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DoctorFragment : Fragment() {
    val actionAddDoctor = Navigation.createNavigateOnClickListener(R.id.action_doctorFragment_to_addDoctorFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentDoctorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_doctor, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
          findNavController().popBackStack()
        }
        //Demo doctor info
        val item = ArrayList<Doctor>()
        item.add(Doctor("Ashraful Alam", "Medicine", "MBBS(DMC),FCPF,BCS", R.drawable.ashraful))
        item.add(Doctor("Md Ashraful Alam", "Ear Nose and Throat", "MBBS(DMC),FCPF,BCS", R.drawable.doctor))

        val adapter = DoctorAdapter(item, requireContext())
        binding.doctorListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.doctorListRecycle.adapter = adapter

        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("name",it.name)
            bundle.putString("post",it.post)
            bundle.putString("qualification",it.qualification)
            bundle.putString("dob",it.dob)
            bundle.putString("email",it.email)
            bundle.putString("phone",it.phone)
            bundle.putString("address",it.address)
            bundle.putString("image", it.image.toString())
            findNavController().navigate(R.id.action_doctorFragment_to_doctorDetailsFragment,bundle)
        }
        //end
        return binding.root
    }
}