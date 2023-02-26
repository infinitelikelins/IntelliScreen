package com.bearya.intelliscreen.parts.code

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bearya.intelliscreen.http.Api
import com.bearya.intelliscreen.library.ext.Empty
import com.bearya.intelliscreen.library.ext.Fail
import com.bearya.intelliscreen.library.ext.NoNet
import java.net.UnknownHostException

class CodeViewModel : ViewModel() {

    fun activateVerify(code: String?): LiveData<Int?> = liveData {
        when {
            code.isNullOrBlank() -> emit(Empty)
            else -> emit(try {
                Api.activateVerify(code)?.data
            } catch (ex: UnknownHostException) {
                NoNet
            } catch (ex: Exception) {
                Fail
            })
        }
    }


}