package com.myapp.deshhospital

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.myapp.deshhospital.databinding.ActivityMainBinding
import com.myapp.deshhospital.util.DHActivityUtil
import com.myapp.deshhospital.util.SharePreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),DHActivityUtil.ActivityListener {
    @Inject
    lateinit var sharedPrefs:SharePreferenceUtil
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        setSupportActionBar(binding.toolbar)
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toggle) // use your hamburger icon here

        binding.vavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> showToast("Click Home")
                R.id.nav_profile -> showToast("Click Profile")
                R.id.nav_screening -> showToast("Click Screening")
                R.id.nav_invoice -> showToast("Click Invoice")
                R.id.nav_discharge -> showToast("Click Discharge")
                R.id.nav_changePass -> showToast("Click Change Password")
                R.id.nav_exit -> showToast("Click Exit")
            }
            binding.drawerLayout.closeDrawer(binding.vavView)
            true
        }



        val authUser:Boolean = try {
            !sharedPrefs.getAuthToken().isNullOrEmpty()
        }catch (e:Exception){
            false
        }
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentContainer) as NavHostFragment
        with(navHostFragment) {
            val inflater = findNavController().navInflater
            if (authUser){
                findNavController().graph = (inflater.inflate(R.navigation.dashboard_navigation))
            }else{
                findNavController().graph = (inflater.inflate(R.navigation.login_navigation))
            }
        }
        navController = navHostFragment.navController
        binding.bottomNavigationView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.home -> {}
                R.id.order -> {}
                R.id.profile -> {}
            }
            true
        }
    }
    companion object{
        fun getLaunchIntent(context: Context)= Intent(context,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Toggle manually
                if (binding.drawerLayout.isDrawerOpen(binding.vavView)) {
                    binding.drawerLayout.closeDrawer(binding.vavView)
                } else {
                    binding.drawerLayout.openDrawer(binding.vavView)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    override fun hideBottomNavigation(hide: Boolean) {
        if (hide){
            binding.bottomNavigationView.visibility = View.GONE
        }else{
            binding.bottomNavigationView.visibility = View.VISIBLE
        }
    }

    override fun setFullScreenLoading(short: Boolean) {
        if (short){
          binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
          binding.fullscreenLoading.visibility = View.VISIBLE
        }else{
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            binding.fullscreenLoading.visibility = View.GONE
        }
    }

    override fun hideDrawerNavigation(hide: Boolean) {
        if (hide){
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            binding.vavView.visibility = View.GONE
            binding.toolbar.visibility = View.GONE
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }else{
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            binding.vavView.visibility = View.VISIBLE
            binding.toolbar.visibility = View.VISIBLE
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}