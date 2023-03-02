package com.bearya.intelliscreen.parts.content

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.databinding.EmptyListBinding

class EmptyFragment : Fragment() {

    companion object {
        fun newInstance(): EmptyFragment = EmptyFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = EmptyListBinding.inflate(inflater, container, false)
        binding.root.setBackgroundColor(Color.parseColor("#2F83CA"))
        return binding.root
    }

}