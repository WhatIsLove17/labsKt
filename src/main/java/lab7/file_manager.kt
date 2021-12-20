package lab7

import java.io.File

class FileManager {
    fun readTextFromFile(path: String): String {
        return File(path).readText()
    }

    fun writeTextIntoFile(path: String, text: String) {
        File(path).writeText(text)
    }
}