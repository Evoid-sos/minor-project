package com.example.evoid

import android.os.Parcel
import android.os.Parcelable

data class ContactsDetails(
    val contactName:String = "",
    val contactPhoneNumber:Long = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readLong(),
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, p1: Int) = with(dest)
    {
        writeString(contactName)
        writeLong(contactPhoneNumber)

    }

    companion object CREATOR : Parcelable.Creator<ContactsDetails> {
        override fun createFromParcel(parcel: Parcel): ContactsDetails {
            return ContactsDetails(parcel)
        }
        override fun newArray(size: Int): Array<ContactsDetails?> {
            return arrayOfNulls(size)
        }
    }
}