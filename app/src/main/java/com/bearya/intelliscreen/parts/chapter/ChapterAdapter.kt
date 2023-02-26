package com.bearya.intelliscreen.parts.chapter

import android.view.View
import androidx.core.view.ViewCompat
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.MenuChapter
import com.bearya.intelliscreen.databinding.ItemMenuChapterBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ChapterAdapter : BaseQuickAdapter<MenuChapter, ChapterViewHolder>(R.layout.item_menu_chapter) {

    private var lastPosition = 0

    override fun convert(holder: ChapterViewHolder, item: MenuChapter) {

        holder.setImageResource(R.id.item_chapter, item.itemChapterIcon)

        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                lastPosition = holder.bindingAdapterPosition
            }
            // 焦点位置缩放
            val scale = if (hasFocus) 1.08f else 0.75f
            ViewCompat.animate(holder.bindView.itemChapter).scaleX(scale).scaleY(scale).start()
            val width = if (hasFocus) 3 else 0
            holder.bindView.itemChapter.setBorderWidth(width)
        }
        // 焦点位置定位
        if (lastPosition == holder.bindingAdapterPosition) {
            recyclerView.smoothScrollToPosition(lastPosition)
            holder.itemView.post { holder.itemView.requestFocus() }
        }

    }

}

class ChapterViewHolder(itemView: View) : BaseViewHolder(itemView) {
    val bindView: ItemMenuChapterBinding = ItemMenuChapterBinding.bind(itemView)
}