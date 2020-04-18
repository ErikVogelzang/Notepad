package com.example.notepad.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "noteTable")
data class Note (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "lastUpdated")
    var lastUpdated: Date,
    @ColumnInfo(name = "text")
    var text: String
) : Parcelable