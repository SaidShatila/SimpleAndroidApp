package com.example.simpleandroidapp.ui.fragments.timer

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.simpleandroidapp.data.datastore.PrefStoreImplementation
import com.example.simpleandroidapp.databinding.FragmentTimerBinding
import com.example.simpleandroidapp.utils.TimerUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding

    @Inject
    lateinit var prefStoreImplementation: PrefStoreImplementation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            binding.tvDateLoggedIn.text = prefStoreImplementation.getUserLoginDate().first()
            TimerUtils.timeFlow.collect { formattedTime ->
                binding.tvTimer.text = formattedTime
            }

        }
    }
}