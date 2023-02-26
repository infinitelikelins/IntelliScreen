package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageB
import com.bearya.intelliscreen.databinding.ModelPVBinding
import es.dmoral.toasty.Toasty

/**
 * 模板2： 背景一张 + 视频一个（PV）
 */
class BFragment : Fragment() {

    companion object {
        fun newInstance(item: PageB?): BFragment =
            BFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.videoCover.requestFocus()
        bindView.videoCover.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoCover.setBorderWidth(width)
        }
        bindView.videoCover.setOnClickListener{ Toasty.success(requireContext() , "success" , 2000).show() }
    }

}