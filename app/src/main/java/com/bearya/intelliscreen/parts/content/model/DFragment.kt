package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageD
import com.bearya.intelliscreen.databinding.ModelPVVLRBinding
import es.dmoral.toasty.Toasty

/**
 * 模板4 ： 背景一张 + 左右视频两个 (PVVLR)
 */
class DFragment : Fragment() {

    companion object {
        fun newInstance(item: PageD?): DFragment =
            DFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVVLRBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVVLRBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindView.videoLeft.requestFocus()

        bindView.videoLeft.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeft.setBorderWidth(width)
        }
        bindView.videoLeft.setOnClickListener {  Toasty.success(requireContext() , "up" , 2000).show() }
        bindView.videoRight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRight.setBorderWidth(width)
        }
        bindView.videoRight.setOnClickListener {  Toasty.success(requireContext() , "down" , 2000).show() }

    }

}