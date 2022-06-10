package dev.rodosteam.questtime.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toBitmap
import java.io.File

class InternalStorage(private val filesDir: File) {
    companion object {
        // BMP compress quality
        private const val COMPRESS_QUALITY = 95
    }

    /**
     * Saves content to file with given name and directory.
     */
    fun save(dirPath: String, filename: String, content: String) {
        val file = fileWithDirectoryAssurance(filesDir.resolve(dirPath).toString(), filename)
        file.bufferedWriter()
            .use { w -> w.write(content) }
    }

    /**
     * Reads content from given file path, convert it to string.
     */
    fun read(path: String): String =
        filesDir.resolve(path).bufferedReader().use { r ->
            r.readText()
        }

    /**
     * Copy from raw resources to Internal storage in pathTo directory.
     * @param resourcesToCopy map with resource id and future filename, that will be used
     * in internal storage
     */
    fun copyFromRawResources(
        resources: Resources,
        resourcesToCopy: Map<Int, String>,
        pathTo: String
    ) {
        for (entry in resourcesToCopy) {
            resources.openRawResource(entry.key).bufferedReader().use { reader ->
                save(pathTo, entry.value, reader.readText())
            }
        }
    }

    /**
     * Copy from image resources to Internal storage in pathTo directory.
     * @param resourcesToCopy map with resource id and future filename, that will be used
     * in internal storage
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    fun copyFromImageResources(
        resources: Resources,
        resourcesToCopy: Map<Int, String>,
        pathTo: String
    ) {
        val dirStr = filesDir.resolve(pathTo).toString()
        val dir = fileWithDirectoryAssurance(dirStr, dirStr)
        for (entry in resourcesToCopy) {
            dir.resolve(entry.value).outputStream().use { stream ->
                resources.getDrawable(entry.key, null).toBitmap()
                    .compress(Bitmap.CompressFormat.PNG, COMPRESS_QUALITY, stream)
            }
        }
    }

    /**
     * Get bmp file from internal storage with given filePath.
     * @throws NoSuchFileException if not exists.
     */
    fun getBitmap(filePath: String): Bitmap {
        val fileAbsPath = filesDir.resolve(filePath).toString()
        if (!exists(fileAbsPath)) {
            throw NoSuchFileException(File(fileAbsPath))
        }

        return BitmapFactory.decodeFile(fileAbsPath)
    }

    /**
     * Check whether file exists in internal storage.
     */
    fun exists(path: String): Boolean {
        return filesDir.resolve(path).exists()
    }

    /** Creates parent directories if necessary. Then returns file  */
    private fun fileWithDirectoryAssurance(directory: String, filename: String): File {
        val dir = File(directory)
        if (!dir.exists()) dir.mkdirs()
        return dir.resolve(filename)
    }
}