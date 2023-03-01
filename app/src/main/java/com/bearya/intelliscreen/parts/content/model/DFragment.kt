package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageD
import com.bearya.intelliscreen.databinding.ModelPVVLRBinding
import com.bearya.intelliscreen.library.tool.Storage
import com.bearya.intelliscreen.parts.video.VideoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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

        val item = arguments?.getSerializable("item") as? PageD?

        val backgroundPath = Storage.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .into(bindView.background)


        val videoAPath = Storage.getUsbDir(requireContext()) + item?.videoA

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoAPath)
            .into(bindView.videoLeft)

        val videoBPath = Storage.getUsbDir(requireContext()) + item?.videoB

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoBPath)
            .into(bindView.videoRight)

        bindView.videoLeft.requestFocus()

        bindView.videoLeft.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeft.setBorderWidth(width)
        }
        bindView.videoLeft.setOnClickListener {  VideoActivity.start(requireContext() , videoAPath) }
        bindView.videoRight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRight.setBorderWidth(width)
        }
        bindView.videoRight.setOnClickListener {  VideoActivity.start(requireContext() , videoBPath) }

    }

}