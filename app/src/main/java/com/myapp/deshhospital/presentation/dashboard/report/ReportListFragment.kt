package com.myapp.deshhospital.presentation.dashboard.report

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
import com.myapp.deshhospital.databinding.FragmentReportListBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.PrescriptionAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.ReportAdapter
import com.myapp.deshhospital.presentation.model.Prescription
import com.myapp.deshhospital.presentation.model.Report
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReportListFragment : Fragment() {
    val actionAddReport = Navigation.createNavigateOnClickListener(R.id.action_reportListFragment_to_addReportFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding:FragmentReportListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_report_list, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        activityUtil.hideDrawerNavigation(true)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val item = ArrayList<Report>()
        item.add(Report("12/11/23","Dr.Ashraful", R.drawable.ic_pdf))
        item.add(Report("1212/24","Dr.Ashraful Alam", R.drawable.ic_pdf))

        val adapter = ReportAdapter(item, requireContext())
        binding.reportListRecycle.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.reportListRecycle.adapter = adapter
        adapter.onItemClick={
            val bundle = Bundle()
            bundle.putString("name",it.diagnosticName)
            findNavController().navigate(R.id.action_reportListFragment_to_reportDetailsFragment,bundle)
        }
        return binding.root
    }
}