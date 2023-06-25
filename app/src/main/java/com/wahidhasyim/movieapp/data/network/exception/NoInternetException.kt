package com.wahidhasyim.movieapp.data.network.exception

import java.io.IOException

class NoInternetException: IOException() {
    override val message: String?
        get() = "No Internet Connection"
}