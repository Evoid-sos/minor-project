package com.example.evoid

import android.os.Parcel
import android.os.Parcelable

data class locationDetails(
    val latitude:String = "",
    val longitude:String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, p1: Int) = with(dest)
    {
        writeString(latitude)
        writeString(longitude)

    }

    companion object CREATOR : Parcelable.Creator<locationDetails> {
        override fun createFromParcel(parcel: Parcel): locationDetails {
            return locationDetails(parcel)
        }
        override fun newArray(size: Int): Array<locationDetails?> {
            return arrayOfNulls(size)
        }
    }
}