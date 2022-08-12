package com.example.spaceprobe.application.exception

open class ApiException(
    message: String? = null,
    cause: Throwable? = null,
    val error: String = "internal_error",
    val statusCode: Int = 500,
    val source: Class<*>?,
    val event: String?
) : RuntimeException(message, cause)
