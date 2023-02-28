package com.bearya.intelliscreen.data.bean

/**
 * 二级菜单
 */
data class MenuChapter(
    val id : Int,
    /**
     * 二级菜单图标位置
     */
    val itemChapterIcon: String,
    /**
     * 对应三级课件文件配置
     */
    val itemFile:String
)
