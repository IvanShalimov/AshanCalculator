package game.ivan.ashancalculator.service

import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.util.Log

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

import android.R.attr.bitmap

/**
 * Created by ivan on 08.01.2017.
 */

object RotateManager {

    fun checkRotation(filepath: String,bitmap: Bitmap) {
        var bitmap = bitmap
        val ei: ExifInterface?
        try {
            ei = ExifInterface(filepath)
            val orientation = ei.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED)

            when (orientation) {

                ExifInterface.ORIENTATION_ROTATE_90 -> bitmap = rotateImage(bitmap, 90f)

                ExifInterface.ORIENTATION_ROTATE_180 -> bitmap = rotateImage(bitmap, 180f)

                ExifInterface.ORIENTATION_ROTATE_270 -> bitmap = rotateImage(bitmap, 270f)

                else -> {
                }
            }

            val file = File(filepath)
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height,
                matrix, true)
    }
}
