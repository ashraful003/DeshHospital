package com.myapp.deshhospital.presentation.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.databinding.FragmentLoginInputBinding
import com.myapp.deshhospital.util.DHActivityUtil
import com.myapp.deshhospital.util.SharePreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class LoginInputFragment : Fragment() {
    val actionSignUp = Navigation.createNavigateOnClickListener(R.id.action_loginInputFragment_to_loginCreateFragment)
    val actionForgotPassword = Navigation.createNavigateOnClickListener(R.id.action_loginInputFragment_to_loginForgotPasswordFragment)
    @Inject
    lateinit var activityUtil: DHActivityUtil
    private lateinit var binding: FragmentLoginInputBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_input, container, false)
        binding.model = this
        activityUtil.hideBottomNavigation(true)
        isEnableSignInButton(false)
        val emailStream = RxTextView.textChanges(binding.emailEt)
            .skipInitialValue()
            .map { email->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            binding.emailEt.error = if (it) getString(R.string.error_email) else null
        }

        val passswordStream = RxTextView.textChanges(binding.passwordEt)
            .skipInitialValue()
            .map { password->
                password.isEmpty()
            }

        val invalidFiledStream = io.reactivex.Observable.combineLatest(
            emailStream,
            passswordStream
        ){
            emailInvalid:Boolean, passwordInvalid:Boolean ->
            !emailInvalid && !passwordInvalid
        }
        invalidFiledStream.subscribe { isValid ->
            isEnableSignInButton(isValid)
        }
        return binding.root
    }
    private fun isEnableSignInButton(isEnable:Boolean){
        if (isEnable){
            binding.btnSignIn.isEnabled = true
            binding.btnSignIn.backgroundTintList = ContextCompat.getColorStateList(requireActivity(),R.color.colorPrimary)
        }else{
            binding.btnSignIn.isEnabled = false
            binding.btnSignIn.backgroundTintList = ContextCompat.getColorStateList(requireActivity(),R.color.blue_500)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }
}