package com.myapp.deshhospital.presentation.dashboard.pharmaceutical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentExpireMedicineBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.MedicineAdapter
import com.myapp.deshhospital.presentation.model.Medicine
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExpireMedicineFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentExpireMedicineBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_expire_medicine, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Medicine>()
        item.add(Medicine("Napa","Beximco Pharma",R.drawable.blood))
        val adapter  = MedicineAdapter(item,requireContext())
        binding.medicineListRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.medicineListRecycle.adapter =  adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("name",it.name)
            findNavController().navigate(R.id.action_expireMedicineFragment_to_expireMedicineDetailsFragment)
        }
        return binding.root
    }
}