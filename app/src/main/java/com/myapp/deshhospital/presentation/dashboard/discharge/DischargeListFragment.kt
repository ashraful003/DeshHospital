package com.myapp.deshhospital.presentation.dashboard.discharge

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
import com.myapp.deshhospital.databinding.FragmentDischargeListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.DischargeAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.PrescriptionAdapter
import com.myapp.deshhospital.presentation.model.Discharge
import com.myapp.deshhospital.presentation.model.Prescription
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DischargeListFragment : Fragment() {
    val actionAddPrescription = Navigation.createNavigateOnClickListener(R.id.action_dischargeListFragment_to_addDischargeListFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding: FragmentDischargeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_discharge_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Discharge>()
        item.add(Discharge("12/11/23","Dr.Ashraful"))

        val adapter = DischargeAdapter(item, requireContext())
        binding.dischargeListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.dischargeListRecycle.adapter = adapter
        adapter.onItemClick={
            val bundle = Bundle()
            bundle.putString("name",it.docName)
            findNavController().navigate(R.id.action_dischargeListFragment_to_dischargeInfoDetailsFragment,bundle)
        }
        return binding.root
    }
}