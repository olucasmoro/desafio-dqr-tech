package olucasmoro.android.desafiodqrtech.presenter.iu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.data.local.model.Resource
import olucasmoro.android.desafiodqrtech.data.network.model.ExchangeDTO
import olucasmoro.android.desafiodqrtech.domain.repository.AppRepositoryData
import olucasmoro.android.desafiodqrtech.domain.repository.TitleRefreshError
import olucasmoro.android.desafiodqrtech.presenter.util.SELECT_CURRENCY_DESTINATION
import olucasmoro.android.desafiodqrtech.presenter.util.SELECT_CURRENCY_SOURCE

class ConvertViewModel(
    private val repository: AppRepositoryData
) : ViewModel() {

    private val _snackBar = MutableLiveData<String?>()
    val snackbar: LiveData<String?>
        get() = _snackBar

    private val _spinner = MutableLiveData(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    val listCurrency = repository.getList()

    val search = MutableLiveData<String?>()

    val typeCurrency = MutableLiveData<String>()

    var currencySource = MutableLiveData<Currency>()
    var currencyDestination = MutableLiveData<Currency>()

    fun verifyFields(): Boolean {
        if (currencySource.value?.name.isNullOrEmpty()) {
            _snackBar.value = SELECT_CURRENCY_SOURCE
            return false
        } else if (currencyDestination.value?.name.isNullOrEmpty()) {
            _snackBar.value = SELECT_CURRENCY_DESTINATION
            return false
        }
        return true
    }

    fun converterValue(
        source: String,
        destination: String,
        value: String
    ): LiveData<Resource<ExchangeDTO>> = runBlocking {
        repository.converter(destination, source, value)
    }

    fun getAllCurrencyApi() {
        launchDataLoad {
            repository.getAllCurrencyApi()
        }
    }

    fun getAllCurrencySearch() {
        launchDataLoad {
            if (!search.value.isNullOrBlank()) {
                search.value?.let {
                    repository.searchCurrenciesOrderByName(it)
                }
            }
        }
    }

    fun onSnackbarShown() {
        _snackBar.value = null
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: TitleRefreshError) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }
}