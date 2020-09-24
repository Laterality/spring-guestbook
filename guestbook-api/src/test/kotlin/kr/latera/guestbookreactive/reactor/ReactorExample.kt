package kr.latera.guestbookreactive.reactor

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

class ReactorExample {
  private val logger = LoggerFactory.getLogger(javaClass)

  private fun createFlux(): Flux<String> = Flux.just("A", "B", "C")

  @Test
  internal fun `구독`() {
    createFlux()
      .subscribe(
        {
          logger.info("Got {}", it)
        },
        {
          logger.error("Error while subscribing", it)
        },
        {
          logger.info("Done")
        },
        {
          it.request(2)
//              it.cancel()
        }
      )

    createFlux()
      .subscribe(LoggingSubscriber())
  }

  /**
   * [org.reactivestreams.Subscriber]를 직접 구현해도 되지만 TCK를 만족하도록 구현하기 까다로우므로
   * 미리 정의된 템플릿을 확장하는 방식이 낫다.
   */
  class LoggingSubscriber : BaseSubscriber<String>() {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun hookOnNext(value: String) {
      logger.info("Got: {}", value)
    }
  }
}
