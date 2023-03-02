package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageC
import com.bearya.intelliscreen.databinding.ModelPVVUDBinding
import com.bearya.intelliscreen.library.tool.Storage
import com.bearya.intelliscreen.parts.video.VideoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

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

        val item = arguments?.getSerializable("item") as? PageC?

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
            .into(bindView.videoUp)

        val videoBPath = Storage.getUsbDir(requireContext()) + item?.videoB

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoBPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.videoDown)

        bindView.videoUp.requestFocus()

        bindView.videoUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoUp.setBorderWidth(width)
        }
        bindView.videoUp.setOnClickListener {  VideoActivity.start(requireContext() , videoAPath) }

        bindView.videoDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoDown.setBorderWidth(width)
        }
        bindView.videoDown.setOnClickListener {  VideoActivity.start(requireContext() , videoBPath) }

    }

}