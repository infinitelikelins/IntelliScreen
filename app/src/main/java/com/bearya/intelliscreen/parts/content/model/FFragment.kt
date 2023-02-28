package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageF
import com.bearya.intelliscreen.databinding.ModelPVVVLMRBinding
import com.bearya.intelliscreen.library.tool.StorageTool
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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

        val item = arguments?.getSerializable("item") as? PageF?

        val backgroundPath = StorageTool.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .into(bindView.background)

        val videoAPath = StorageTool.getUsbDir(requireContext()) + item?.videoA

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoAPath)
            .into(bindView.videoLeft)

        val videoBPath = StorageTool.getUsbDir(requireContext()) + item?.videoB

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoBPath)
            .into(bindView.videoMiddle)

        val videoCPath = StorageTool.getUsbDir(requireContext()) + item?.videoC

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoCPath)
            .into(bindView.videoRight)

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