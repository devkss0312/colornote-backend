package com.colornote.app.service


import com.colornote.app.model.Note
import org.jdom2.input.SAXBuilder
import org.jdom2.Element
import org.springframework.stereotype.Service
import java.io.File

@Service
class MusicXmlParser {

    fun parseMusicXml(xmlFile: File): List<Note> {
        val builder = SAXBuilder()
        val document = builder.build(xmlFile)
        val rootElement = document.rootElement

        val notes = mutableListOf<Note>()

        val partList = rootElement.getChildren("part")
        for (part in partList) {
            val measures = part.getChildren("measure")
            for (measure in measures) {
                val noteElements = measure.getChildren("note")
                for (noteElement in noteElements) {
                    val pitchElement = noteElement.getChild("pitch")
                    if (pitchElement != null) {
                        val step = pitchElement.getChildText("step")
                        val octave = pitchElement.getChildText("octave")
                        notes.add(Note(step, octave))
                    }
                }
            }
        }

        return notes
    }
}