package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageK
import com.bearya.intelliscreen.databinding.ModelPVUBinding
import com.bearya.intelliscreen.library.tool.Storage
import com.bearya.intelliscreen.parts.video.VideoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class KFragment : Fragment() {

    companion object{
        fun newInstance(item: PageK?): KFragment =
            KFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVUBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = ModelPVUBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val item = arguments?.getSerializable("item") as? PageK?

        val backgroundPath = Storage.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.background)

        val videoPath = Storage.getUsbDir(requireContext()) + item?.video

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                .frame(10)
                .skipMemoryCache(true)
                .fitCenter())
            .load(videoPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.videoUp)

        bindView.videoUp.requestFocus()

        bindView.videoUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoUp.setBorderWidth(width)
        }
        bindView.videoUp.setOnClickListener {
            VideoActivity.start(requireContext() , videoPath)
        }

    }

}