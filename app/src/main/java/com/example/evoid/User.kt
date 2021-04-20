package com.example.evoid

import android.os.Parcel
import android.os.Parcelable

data class User (
        val id:String = "",
        val firstName:String = "",
        val lastName:String = "",
        val phoneNumber:Long = 0,
        val emailId:String = "",
        val fcmToken:String = "",
        val image:String = "",
        val lang:String = "en"

): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readLong(),
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!

        ) {
        }

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel, p1: Int) = with(dest)
        {
                writeString(id)
                writeString(firstName)
                writeString(lastName)
                writeLong(phoneNumber)
                writeString(emailId)
                writeString(fcmToken)
                writeString(image)
                writeString(lang)

        }

        companion object CREATOR : Parcelable.Creator<User> {
                override fun createFromParcel(parcel: Parcel): User {
                        return User(parcel)
                }

                override fun newArray(size: Int): Array<User?> {
                        return arrayOfNulls(size)
                }
        }
}

