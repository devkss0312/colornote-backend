package com.colornote.app.model

data class Note(
        val step: String,    // 음의 이름 (C, D, E, F, G, A, B)
        val octave: String   // 옥타브 (1, 2, 3, 4, 5 등)
)