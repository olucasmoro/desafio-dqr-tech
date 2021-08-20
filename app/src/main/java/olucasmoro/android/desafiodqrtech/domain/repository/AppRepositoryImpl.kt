package olucasmoro.android.desafiodqrtech.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.data.local.model.Resource
import olucasmoro.android.desafiodqrtech.data.local.source.AppLocalData
import olucasmoro.android.desafiodqrtech.data.network.model.CurrencyDTO
import olucasmoro.android.desafiodqrtech.data.network.model.ExchangeDTO
import olucasmoro.android.desafiodqrtech.data.network.model.asDatabaseModel
import olucasmoro.android.desafiodqrtech.data.network.source.AppRemoteData
import olucasmoro.android.desafiodqrtech.presenter.util.ERROR

class AppRepositoryImpl(
    private val remoteData: AppRemoteData,
    private val localData: AppLocalData,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppRepositoryData {

    private val mCurrencyList = MutableLiveData<List<Currency>>()
    private val currencyList: LiveData<List<Currency>>
        get() = mCurrencyList

    override fun getList(): LiveData<List<Currency>> =
        currencyList

    override suspend fun getAllCurrencyApi() {
        try {
            val result = remoteData.getAllCurrencyApi()
            insertAllCurrencyLocal(result)

        } catch (cause: Throwable) {
            throw TitleRefreshError(ERROR, cause)
        }
    }

    private suspend fun insertAllCurrencyLocal(currencyDTO: CurrencyDTO) {
        val items = currencyDTO.asDatabaseModel()
        mCurrencyList.postValue(items)
        withContext(ioDispatcher) {
            coroutineScope {
                launch { localData.insertAllCurrency(items) }
            }
        }
    }

    override suspend fun getCurrenciesOrderByCode(): List<Currency> {
        val items = localData.getCurrenciesOrderByCode()
        mCurrencyList.postValue(items)
        return items
    }

    override suspend fun searchCurrenciesOrderByName(field: String): List<Currency> {
        val items = localData.searchCurrenciesOrderByName(field)
        mCurrencyList.postValue(items)
        return items
    }

    override suspend fun converter(
        currency: String,
        source: String,
        format: String
    ): LiveData<Resource<ExchangeDTO>> {
        val liveData = MutableLiveData<Resource<ExchangeDTO>>()

        remoteData.getConverter(
            currency,
            source,
            format,
            success = { converter ->
                converter?.let {
                    liveData.postValue(Resource(it))
                }
            }, error = { err -> liveData.postValue(Resource(data = null, error = err)) }
        );

        return liveData
    }

}

class TitleRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)