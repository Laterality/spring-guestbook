package kr.latera.guestbookreactive.domain

import kr.latera.guestbookreactive.domain.dto.GuestbookPublishRequest
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@Validated
@Service
class GuestbookService(
  private val repository: GuestbookPostRepository
) {
  fun publish(@Valid guestbookPublishRequest: GuestbookPublishRequest): Mono<GuestbookPost> {
    return repository.save(GuestbookPost(content = guestbookPublishRequest.content))
  }

  fun findPostById(id: Long): Mono<GuestbookPost> =
    repository.findById(id)

  fun findAllPosts(): Flux<GuestbookPost> =
    repository.findAll()
}
