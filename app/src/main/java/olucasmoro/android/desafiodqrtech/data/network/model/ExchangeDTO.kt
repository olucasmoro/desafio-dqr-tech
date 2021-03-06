package olucasmoro.android.desafiodqrtech.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExchangeDTO(
    @SerializedName("success")
    @Expose
    val success: Boolean? = null,

    @SerializedName("terms")
    @Expose
    val terms: String? = null,

    @SerializedName("privacy")
    @Expose
    val privacy: String? = null,

    @SerializedName("source")
    @Expose
    val source: String? = null,

    @SerializedName("quotes")
    @Expose
    val quotes: Map<String, String>? = null
)