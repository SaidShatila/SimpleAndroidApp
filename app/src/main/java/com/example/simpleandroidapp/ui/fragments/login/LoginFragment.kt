package com.example.simpleandroidapp.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simpleandroidapp.R
import com.example.simpleandroidapp.utils.TimerUtils
import com.example.simpleandroidapp.databinding.FragmentLoginBinding
import com.example.simpleandroidapp.ui.activities.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutListeners()
    }


    private fun setLayoutListeners() {
        with(binding) {
            etEmail.addTextChangedListener {
                btLogin.isEnabled = it?.trim()?.isNotEmpty() == true
            }
            btLogin.setOnClickListener {
                if (areInputValid()) {
                    lifecycleScope.launch {
                        viewModel.saveState(cbStayLoggedIn.isChecked)
                    }
                    startActivity(Intent(requireActivity(), HomeActivity::class.java))
                    TimerUtils.resetTimer()
                    //  requireActivity().finish()
                }
            }
        }
    }

    private fun areInputValid(): Boolean {
        var isValid = true
        with(binding) {
            if (etEmail.text.isEmpty()) {
                isValid = false
                etEmail.error = getString(R.string.field_not_empty)
            } else etEmail.error = null

            if (etPassword.text.isEmpty()) {
                isValid = false
                etPassword.error = getString(R.string.field_not_empty)
            } else etPassword.error = null
        }
        return isValid
    }
}