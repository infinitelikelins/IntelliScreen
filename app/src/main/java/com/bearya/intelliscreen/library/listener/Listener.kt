package com.bearya.intelliscreen.library.listener

import android.view.View

typealias OnItemSelectedListener<T> = (view: View, item: T?, position: Int) -> Unit