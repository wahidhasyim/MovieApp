package com.wahidhasyim.movieapp.data.network.exception

import java.io.IOException

class UnknownException: IOException() {
    override val message: String?
        get() = "Some Unknown Error Occurred"
}