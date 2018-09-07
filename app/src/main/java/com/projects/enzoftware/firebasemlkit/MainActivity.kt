package com.projects.enzoftware.firebasemlkit

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.projects.enzoftware.firebasemlkit.model.ImageLabel
import com.projects.enzoftware.firebasemlkit.utils.doImageRecognition
import io.fotoapparat.Fotoapparat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PermissionListener {

    private lateinit var fotoapparat: Fotoapparat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Dexter  .withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(this).check()

        fabTakePicture.setOnClickListener {
            takePicture()
        }

    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun takePicture() {
        val photo = fotoapparat.takePicture()
        photo   .toBitmap()
                .whenAvailable {
                    val labels_list = doImageRecognition(it!!.bitmap, this)

                }
    }

    private fun navigateToListImageResults(labels: List<ImageLabel>) {
        val intent = ListLabelsActivity.callingIntent(this, ArrayList(labels))
        startActivity(intent)
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        fotoapparat = Fotoapparat(this,cameraView)
        fotoapparat.start()
    }

    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {

    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this,getString(R.string.denied_permission), Toast.LENGTH_SHORT).show()
    }
}
