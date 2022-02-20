package com.example.simpleandroidapp.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.simpleandroidapp.data.TimerUtils
import com.example.simpleandroidapp.databinding.DialogLogoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutDialog :
    DialogFragment() {

    private lateinit var binding: DialogLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutListeners()
    }

    private fun setLayoutListeners() {
        binding.apply {
            btCancel.setOnClickListener {
                dismiss()
            }
            btLogout.setOnClickListener {
                dismiss()
                TimerUtils.resetTimer()
            }
        }

    }
}