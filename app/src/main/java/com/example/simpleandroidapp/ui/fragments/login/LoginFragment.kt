package com.example.simpleandroidapp.ui.fragments.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simpleandroidapp.R
import com.example.simpleandroidapp.databinding.FragmentLoginBinding
import com.example.simpleandroidapp.ui.activities.HomeActivity
import com.example.simpleandroidapp.utils.TimerUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutListeners()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setLayoutListeners() {
        with(binding) {
            etEmail.addTextChangedListener {
                btLogin.isEnabled = it?.trim()?.isNotEmpty() == true
            }
            btLogin.setOnClickListener {
                if (areInputValid()) {
                    lifecycleScope.launch {
                        viewModel.saveState(cbStayLoggedIn.isChecked)
                        val currentTime = LocalDateTime.now()
                        val date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                        val myDate = currentTime.format(date)
                        viewModel.saveLoginDate(myDate)
                    }
                    startActivity(Intent(requireActivity(), HomeActivity::class.java))
                    TimerUtils.resetTimer()
                    requireActivity().finish()
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