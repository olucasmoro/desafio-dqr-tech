package olucasmoro.android.desafiodqrtech.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey()
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: String
)

