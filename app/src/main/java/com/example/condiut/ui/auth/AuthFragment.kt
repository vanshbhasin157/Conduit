package com.example.condiut.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import com.example.condiut.databinding.AuthFragmentBinding
import com.google.android.material.tabs.TabLayout

class AuthFragment: Fragment() {
    private  var _binding: AuthFragmentBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AuthFragmentBinding.inflate(inflater,container,false)
        _binding?.authTabView?.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}