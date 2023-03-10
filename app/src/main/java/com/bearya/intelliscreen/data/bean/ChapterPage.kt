package com.bearya.intelliscreen.data.bean

/**
 * 三级课件
 */
data class ChapterPage(
    // 模板1 ： 纯图片 一张 (P)
    val A: PageA? = null,
    // 模板2： 背景一张 + 视频一个（PV）
    val B: PageB? = null,
    // 模板3 ： 背景一张 + 上下视频两个 (PVVUD)
    val C: PageC? = null,
    // 模板4 ： 背景一张 + 左右视频两个 (PVVLR)
    val D: PageD? = null,
    // 模板5： 背景一张 + 视频三个--左下,右上,右下 (PVVVLRR)
    val E: PageE? = null,
    // 模板6 ： 背景一张 + 左中右视频三个 (PVVVLMR)
    val F: PageF? = null,
    // 模板7： 背景一张 + 视频四个 (PVVVV)
    val G: PageG? = null,
    // 模板8： 背景一张 + 音频一个 （PA）
    val H: PageH? = null,
    // 模板9： 背景一张 + 音频三个 （PAAA）
    val I: PageI? = null,
    // 模板10： 背景一张 + 音频三个 （PAAALMR）
    val J:PageJ? = null
)

data class PageA(
    val background: String
) : java.io.Serializable

data class PageB(
    val background: String,
    val video: String
) : java.io.Serializable

data class PageC(
    val background: String,
    val videoA:String,
    val videoB:String
) : java.io.Serializable

data class PageD(
    val background: String,
    val videoA:String,
    val videoB:String
) : java.io.Serializable

data class PageE(
    val background: String,
    val videoA:String,
    val videoB:String,
    val videoC:String
) : java.io.Serializable

data class PageF(
    val background: String,
    val videoA:String,
    val videoB:String,
    val videoC:String
) : java.io.Serializable

data class PageG(
    val background: String,
    val videoA:String,
    val videoB:String,
    val videoC:String,
    val videoD:String
) : java.io.Serializable

data class PageH(
    val background: String,
    val audio:String
) : java.io.Serializable

data class PageI(
    val background: String,
    val audioA:String,
    val audioB:String,
    val audioC:String,
    val audioAIcon:String,
    val audioBIcon:String,
    val audioCIcon:String
) : java.io.Serializable

data class PageJ(
    val background: String,
    val audioA:String,
    val audioB:String,
    val audioC:String,
    val audioAIcon:String,
    val audioBIcon:String,
    val audioCIcon:String
) : java.io.Serializable