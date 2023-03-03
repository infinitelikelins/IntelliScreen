package com.bearya.intelliscreen.data.bean

/**
 * 一级菜单
 */
data class HomeMenus(
    // 主键
    val id: Int,
    // 菜单名，english
    val name: String,
    // 菜单标题 ，chinese
    val title: String,
    // 菜单图标(目标文件完整地址)
    val icon: String,
    // 菜单对应二级文件目录名(目标文件完整地址)
    val file: String,
    // 二级目录对应标题(目标文件完整地址)
    val subTitleIcon: String,
    // 二级目录对应背景(目标文件完整地址)
    val subContainerIcon: String
)

