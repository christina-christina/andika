package com.platzi.conf.network

import android.graphics.Bitmap
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions
import com.google.mlkit.common.model.CustomRemoteModel
import com.google.mlkit.linkfirebase.FirebaseModelSource

class LabelingService {

    val options = FirebaseVisionOnDeviceImageLabelerOptions.Builder()
        .setConfidenceThreshold(0.7F)
        .build()

    val labelDetector = FirebaseVision.getInstance().getOnDeviceImageLabeler(options)


    fun analisisFoto(callback:Callback<String>, image: Bitmap){
        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(image)

        this.labelDetector.processImage(firebaseVisionImage)
            .addOnSuccessListener { labels ->

                var maxLabel = "Tidak terdeteksi"

                if (labels.size > 0 ) {

                    var maxConfidence = labels[0].confidence.toFloat()
                    maxLabel = labels[0].text
                    for (label in labels) {
                        if (label.confidence.toFloat() > maxConfidence) {
                            maxConfidence = label.confidence.toFloat()
                            maxLabel = label.text
                        }
                    }

                    if (maxLabel.contains("Skin") ||  maxLabel.contains("Mouth") || maxLabel.contains("Smile") || maxLabel.contains("Poster")) {
                        maxLabel = "Card"
                    }
                }

                callback.onSuccess(maxLabel)
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }

    }
}