import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

fun main() {
    // Generate a large file to increase JAR size
    generateLargeFile("large_data.bin", 45 * 1024 * 1024) // 45 MB

    println("Launching interactive bash session...")
    
    val processBuilder = ProcessBuilder("/bin/bash")
    processBuilder.directory(File(System.getProperty("user.home")))
    processBuilder.inheritIO()
    
    val process = processBuilder.start()
    process.waitFor()
    
    println("Bash session ended.")
}

fun generateLargeFile(fileName: String, sizeInBytes: Int) {
    val file = File(fileName)
    FileOutputStream(file).use { fos ->
        val buffer = ByteArray(8192)
        var bytesWritten = 0
        while (bytesWritten < sizeInBytes) {
            Random.nextBytes(buffer)
            val bytesToWrite = minOf(buffer.size, sizeInBytes - bytesWritten)
            fos.write(buffer, 0, bytesToWrite)
            bytesWritten += bytesToWrite
        }
    }
    println("Generated large file: ${file.absolutePath}")
}
