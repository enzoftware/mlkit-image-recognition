package com.projects.enzoftware.firebasemlkit.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.enzoftware.firebasemlkit.R
import com.projects.enzoftware.firebasemlkit.model.ImageLabel
import kotlinx.android.synthetic.main.item_label_image.view.*

class ImageLabelAdapter(private val labels : ArrayList<ImageLabel>, val context: Context) : RecyclerView.Adapter<ImageLabelAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageLabelAdapter.ViewHolder {
        val view = LayoutInflater   .from(parent.context)
                                    .inflate(R.layout.item_label_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = labels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageLabel = labels[position]
        val confidence = imageLabel.confidence * 100.0f
        when(confidence){
            in 0..50    -> holder.view.txtConfidence.setTextColor(ContextCompat.getColor(context,R.color.colorError))
            in 51..69   -> holder.view.txtConfidence.setTextColor(ContextCompat.getColor(context,R.color.colorAlert))
            in 70..100  -> holder.view.txtConfidence.setTextColor(ContextCompat.getColor(context,R.color.colorSuccess))
        }
        holder.view.txtLabel.text = imageLabel.label
        holder.view.txtConfidence.text = "Confidence $confidence %"
    }

    class ViewHolder(val view:View) : RecyclerView.ViewHolder(view)
}
