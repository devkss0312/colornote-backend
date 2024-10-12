package com.colornote.app.service

import com.colornote.app.model.Note
import org.springframework.stereotype.Service

@Service
class ColorMappingService {

    private val pitchToColor = mapOf(
            "C" to "red",
            "D" to "orange",
            "E" to "yellow",
            "F" to "green",
            "G" to "blue",
            "A" to "indigo",
            "B" to "violet"
    )

    fun getColorForPitch(note: Note): String {
        return pitchToColor.getOrDefault(note.step, "black")
    }
}