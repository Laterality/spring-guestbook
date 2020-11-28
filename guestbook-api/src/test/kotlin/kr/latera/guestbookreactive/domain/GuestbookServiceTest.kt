package kr.latera.guestbookreactive.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kr.latera.guestbookreactive.domain.dto.GuestbookPublishRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID
import javax.validation.ConstraintViolationException

@SpringBootTest
internal class GuestbookServiceTest {

  @Autowired
  private lateinit var service: GuestbookService

  @Autowired
  private lateinit var repository: GuestbookPostRepository

  @Test
  internal fun `게시물을 작성한다`() {
    val content = "Hello!"
    service.publish(GuestbookPublishRequest(content))
      .doOnNext { post ->
        post shouldNotBe null
        post!!.id shouldNotBe null
      }
      .flatMap {
        repository.findById(it.id!!)
      }
      .doOnNext {
        it.content shouldBe content
      }
  }

  @Test
  internal fun `빈 내용으로 생성을 시도하는 경우 예외를 던진다`() {
    shouldThrow<ConstraintViolationException> {
      service.publish(GuestbookPublishRequest(""))
    }
  }

  @Test
  internal fun `게시물 ID로 게시물을 조회한다`() {
    publishRandomPost()
      .flatMap {
        service.findPostById(it.id!!)
      }
      .doOnNext {
        it shouldNotBe null
      }
  }

  @Test
  internal fun `전체 게시물을 조회한다`() {
    publishRandomPost()
      .doOnNext {
        publishRandomPost()
      }
      .flatMapMany {
        service.findAllPosts()
      }
      .collectList()
      .doOnNext { result ->
        result.size shouldBeGreaterThanOrEqual 2
      }
  }

  private fun publishRandomPost() =
    service.publish(GuestbookPublishRequest(UUID.randomUUID().toString()))
}
