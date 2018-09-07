package com.projects.enzoftware.firebasemlkit.model

import android.os.Parcel
import android.os.Parcelable

class ImageLabel(val label:String, val confidence:Float) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readFloat())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
        parcel.writeFloat(confidence)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageLabel> {
        override fun createFromParcel(parcel: Parcel): ImageLabel {
            return ImageLabel(parcel)
        }

        override fun newArray(size: Int): Array<ImageLabel?> {
            return arrayOfNulls(size)
        }
    }
}