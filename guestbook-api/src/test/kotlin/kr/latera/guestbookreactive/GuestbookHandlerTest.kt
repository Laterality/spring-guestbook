package kr.latera.guestbookreactive

import kr.latera.guestbookreactive.handler.GuestbookHandler
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.EntityExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class GuestbookHandlerTest {

  @field:Autowired
  private lateinit var client: WebTestClient

  @Test
  internal fun `방명록을 게시하고 조회할 수 있다`() {
    val contents = "Hello, Reactor!"

    publishGuestbookPost(contents) { publishResponse ->
      retrieveSingleGuestbookPost(publishResponse, contents)
    }
  }

  private fun publishGuestbookPost(content: String, consumer: ((EntityExchangeResult<ByteArray>) -> Unit)? = null) {
    val jsonBody =
      """
      {
        "content": "$content"
      }
      """.trimIndent()

    client.post()
      .uri(GuestbookHandler.BASE_PATH)
      .contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(jsonBody), String::class.java)
      .exchange()
      .expectStatus().isCreated
      .expectHeader().valueMatches("Location", "${GuestbookHandler.BASE_PATH}/.*")
      .expectBody()
      .consumeWith {
        consumer?.invoke(it)
      }
  }

  private fun retrieveSingleGuestbookPost(publishResponse: EntityExchangeResult<ByteArray>, contents: String) {
    client.get()
      .uri(publishResponse.responseHeaders.location!!.toASCIIString())
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.content").isEqualTo(contents)
      .jsonPath("$.id").isNumber
  }

  @Test
  internal fun `전체 방명록 게시물을 조회한다`() {
    publishGuestbookPost("Hello, Spring!")
    client.get()
      .uri(GuestbookHandler.BASE_PATH)
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("posts").isArray
      .jsonPath("posts").isNotEmpty
  }
}
