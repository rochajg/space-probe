package com.example.spaceprobe.application.exception

class BadRequestException(
    message: String? = null,
    cause: Throwable? = null,
    source: Class<*>? = null,
    event: String? = null
) : ApiException(
    message = message,
    cause = cause,
    error = "bad_request",
    statusCode = 400,
    source = source,
    event = event
)
