package olucasmoro.android.desafiodqrtech.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.data.local.model.Exchange

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrency(currencyList: List<Currency>)

    @Query("SELECT * FROM currency WHERE (value LIKE '%' || :field || '%') OR (name LIKE '%' || :field || '%') ORDER BY name ASC")
    suspend fun searchCurrenciesOrderByName(field: String): List<Currency>

    @Query("SELECT * FROM currency ORDER BY value ASC")
    suspend fun getCurrenciesOrderByCode(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllExchange(exchangeList: List<Exchange>)

}