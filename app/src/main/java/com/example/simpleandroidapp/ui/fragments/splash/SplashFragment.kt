package com.example.simpleandroidapp.ui.fragments.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpleandroidapp.R
import com.example.simpleandroidapp.data.datastore.PrefStoreImplementation
import com.example.simpleandroidapp.databinding.FragmentSplashBinding
import com.example.simpleandroidapp.ui.activities.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val handler = Handler(Looper.getMainLooper())

    @Inject
    lateinit var prefStoreImplementation: PrefStoreImplementation
    private var state = false
    private val runnable = Runnable {

        runBlocking { state = prefStoreImplementation.getRememberMeState().first() }

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

        handler.postDelayed(runnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}