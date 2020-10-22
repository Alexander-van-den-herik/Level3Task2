package nl.herika.app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    var title: String,
    var url: String
) : Parcelable
