package com.bearya.intelliscreen.parts.home

import android.view.View
import androidx.core.view.ViewCompat
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.databinding.ItemHomeMenusBinding
import com.bearya.intelliscreen.library.listener.OnItemSelectedListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class MenusAdapter : BaseQuickAdapter<String,  MenusViewHolder>(R.layout.item_home_menus) {

    // 焦点记忆的位置
    private var lastPosition = 0

    var onItemSelectedListener: OnItemSelectedListener<String>? = null

    override fun convert(holder: MenusViewHolder, item: String) {
        holder.itemView.setOnClickListener { onItemSelectedListener?.invoke(it, item, holder.bindingAdapterPosition) }
        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                lastPosition = holder.bindingAdapterPosition
            }
            // 焦点位置缩放
            val scale = if (hasFocus) 1.18f else 1.0f
            ViewCompat.animate(v).scaleX(scale).scaleY(scale).start()
        }
        // 焦点位置定位
        if (lastPosition == holder.bindingAdapterPosition) {
            recyclerView.smoothScrollToPosition(lastPosition)
            holder.itemView.post { holder.itemView.requestFocus() }
        }
    }

}

class MenusViewHolder(itemView : View)  : BaseViewHolder(itemView) {
    val bindView: ItemHomeMenusBinding = ItemHomeMenusBinding.bind(itemView)
}