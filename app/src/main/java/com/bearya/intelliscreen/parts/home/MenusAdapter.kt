package com.bearya.intelliscreen.parts.home

import android.view.View
import androidx.core.view.ViewCompat
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.HomeMenus
import com.bearya.intelliscreen.databinding.ItemHomeMenusBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class MenusAdapter : BaseQuickAdapter<HomeMenus,  MenusViewHolder>(R.layout.item_home_menus) {

    // 焦点记忆的位置
    private var lastPosition = 0
    override fun convert(holder: MenusViewHolder, item: HomeMenus) {

        holder.bindView.unitCover.setImageResource(item.icon)

        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                lastPosition = holder.bindingAdapterPosition
            }
            // 焦点位置缩放
            val scale = if (hasFocus) 1.08f else 0.75f
            ViewCompat.animate(v).scaleX(scale).scaleY(scale).start()
            val width = if (hasFocus) 3 else 0
            holder.bindView.unitCover.setBorderWidth(width)
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