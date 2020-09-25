package kr.latera.guestbookreactive.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * 게시물을 나타내는 엔티티
 */
@Entity
class GuestbookPost(
  id: Long? = null,
  val content: String
) {
  @Id
  @org.springframework.data.annotation.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = id
  protected set
}
