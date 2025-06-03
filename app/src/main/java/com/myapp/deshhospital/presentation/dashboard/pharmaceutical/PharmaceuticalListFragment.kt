package com.myapp.deshhospital.presentation.dashboard.pharmaceutical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentPharmaceuticalListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.PharmaceuticalAdapter
import com.myapp.deshhospital.presentation.model.Pharmaceutical
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PharmaceuticalListFragment : Fragment() {
    var actionAddPharmaceutical = Navigation.createNavigateOnClickListener(R.id.action_pharmaceuticalListFragment_to_addPharmaceuticalFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentPharmaceuticalListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pharmaceutical_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Pharmaceutical>()
        item.add(Pharmaceutical("Square"))
        val adapter = PharmaceuticalAdapter(item,requireContext())
        binding.pharmaceuticalListRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.pharmaceuticalListRecycle.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("name",it.pharmaName)
            findNavController().navigate(R.id.action_pharmaceuticalListFragment_to_pharmaceuticalDetailsFragment,bundle)
        }
        binding.medicineContainer.setOnClickListener {
            findNavController().navigate(R.id.action_pharmaceuticalListFragment_to_pharmaceuticalActivityFragment)
        }
        return binding.root
    }
}