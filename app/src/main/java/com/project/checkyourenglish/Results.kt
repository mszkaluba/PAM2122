package com.project.checkyourenglish

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Results")
data class Results(
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "good_answers")
    var goodAnswers: String?,
    @ColumnInfo(name = "wrong_answers")
    var wrongAnswers: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}