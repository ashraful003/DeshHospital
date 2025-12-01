package com.myapp.deshhospital.presentation.dashboard.home
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
import com.myapp.deshhospital.databinding.FragmentHomeBinding
import com.myapp.deshhospital.presentation.dashboard.adapter.MedicineAdapter
import com.myapp.deshhospital.presentation.dashboard.adapter.PrescriptionAdapter
import com.myapp.deshhospital.presentation.model.Medicine
import com.myapp.deshhospital.presentation.model.Prescription
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var activityUtil: DHActivityUtil
    val actionDoctor = Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_doctorFragment)
    val actionAppointment= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_allSpecialtiesFragment)
    val actionPrescription= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_prescriptionHomeFragment)
    val actionReport= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_reportHomeFragment)
    val actionDischarge= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_dischargeHomeFragment)
    val actionAdmission= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_admissionListFragment)
    val actionEmergency= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_emergencyContactFragment)
    val actionBlood= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_bloodDonorListFragment)
    val actionAmbulance= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_ambulanceListFragment)
    val actionPharmaceutical= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_pharmaceuticalListFragment)
    val actionMedicineStore= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_pharmaceuticalActivityFragment)
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(false)
        activityUtil.hideDrawerNavigation(false)

        return binding.root
    }
}