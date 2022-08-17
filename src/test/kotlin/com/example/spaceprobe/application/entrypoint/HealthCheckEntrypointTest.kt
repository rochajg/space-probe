package com.example.spaceprobe.application.entrypoint

import com.example.spaceprobe.application.entrypoint.client.HealthCheckClient
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckEntrypointTest(
    @Autowired
    val client: HealthCheckClient
) {

    @Test
    fun `test ping - pong entrypoint`() {
        // given
        // when
        val response = this.client.ping()

        // then
        response.status shouldBe 200
        response.body shouldBe "pong"
    }
}
