package com.projects.enzoftware.firebasemlkit.utils

import android.graphics.Bitmap
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage

fun doImageRecognition(bitmap: Bitmap){
    val image = FirebaseVisionImage.fromBitmap(bitmap)
    val detector = FirebaseVision.getInstance().visionLabelDetector
    detector.detectInImage(image)
            .addOnSuccessListener {
                it.forEach {

                }
            }
            .addOnFailureListener {

            }
}