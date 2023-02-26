package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.PageA
import com.bearya.intelliscreen.databinding.ModelPBinding
import com.bumptech.glide.Glide

/**
 * 模板1 ： 纯图片 一张 (P)
 */
class AFragment : Fragment() {

    companion object {
        fun newInstance(item: PageA?): AFragment =
            AFragment().apply { arguments?.putSerializable("item", item) }
    }

    private lateinit var bindView: ModelPBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = arguments?.getSerializable("item") as? PageA
        Glide.with(view)
            .asDrawable()
            .error(R.drawable.container_rhythm)
            .load(item?.background)
            .into(bindView.picture)
    }

}