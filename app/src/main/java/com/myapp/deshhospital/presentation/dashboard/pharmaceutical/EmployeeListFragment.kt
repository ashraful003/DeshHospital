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
import com.myapp.deshhospital.databinding.FragmentEmployeeListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.EmployeeAdapter
import com.myapp.deshhospital.presentation.model.Employee
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {
    val actionAddEmployee = Navigation.createNavigateOnClickListener(R.id.action_employeeListFragment_to_addEmployeeFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentEmployeeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employee_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        var item = ArrayList<Employee>()
        item.add(Employee("Ashraful","Manager",R.drawable.ambulance))
        val adapter = EmployeeAdapter(item,requireContext())
        binding.employeeListRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.employeeListRecycle.adapter = adapter
        adapter.onItemClick= {
            val bundle = Bundle()
            bundle.putString("name",it.name)
            findNavController().navigate(R.id.action_employeeListFragment_to_employeeDetailsFragment)
        }
        return binding.root
    }
}