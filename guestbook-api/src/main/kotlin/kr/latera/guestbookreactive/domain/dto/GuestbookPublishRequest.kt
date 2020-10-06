package kr.latera.guestbookreactive.domain.dto

import javax.validation.constraints.NotBlank

data class GuestbookPublishRequest(
  @field:NotBlank
  val content: String
)
