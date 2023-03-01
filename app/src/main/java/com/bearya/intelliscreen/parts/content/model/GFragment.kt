package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageG
import com.bearya.intelliscreen.databinding.ModelPVVVVBinding
import com.bearya.intelliscreen.library.tool.Storage
import com.bearya.intelliscreen.parts.video.VideoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * 模板7： 背景一张 + 视频四个 (PVVVV)
 */
class GFragment : Fragment() {

    companion object {
        fun newInstance(item: PageG?): GFragment =
            GFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVVVVBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVVVVBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val item = arguments?.getSerializable("item") as? PageG?

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
            .into(bindView.videoLeftUp)

        val videoBPath = Storage.getUsbDir(requireContext()) + item?.videoB

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoBPath)
            .into(bindView.videoLeftDown)

        val videoCPath = Storage.getUsbDir(requireContext()) + item?.videoC

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoCPath)
            .into(bindView.videoRightUp)

        val videoDPath = Storage.getUsbDir(requireContext()) + item?.videoD

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoDPath)
            .into(bindView.videoRightDown)

        bindView.videoLeftUp.requestFocus()

        bindView.videoLeftUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeftUp.setBorderWidth(width)
        }
        bindView.videoLeftDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeftDown.setBorderWidth(width)
        }
        bindView.videoRightUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightUp.setBorderWidth(width)
        }
        bindView.videoRightDown.onFocusChangeListener= View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightDown.setBorderWidth(width)
        }

        bindView.videoLeftUp.setOnClickListener {  VideoActivity.start(requireContext(),videoAPath) }
        bindView.videoLeftDown.setOnClickListener {  VideoActivity.start(requireContext(),videoBPath) }
        bindView.videoRightUp.setOnClickListener {  VideoActivity.start(requireContext(),videoCPath) }
        bindView.videoRightDown.setOnClickListener {  VideoActivity.start(requireContext(),videoDPath) }

    }

}
