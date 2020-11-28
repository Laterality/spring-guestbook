package kr.latera.guestbookreactive.handler

import kr.latera.guestbookreactive.domain.GuestbookPost
import kr.latera.guestbookreactive.domain.GuestbookService
import kr.latera.guestbookreactive.handler.dto.GuestbookPostListResponse
import kr.latera.guestbookreactive.handler.dto.GuestbookPostResponse
import kr.latera.guestbookreactive.domain.dto.GuestbookPublishRequest
import kr.latera.guestbookreactive.handler.dto.ErrorResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI

@Component
class GuestbookHandler(
  private val service: GuestbookService
) {
  fun publishGuestbookPost(request: ServerRequest): Mono<ServerResponse> =
    request.bodyToMono(GuestbookPublishRequest::class.java)
      .flatMap {
        service.publish(it)
      }
      .flatMap {
        ServerResponse
          .created(buildPostLocationUri(it))
          .build()
      }
      .onErrorResume {
        ServerResponse.badRequest().body(Mono.just(ErrorResponse(it.message!!)), ErrorResponse::class.java)
      }

  private fun buildPostLocationUri(it: GuestbookPost): URI {
    return UriComponentsBuilder.fromUriString(BASE_PATH)
      .pathSegment(it.id.toString())
      .build().toUri()
  }

  fun retrieveGuestbookPost(request: ServerRequest): Mono<ServerResponse> =
    service.findPostById(request.pathVariable(PATH_VARIABLE_KEY_POST_ID).toLong())
      .flatMap {
        ServerResponse.ok()
          .body(
            Mono.just(
              GuestbookPostResponse(
                it.id,
                it.content
              )
            ),
            GuestbookPostResponse::class.java
          )
      }

  fun retrieveGuestbookPosts(request: ServerRequest): Mono<ServerResponse> =
    service.findAllPosts()
      .map {
        GuestbookPostResponse(
          it.id,
          it.content
        )
      }
      .collectList()
      .flatMap {
        ServerResponse.ok()
          .body(Mono.just(GuestbookPostListResponse(it)), GuestbookPostListResponse::class.java)
      }

  companion object {
    const val BASE_PATH = "/api/v1/guestbook/posts"
    const val PATH_VARIABLE_KEY_POST_ID = "postId"
    const val BASE_PATH_APPEND_ID = "/{$PATH_VARIABLE_KEY_POST_ID}"
  }
}
