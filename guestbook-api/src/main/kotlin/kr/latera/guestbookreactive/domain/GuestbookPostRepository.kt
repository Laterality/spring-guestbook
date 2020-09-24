package kr.latera.guestbookreactive.domain

import org.springframework.data.r2dbc.repository.R2dbcRepository

interface GuestbookPostRepository : R2dbcRepository<GuestbookPost, Long>
