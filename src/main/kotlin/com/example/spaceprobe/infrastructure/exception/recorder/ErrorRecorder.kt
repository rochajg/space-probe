package com.example.spaceprobe.infrastructure.exception.recorder

import com.example.spaceprobe.infrastructure.exception.handler.ApiError
import javax.servlet.http.HttpServletRequest

interface ErrorRecorder {
    fun recordError(request: HttpServletRequest?, ex: Exception, apiError: ApiError, customMessage: String?)
}
