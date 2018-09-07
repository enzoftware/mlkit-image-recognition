package com.projects.enzoftware.firebasemlkit.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.projects.enzoftware.firebasemlkit.R
import com.projects.enzoftware.firebasemlkit.model.ImageLabel

fun doImageRecognition(bitmap: Bitmap, context : Context): List<ImageLabel>{
    val image = FirebaseVisionImage.fromBitmap(bitmap)
    val detector = FirebaseVision.getInstance().visionLabelDetector
    lateinit var labels : List<ImageLabel>
    detector.detectInImage(image)
            .addOnSuccessListener { it ->
                labels = it.map { ImageLabel(it.label, it.confidence) }.toList()
            }
            .addOnFailureListener {
                Toast.makeText(context, context.getString(R.string.operation_dont_success), Toast.LENGTH_SHORT).show()
                labels = emptyList()
            }
    return labels
}