package com.wahidhasyim.movieapp.util

import androidx.annotation.LayoutRes

interface IBaseView {
    @LayoutRes
    fun getLayoutResource(): Int
    fun initViews()
    fun initObservers()
    fun initData()
    fun setupToolbar()

}