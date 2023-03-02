package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageE
import com.bearya.intelliscreen.databinding.ModelPVVVLRRBinding
import com.bearya.intelliscreen.library.tool.Storage
import com.bearya.intelliscreen.parts.video.VideoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


/**
 * 模板5： 背景一张 + 视频三个--左下,右上,右下 (PVVVLRR)
 */
class EFragment : Fragment() {

    companion object {
        fun newInstance(item: PageE?): EFragment =
            EFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVVVLRRBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVVVLRRBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val item = arguments?.getSerializable("item") as? PageE?

        val backgroundPath = Storage.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.background)


        val videoAPath = Storage.getUsbDir(requireContext()) + item?.videoA

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoAPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.videoLeftDown)

        val videoBPath = Storage.getUsbDir(requireContext()) + item?.videoB

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoBPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.videoRightUp)

        val videoCPath = Storage.getUsbDir(requireContext()) + item?.videoC

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoCPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.videoRightDown)

        bindView.videoLeftDown.requestFocus()

        bindView.videoLeftDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeftDown.setBorderWidth(width)
        }
        bindView.videoLeftDown.setOnClickListener {  VideoActivity.start(requireContext(),videoAPath) }

        bindView.videoRightDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightDown.setBorderWidth(width)
        }
        bindView.videoRightDown.setOnClickListener {  VideoActivity.start(requireContext(),videoCPath) }

        bindView.videoRightUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightUp.setBorderWidth(width)
        }
        bindView.videoRightUp.setOnClickListener { VideoActivity.start(requireContext(),videoBPath) }

    }

}