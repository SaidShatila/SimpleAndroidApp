package com.example.simpleandroidapp.ui.fragments.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.simpleandroidapp.utils.TimerUtils
import com.example.simpleandroidapp.databinding.FragmentTimerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            TimerUtils.timeFlow.collect { formattedTime ->
                binding.tvTimer.text = formattedTime
            }
        }
    }
}