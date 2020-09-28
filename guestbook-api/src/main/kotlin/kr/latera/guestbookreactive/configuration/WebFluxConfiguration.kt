package kr.latera.guestbookreactive.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class WebFluxConfiguration: WebFluxConfigurer {

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/api/v1/**")
      .allowedOrigins("*")
      .allowCredentials(true)
      .exposedHeaders("Location")
  }
}
