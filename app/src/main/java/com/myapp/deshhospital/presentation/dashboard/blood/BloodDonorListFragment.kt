package com.myapp.deshhospital.presentation.dashboard.blood

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
import com.myapp.deshhospital.databinding.FragmentBloodDonorListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.BloodDonorAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.DischargeAdapter
import com.myapp.deshhospital.presentation.model.Blood
import com.myapp.deshhospital.presentation.model.Discharge
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BloodDonorListFragment : Fragment() {
    val actionAddDonor= Navigation.createNavigateOnClickListener(R.id.action_bloodDonorListFragment_to_addBloodDonorFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentBloodDonorListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_blood_donor_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Blood>()
        item.add(Blood("Ashraful","Dhanmondi"))
        item.add(Blood("Sheam","Uttora"))

        val adapter = BloodDonorAdapter(item, requireContext())
        binding.bloodDonorListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.bloodDonorListRecycle.adapter = adapter
        adapter.onClickItem={
            val bundle = Bundle()
            bundle.putString("name",it.name)
            findNavController().navigate(R.id.action_bloodDonorListFragment_to_bloodDonorDetailsFragment,bundle)
        }

        return binding.root
    }
}