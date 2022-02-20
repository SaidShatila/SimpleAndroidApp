package com.example.simpleandroidapp.ui.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.simpleandroidapp.data.datastore.PrefStoreImplementation
import com.example.simpleandroidapp.databinding.DialogLogoutBinding
import com.example.simpleandroidapp.ui.activities.MainActivity
import com.example.simpleandroidapp.utils.TimerUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogoutDialog :
    DialogFragment() {

    private lateinit var binding: DialogLogoutBinding

    @Inject
    lateinit var prefStoreImplementation: PrefStoreImplementation
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
                TimerUtils.resetTimer()
                lifecycleScope.launch {
                    prefStoreImplementation.saveRememberMeState(false)
                }
                dismiss()
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        }

    }
}