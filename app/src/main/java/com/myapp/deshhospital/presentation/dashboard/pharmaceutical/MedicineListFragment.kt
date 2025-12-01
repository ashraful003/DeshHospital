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
import com.myapp.deshhospital.databinding.FragmentMedicineListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.MedicineAdapter
import com.myapp.deshhospital.presentation.model.Medicine
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MedicineListFragment : Fragment() {
    val actionAddMedicine = Navigation.createNavigateOnClickListener(R.id.action_medicineListFragment_to_addMedicineFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentMedicineListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_medicine_list, container, false)
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
            findNavController().navigate(R.id.action_medicineListFragment_to_medicineDetailsFragment)
        }

        return binding.root
    }
}