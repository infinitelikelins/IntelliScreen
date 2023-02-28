package com.bearya.intelliscreen.data.event

data class PermissionEvent(val requestCode: Int, val permission: String, val grantResults: Int)
