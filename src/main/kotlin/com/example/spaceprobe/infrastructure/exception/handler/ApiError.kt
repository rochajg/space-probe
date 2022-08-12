package com.example.spaceprobe.infrastructure.exception.handler

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

@JsonInclude(Include.NON_NULL)
data class ApiError(
    val error: String,
    val message: String?,
    val status: Int,
    val cause: String? = null
)
