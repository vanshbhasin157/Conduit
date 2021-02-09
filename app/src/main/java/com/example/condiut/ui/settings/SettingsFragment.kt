package com.example.condiut.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.condiut.AuthViewModel
import com.example.condiut.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    private val authViewModel by activityViewModels<AuthViewModel>()
    private var _binding: SettingsFragmentBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({ lifecycle }) {
            _binding?.apply {
                bioEditText.setText(it?.bio ?: "")
                usernameEditText.setText(it?.username ?: "")
                emailEditText.setText(it?.email ?: "")
                imageEditText.setText(it?.image ?: "")


            }
            _binding?.apply {
                updateSettingsButton.setOnClickListener{
                    authViewModel.update(
                        bioEditText.text.toString(),
                        usernameEditText.text.toString().takeIf { it.isNotEmpty() },
                        emailEditText.text.toString().takeIf { it.isNotEmpty() },
                        passwordEditText.text.toString().takeIf { it.isNotEmpty() },
                        imageEditText.text.toString()

                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}