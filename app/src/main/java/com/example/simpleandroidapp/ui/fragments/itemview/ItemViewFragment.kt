package com.example.simpleandroidapp.ui.fragments.itemview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simpleandroidapp.data.remote.response.Todos
import com.example.simpleandroidapp.databinding.FragmentItemViewBinding
import com.example.simpleandroidapp.utils.Constants.KeyUtils.Companion.KEY_ITEM_TODOS


class ItemViewFragment : Fragment() {
    private lateinit var binding: FragmentItemViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<Todos>(KEY_ITEM_TODOS)

        with(binding) {
            data?.apply {
                tvCompleted.text = completed.toString()
                tvId.text = id.toString()
                tvUserId.text = userId.toString()
                tvName.text = title
            }

        }
    }
}