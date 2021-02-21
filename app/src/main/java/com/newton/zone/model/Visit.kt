package com.newton.zone.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.newton.zone.extension.returnUUID
import java.io.Serializable
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Business::class,
        childColumns = ["id"],
        parentColumns = ["business_id"],
        onUpdate = CASCADE,
        onDelete = CASCADE
    )],
    indices = [Index("business_id")]
)
class Visit(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val address: String,
    val segment: Segment,
    val date: Date,
    val hour: Date,
    @ColumnInfo(name = "business_id")
    val businessId: String
) : Serializable {
    constructor(business: Business, id: String = "", day: Date, hour: Date) : this(
        id.returnUUID(),
        business.name,
        business.address,
        business.segment,
        day,
        hour,
        business.id
    )
}
