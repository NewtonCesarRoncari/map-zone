package com.newton.zone.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.newton.zone.extension.returnUUID
import java.io.Serializable
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Business::class,
        parentColumns = ["id"],
        childColumns = ["business_id"],
        onUpdate = CASCADE,
        onDelete = CASCADE
    )],
    indices = [Index("business_id")]
)
class Visit(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val segment: String = "",
    val date: Date = Date(),
    val hour: Date = Date(),
    val observation: String = "",
    @ColumnInfo(name = "business_id")
    val businessId: String = ""
) : Serializable {
    constructor(business: Business, id: String = "", day: Date, hour: Date, observation: String) : this(
        id.returnUUID(),
        business.name,
        business.address,
        business.segment,
        day,
        hour,
        observation,
        business.id
    )
}
