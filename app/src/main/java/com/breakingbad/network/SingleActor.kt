package com.breakingbad.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SingleActor(
        val char_id: Int,
        val name: String?,
        val birthday: String?,
        val occupation: List<String>,
        val img: String?,
        val status: String?,
        val nickname: String?,
        val appearance: List<Int>? = null,
        val portrayed: String?,
        val category: String?,
        val better_call_saul_appearance: List<String>
): Parcelable
