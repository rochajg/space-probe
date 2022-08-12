package com.example.spaceprobe.infrastructure.exception.handler

import com.example.spaceprobe.application.exception.ApiException
import com.example.spaceprobe.infrastructure.exception.recorder.ErrorRecorder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionHandler(
    private val errorRecorders: List<ErrorRecorder>
) : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException, request: HttpServletRequest?): ResponseEntity<ApiError> {
        return recordErrorAndBuildResponseEntity(ex, ex.toApiError(), request)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: java.lang.Exception, request: HttpServletRequest?): ResponseEntity<ApiError> {
        return recordErrorAndBuildResponseEntity(ex, ex.toApiError(), request)
    }

    private fun recordErrorAndBuildResponseEntity(
        ex: Exception,
        errorResponse: ApiError,
        request: HttpServletRequest?
    ): ResponseEntity<ApiError> {
        return ResponseEntity(errorResponse, HttpStatus.valueOf(errorResponse.status))
            .also {
                recordError(
                    request = request,
                    ex = ex,
                    apiError = errorResponse
                )
            }
    }

    private fun recordError(
        request: HttpServletRequest?,
        ex: Exception,
        apiError: ApiError,
        customMessage: String? = apiError.message + (apiError.cause?.let { " - $it" } ?: "")
    ) {
        errorRecorders.map { it.recordError(request, ex, apiError, customMessage) }
    }

    private fun ApiException.toApiError(): ApiError =
        ApiError(
            error = this.error,
            message = this.message,
            status = this.statusCode,
            cause = this.cause?.message
        )

    private fun Exception.toApiError(): ApiError = ApiError(
        error = "internal_error",
        message = this.message ?: "Internal Server Error",
        status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        cause = this.cause?.message
    )
}
