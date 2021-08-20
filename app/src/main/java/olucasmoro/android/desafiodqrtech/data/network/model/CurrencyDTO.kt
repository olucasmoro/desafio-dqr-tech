package olucasmoro.android.desafiodqrtech.data.network.model

import com.google.gson.annotations.SerializedName
import olucasmoro.android.desafiodqrtech.data.local.model.Currency

data class CurrencyDTO(
    @SerializedName("success") val success: Boolean,
    @SerializedName("terms") val terms: String,
    @SerializedName("privacy") val privacy: String,
    @SerializedName("currencies") val currencies: Map<String, String>
)

fun CurrencyDTO.asDatabaseModel(): List<Currency> =
    currencies.map { Currency(it.key, it.value) }