package com.example.evoid

import android.os.Parcel
import android.os.Parcelable

data class pictures (
    val back:String = "",
    val front:String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, p1: Int) = with(dest)
    {
        writeString(back)
        writeString(front)

    }

    companion object CREATOR : Parcelable.Creator<pictures> {
        override fun createFromParcel(parcel: Parcel): pictures {
            return pictures(parcel)
        }
        override fun newArray(size: Int): Array<pictures?> {
            return arrayOfNulls(size)
        }
    }
}