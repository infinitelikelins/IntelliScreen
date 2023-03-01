package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageB
import com.bearya.intelliscreen.databinding.ModelPVBinding
import com.bearya.intelliscreen.library.tool.Storage
import com.bearya.intelliscreen.parts.video.VideoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
        val item = arguments?.getSerializable("item") as? PageB?

        val backgroundPath = Storage.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .into(bindView.background)

        val videoPath = Storage.getUsbDir(requireContext()) + item?.video

        Glide.with(view)
            .setDefaultRequestOptions(RequestOptions()
                .frame(10)
                .skipMemoryCache(true)
                .fitCenter())
            .load(videoPath)
            .into(bindView.videoCover)

        bindView.videoCover.requestFocus()

        bindView.videoCover.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoCover.setBorderWidth(width)
        }
        bindView.videoCover.setOnClickListener {
            VideoActivity.start(requireContext() , videoPath)
        }
    }

}