package com.example.condiut.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.condiut.AuthViewModel
import com.example.condiut.databinding.LoginSignupFramentBinding

class LoginFragment: Fragment() {
    private var _binding: LoginSignupFramentBinding?=null
     val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginSignupFramentBinding.inflate(inflater, container,false)
        _binding?.usernameEditText?.isVisible=false
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            loginButton.setOnClickListener {
                authViewModel.login(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}