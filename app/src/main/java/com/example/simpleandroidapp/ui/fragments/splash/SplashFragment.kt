package com.example.simpleandroidapp.ui.fragments.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.simpleandroidapp.R
import com.example.simpleandroidapp.data.datastore.PrefStoreImplementation
import com.example.simpleandroidapp.databinding.FragmentSplashBinding
import com.example.simpleandroidapp.ui.activities.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var prefStoreImplementation: PrefStoreImplementation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val state = prefStoreImplementation.getRememberMeState().first()
            delay(3000)
            when (state) {
                true -> {
                    startActivity(Intent(requireActivity(), HomeActivity::class.java))
                    requireActivity().finish()
                }
                false -> {
                    if (findNavController().currentDestination?.id == R.id.splashFragment)
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        }
    }

}