package com.example.themoviedb_android.utils.extensions

import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb_android.views.RecyclerViewItemDecoration

fun RecyclerView.setItemDecorationSpacing(padding: Float) {
    addItemDecoration(RecyclerViewItemDecoration(padding.toInt()))
}