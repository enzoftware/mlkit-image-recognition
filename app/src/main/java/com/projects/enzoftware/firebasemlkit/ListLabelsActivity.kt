package com.projects.enzoftware.firebasemlkit

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import com.google.android.gms.vision.label.ImageLabel
import kotlinx.android.synthetic.main.activity_list_labels.*

class ListLabelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_labels)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Detected items"
        val linearVertical = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearVertical
        recyclerView.adapter = ImageLabelAdapter(intent.getParcelableArrayListExtra<ImageLabel>(KEY_LABELS),this)
    }

    companion object {

        private const val KEY_LABELS = "LABELS"

        fun callingIntent(context: Context, labels : ArrayList<ImageLabel>) : Intent{
            val intent = Intent(context, ListLabelsActivity::class.java)
            intent.putParcelableArrayListExtra(KEY_LABELS, ArrayList(labels))
            return intent
        }
    }
}
