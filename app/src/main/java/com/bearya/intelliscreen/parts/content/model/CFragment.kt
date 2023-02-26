package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageC
import com.bearya.intelliscreen.databinding.ModelPVVUDBinding
import es.dmoral.toasty.Toasty

/**
 * 模板3 ： 背景一张 + 上下视频两个 (PVVUD)
 */
class CFragment : Fragment() {

    companion object {
        fun newInstance(item: PageC?): CFragment =
            CFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVVUDBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVVUDBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindView.videoUp.requestFocus()

        bindView.videoUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoUp.setBorderWidth(width)
        }
        bindView.videoUp.setOnClickListener {  Toasty.success(requireContext() , "up" , 2000).show() }
        bindView.videoDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoDown.setBorderWidth(width)
        }
        bindView.videoDown.setOnClickListener {  Toasty.success(requireContext() , "down" , 2000).show() }

    }

}