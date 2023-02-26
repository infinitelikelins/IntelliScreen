package com.bearya.intelliscreen.data.bean

data class ChapterPage(
    val A: PageA? = null,
    val B: PageB? = null,
    val C: PageC? = null,
    val D: PageD? = null,
    val E: PageE? = null,
    val F: PageF? = null,
    val G: PageG? = null,
    val H: PageH? = null,
    val I: PageI? = null
)

data class PageA(
    val background: String
) : java.io.Serializable

data class PageB(
    val background: String,
    val video: String
) : java.io.Serializable

data class PageC(
    val background: String
) : java.io.Serializable

data class PageD(
    val background: String
) : java.io.Serializable

data class PageE(
    val background: String
) : java.io.Serializable

data class PageF(
    val background: String
) : java.io.Serializable

data class PageG(
    val background: String
) : java.io.Serializable

data class PageH(
    val background: String
) : java.io.Serializable

data class PageI(
    val background: String
) : java.io.Serializable