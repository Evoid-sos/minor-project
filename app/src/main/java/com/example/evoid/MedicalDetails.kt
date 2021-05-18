package com.example.evoid

import android.os.Parcel
import android.os.Parcelable

data class MedicalDetails(
    val name:String = "",
    val address:String = "",
    val bloodType:String = "",
    val allergies:String = "",
    val medications:String = "",
    val medicalNotes:String = "",
    val organDonor:String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, p1: Int) = with(dest)
    {
        writeString(name)
        writeString(address)
        writeString(bloodType)
        writeString(allergies)
        writeString(medications)
        writeString(medicalNotes)
        writeString(organDonor)
    }

    companion object CREATOR : Parcelable.Creator<MedicalDetails> {
        override fun createFromParcel(parcel: Parcel): MedicalDetails {
            return MedicalDetails(parcel)
        }
        override fun newArray(size: Int): Array<MedicalDetails?> {
            return arrayOfNulls(size)
        }
    }
}