package com.colornote.app.controller
import com.colornote.app.service.AudiverisService
import com.colornote.app.service.ColorMappingService
import com.colornote.app.service.ImageColoringService
import com.colornote.app.service.MusicXmlParser
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import  java.io.File
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/colorize")
class ColorNoteController(
        private val audiverisService: AudiverisService,
        private val musicXmlParser: MusicXmlParser,
        private val colorMappingService: ColorMappingService,
        private val imageColoringService: ImageColoringService
) {

    @PostMapping
    fun colorizeNotes(@RequestParam("file") file: MultipartFile): ResponseEntity<FileSystemResource> {
        // 파일 저장
        val imageFile = File("uploaded_image.png")
        file.transferTo(imageFile)

        println(imageFile.absolutePath)
        // Audiveris로 악보 이미지 처리
        val xmlFile = audiverisService.runAudiveris(imageFile.absolutePath, "output")

        // MusicXML 파싱
        val notes = musicXmlParser.parseMusicXml(xmlFile)

        // 각 노트에 색상 적용
        val noteColors = notes.associateWith { colorMappingService.getColorForPitch(it) }

        // 이미지에 색상 적용
        val outputImagePath = "output_colored_image.png"
        imageColoringService.colorNotes(imageFile.absolutePath, notes, noteColors)

        // 색상이 적용된 이미지를 반환
        val fileResource = FileSystemResource(File(outputImagePath))
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"colored_image.png\"")
        return ResponseEntity(fileResource, headers, HttpStatus.OK)
    }
}