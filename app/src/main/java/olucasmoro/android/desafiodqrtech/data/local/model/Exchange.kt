package olucasmoro.android.desafiodqrtech.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "exchange")
data class Exchange(
    @PrimaryKey val id: String,

    @ColumnInfo(name = "source_currency_id", index = true)
    val sourceCurrencyId: String,

    @ColumnInfo(name = "destination_currency_id", index = true)
    val targetCurrencyId: String,

    @ColumnInfo(name = "exchange_rate")
    val exchangeRate: Double
)