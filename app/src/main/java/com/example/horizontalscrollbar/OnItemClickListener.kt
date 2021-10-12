package com.example.horizontalscrollbar

import android.view.View

typealias OnItemClickListener<T> = (View, T, Int) -> Unit

typealias OnButtonClickListener<T> = (T) -> Unit
