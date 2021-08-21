package olucasmoro.android.desafiodqrtech.presenter.iu.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import olucasmoro.android.desafiodqrtech.R
import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.databinding.CurrencyListFragmentBinding
import olucasmoro.android.desafiodqrtech.presenter.iu.ConvertViewModel
import olucasmoro.android.desafiodqrtech.presenter.iu.adapter.CurrencyAdapter
import olucasmoro.android.desafiodqrtech.presenter.iu.adapter.CurrencyListListener
import olucasmoro.android.desafiodqrtech.presenter.util.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CurrencyListFragment : Fragment() {

    private var _binding: CurrencyListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ConvertViewModel by sharedViewModel<ConvertViewModel>()

    private val mAdapter: CurrencyAdapter = CurrencyAdapter()
    private lateinit var mListener: CurrencyListListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCurrencyApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.currency_list_fragment, container, false)
        _binding = CurrencyListFragmentBinding.bind(root).apply {
            this.listViewModel = viewModel
        }

        arguments?.run {
            viewModel.typeCurrency.value = getString("type")
        }

        setupAdapter()
        viewModel.getAllCurrencyApi()
        observer()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun observer() {
        viewModel.spinner.observe(viewLifecycleOwner) { value ->
            value.let { show ->
                binding.progressListCurrency.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.rootLayout, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }

        viewModel.listCurrency.observe(viewLifecycleOwner, {
            mAdapter.updateCurrency(it)
            binding.recyclerListCurrency.adapter = mAdapter
        })
    }

    private fun setupAdapter() {
        mListener = object : CurrencyListListener {
            override fun onClick(currency: Currency) {
                clickItem(currency)
            }
        }
        mAdapter.attachListener(mListener)

        binding.inputSearchCurrency.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.getAllCurrencySearch()
            }

        })

    }

    private fun clickItem(partItem: Currency) {
        if (viewModel.typeCurrency.value == ID_SOURCE) {
            viewModel.currencySource.value = partItem
        }
        if (viewModel.typeCurrency.value == ID_DESTINATION) {
            viewModel.currencyDestination.value = partItem
        }

        activity?.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        if (!verifyNetwork()) showError(NO_NETWORK)
    }

    override fun onResume() {
        super.onResume()
        if (!verifyNetwork()) showError(NO_NETWORK)
    }

}