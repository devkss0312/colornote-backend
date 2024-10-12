package com.colornote.app.service

import com.colornote.app.model.Note
import org.bytedeco.opencv.global.opencv_core.*
import org.bytedeco.opencv.global.opencv_imgcodecs.*
import org.bytedeco.opencv.global.opencv_imgproc.*
import org.bytedeco.opencv.opencv_core.*
import org.springframework.stereotype.Service

@Service
class ImageColoringService {

    fun colorNotes(imagePath: String, notes: List<Note>, noteColors: Map<Note, String>) {
        val img = imread(imagePath)  // JavaCV로 이미지 읽기

        for (note in notes) {
            val color = getScalarColor(noteColors[note] ?: "black")
            val point = Point(100, 100)  // 실제 노트 위치를 대신할 임시 좌표
            circle(img, point, 10, color, -1, LINE_AA, 0)
        }

        // 결과 이미지 저장
        imwrite("output_colored_image.png", img)
    }

    private fun getScalarColor(colorName: String): Scalar {
        return when (colorName) {
            "red" -> Scalar(0.0, 0.0, 255.0, 0.0)       // 빨강
            "orange" -> Scalar(0.0, 165.0, 255.0, 0.0)  // 주황
            "yellow" -> Scalar(0.0, 255.0, 255.0, 0.0)  // 노랑
            "green" -> Scalar(0.0, 255.0, 0.0, 0.0)     // 초록
            "blue" -> Scalar(255.0, 0.0, 0.0, 0.0)      // 파랑
            "indigo" -> Scalar(130.0, 0.0, 75.0, 0.0)   // 남색
            "violet" -> Scalar(238.0, 130.0, 238.0, 0.0)  // 보라
            else -> Scalar(0.0, 0.0, 0.0, 0.0)  // 기본 검정색
        }
    }
}
