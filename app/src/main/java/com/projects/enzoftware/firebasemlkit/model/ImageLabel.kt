package com.projects.enzoftware.firebasemlkit.model

import android.os.Parcel
import android.os.Parcelable

data class ImageLabel(val label : String, val confidence: Float): Parcelable {

    constructor(parcel: Parcel):this(
        parcel.readString(),
        parcel.readFloat()
    )
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.apply {
            writeString(label)
            writeFloat(confidence)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageLabel>{
        override fun newArray(size: Int): Array<ImageLabel?> {
            return arrayOfNulls(size)
        }

        override fun createFromParcel(source: Parcel?): ImageLabel {
            return ImageLabel(source!!)
        }

    }
}