package com.colornote.app.service

import org.springframework.stereotype.Service
import java.io.File
import java.io.FileNotFoundException

@Service
class AudiverisService {

    fun runAudiveris(imagePath: String, outputDir: String): File {
        val audiverisJar = "C:/Users/I'm a Gay/workspace/colornote-backend/libs/audiveris.jar"
        val process = ProcessBuilder("java", "-jar", audiverisJar, "-batch", imagePath)
                .directory(File(outputDir))  // 작업 디렉토리를 outputDir로 설정
                .redirectErrorStream(true)   // 에러 스트림도 함께 출력
                .start()

        val exitCode = process.waitFor()  // 프로세스 종료 대기

        if (exitCode != 0) {
            println("Audiveris 실행 중 오류 발생, 종료 코드: $exitCode")
            process.inputStream.bufferedReader().use {
                println(it.readText())  // Audiveris 오류 메시지 출력
            }
            throw RuntimeException("Audiveris 실행 실패")
        }

        // output_music.xml 파일 확인
        val outputFile = File(outputDir, "output_music.xml")
        if (!outputFile.exists()) {
            throw FileNotFoundException("Audiveris가 output_music.xml 파일을 생성하지 않았습니다.")
        }

        return outputFile
    }
}