package com.bearya.intelliscreen.data.bean

data class HomeMenus(
    // 主键
    val id: Int,
    // 菜单名，english
    val name: String,
    // 菜单标题 ，chinese
    val title: String,
    // 菜单图标
    val icon: String,
    // 菜单对应二级文件目录名
    val file: String,
    // 二级目录对应标题
    val subTitleIcon: String,
    // 二级目录对应背景
    val subContainerIcon: String
)

