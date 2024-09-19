import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

fun main() {
    println("Launching interactive bash session...")
    
    val processBuilder = ProcessBuilder("/bin/bash")
    processBuilder.directory(File(System.getProperty("user.home")))
    processBuilder.inheritIO()
    
    val process = processBuilder.start()
    process.waitFor()
    
    println("Bash session ended.")
}
