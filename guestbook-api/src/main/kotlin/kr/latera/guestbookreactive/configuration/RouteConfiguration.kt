package kr.latera.guestbookreactive.configuration

import kr.latera.guestbookreactive.handler.GuestbookHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteConfiguration {

  @Bean
  fun routes(
    guestbookHandler: GuestbookHandler
  ): RouterFunction<ServerResponse> =
    nest(
      path(GuestbookHandler.BASE_PATH),
      router {
        listOf(
          POST("/", guestbookHandler::publishGuestbookPost),
          GET("/", guestbookHandler::retrieveGuestbookPosts),
          GET(GuestbookHandler.BASE_PATH_APPEND_ID, guestbookHandler::retrieveGuestbookPost)
        )
      }
    )
}
