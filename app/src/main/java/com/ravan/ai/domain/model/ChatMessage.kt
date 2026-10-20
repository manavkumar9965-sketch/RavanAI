package com.ravan.ai.domain.model

enum class Sender { USER, RAVAN }

data class ChatMessage(
    val id: Long,
    val sender: Sender,
    val text: String,
    val timestampMillis: Long
)
