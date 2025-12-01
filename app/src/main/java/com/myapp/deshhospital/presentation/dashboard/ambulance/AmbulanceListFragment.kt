package com.myapp.deshhospital.presentation.dashboard.ambulance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentAmbulanceListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.AmbulanceAdapter
import com.myapp.deshhospital.presentation.model.Ambulance
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AmbulanceListFragment : Fragment() {
    val actionAddAmbulance = Navigation.createNavigateOnClickListener(R.id.action_ambulanceListFragment_to_addAmbulanceFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentAmbulanceListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ambulance_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Ambulance>()
        item.add(Ambulance("Dhaka"))
        item.add(Ambulance("Rangpur"))
        val adapter = AmbulanceAdapter(item,requireContext())
        binding.ambulanceListRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.ambulanceListRecycle.adapter = adapter
        adapter.onItemClick = {
            val bundle=Bundle()
            bundle.putString("name",it.area)
            findNavController().navigate(R.id.action_ambulanceListFragment_to_ambulanceDetailsFragment,bundle)
        }

        return binding.root
    }
}