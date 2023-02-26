package com.bearya.intelliscreen.http

object Api : HttpRetrofit() {

    private val api: ApiService? by lazy(mode = LazyThreadSafetyMode.NONE) {
        retrofit.create(ApiService::class.java)
    }

    /**
     * 课件激活验证
     */
    suspend fun activateVerify(code: String = "0"): HttpResult<Int>? = api?.activateVerify(code)

}