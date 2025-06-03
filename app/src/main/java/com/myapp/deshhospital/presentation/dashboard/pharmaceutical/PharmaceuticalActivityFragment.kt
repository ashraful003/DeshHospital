package com.myapp.deshhospital.presentation.dashboard.pharmaceutical

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
import com.myapp.deshhospital.databinding.FragmentPharmaceuticalActivityBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.HomeAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.MedicineAdapter
import com.myapp.deshhospital.presentation.model.Medicine
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PharmaceuticalActivityFragment : Fragment() {
    val actionMedicineList = Navigation.createNavigateOnClickListener(R.id.action_pharmaceuticalActivityFragment_to_medicineListFragment)
    val actionExpireMedicine = Navigation.createNavigateOnClickListener(R.id.action_pharmaceuticalActivityFragment_to_expireMedicineFragment)
    val actionEmployee = Navigation.createNavigateOnClickListener(R.id.action_pharmaceuticalActivityFragment_to_employeeListFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentPharmaceuticalActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pharmaceutical_activity, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }


        val item = ArrayList<Medicine>()
        item.add(Medicine("Napa","Beximco Pharma", R.drawable.ic_pdf))
        item.add(Medicine("Napa Extra","Beximco Pharma", R.drawable.ic_pdf))

        val adapter = HomeAdapter(item, requireContext())
        binding.userMedicineRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.userMedicineRecycle.adapter = adapter
        adapter.onItemClick={
            val bundle = Bundle()
            bundle.putString("name",it.name)
            findNavController().navigate(R.id.action_pharmaceuticalActivityFragment_to_medicineDetailsFragment,bundle)
        }



        return binding.root
    }
}