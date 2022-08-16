package com.example.spaceprobe.application.entrypoint.client

import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

open class BaseClient(
    context: WebApplicationContext
) {

    protected val objectMapper = MappingJackson2HttpMessageConverter().objectMapper
    protected val mvc = MockMvcBuilders.webAppContextSetup(context).build()

    protected inline fun <reified T> get(url: String): Response {
        return this.performRequest<T>(MockMvcRequestBuilders.get(url))
    }

    protected inline fun <reified T> delete(url: String?): Response {
        return this.performRequest<T>(MockMvcRequestBuilders.delete(url!!))
    }

    protected inline fun <reified T> post(url: String?): Response {
        return this.performRequest<T>(MockMvcRequestBuilders.post(url!!))
    }

    protected inline fun <reified T> post(url: String?, body: Any): Response {
        return this.performRequest<T>(MockMvcRequestBuilders.post(url!!), body)
    }

    protected inline fun <reified T> post(url: String?, body: Any, headers: Map<String, String>): Response {
        return this.performRequest<T>(MockMvcRequestBuilders.post(url!!), body, headers)
    }

    protected inline fun <reified T> put(url: String?): Response {
        return this.performRequest<T>(MockMvcRequestBuilders.put(url!!))
    }

    protected inline fun <reified T> put(url: String?, body: Any): Response {
        return this.performRequest<T>(MockMvcRequestBuilders.put(url!!), body)
    }

    protected fun getBodyAsString(body: Any?): String {
        return if (body is String) body else this.objectMapper.writeValueAsString(body)
    }

    protected inline fun <reified T> performRequest(
        requestBuilder: MockHttpServletRequestBuilder,
        body: Any
    ): Response {
        val bodyString = getBodyAsString(body)
        return this.performRequest<T>(requestBuilder.contentType(MediaType.APPLICATION_JSON).content(bodyString))
    }

    protected inline fun <reified T> performRequest(requestBuilder: MockHttpServletRequestBuilder): Response {
        return this.performRequest<T>(requestBuilder, mapOf())
    }

    protected inline fun <reified T> performRequest(
        requestBuilder: MockHttpServletRequestBuilder,
        body: Any,
        headers: Map<String, String>
    ): Response =
        this.performRequest<T>(
            requestBuilder.contentType(MediaType.APPLICATION_JSON)
                .content(getBodyAsString(body)),
            headers
        )

    protected inline fun <reified T> performRequest(
        requestBuilder: MockHttpServletRequestBuilder,
        headers: Map<String, String>
    ): Response {
        for (name in headers.keys) {
            requestBuilder.header(name, headers[name])
        }
        return this.mvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
            .andReturn()
            .response
            .let { this.mapResponse<T>(it) }
    }

    protected inline fun <reified T> mapResponse(response: MockHttpServletResponse): Response =
        Response(
            status = response.status,
            body = when {
                T::class.java.isAssignableFrom(String::class.java) -> response.contentAsString
                else -> this.objectMapper.readValue<T>(response.contentAsString)
            }
        )

    inner class Response(
        val status: Int,
        val body: Any?
    )
}
