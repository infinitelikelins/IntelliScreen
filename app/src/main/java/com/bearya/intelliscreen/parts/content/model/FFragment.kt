package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageF
import com.bearya.intelliscreen.databinding.ModelPVVVLMRBinding

/**
 * 模板6 ： 背景一张 + 左中右视频三个 (PVVVLMR)
 */
class FFragment : Fragment() {

    companion object {
        fun newInstance(item: PageF?): FFragment =
            FFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVVVLMRBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVVVLMRBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindView.videoLeft.requestFocus()

        bindView.videoLeft.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeft.setBorderWidth(width)
        }
        bindView.videoRight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRight.setBorderWidth(width)
        }
        bindView.videoMiddle.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoMiddle.setBorderWidth(width)
        }

        bindView.videoLeft.setOnClickListener {  }
        bindView.videoRight.setOnClickListener {  }
        bindView.videoMiddle.setOnClickListener {  }
    }

}