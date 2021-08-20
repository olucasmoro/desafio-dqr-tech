package olucasmoro.android.desafiodqrtech.presenter.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import olucasmoro.android.desafiodqrtech.databinding.MainFragmentBinding
import olucasmoro.android.desafiodqrtech.presenter.util.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConverterFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ConversorViewModel by sharedViewModel<ConversorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonSourceCurrency.text = viewModel.currencySource.value?.name ?: "Selecione"
        binding.buttonDestinationCurrency.text =
            viewModel.currencyDestination.value?.name ?: "Selecione"

        binding.buttonSourceCurrency.setOnClickListener() {
            viewModel.typeCurrency.value = ID_SOURCE
            findNavController().navigate(
                ConverterFragmentDirections.actionConverterFragmentToCurrencyListFragment(
                    ID_SOURCE
                )
            )
        }

        binding.buttonDestinationCurrency.setOnClickListener() {
            viewModel.typeCurrency.value = ID_DESTINATION
            findNavController().navigate(
                ConverterFragmentDirections.actionConverterFragmentToCurrencyListFragment(
                    ID_DESTINATION
                )
            )
        }

        binding.buttonConvert.setOnClickListener() {
            callConverter()
        }
    }

    private fun converter(source: String, destiny: String, value: String) {
        viewModel.converterValue(source, destiny, value)
            .observe(viewLifecycleOwner, { resource ->
                resource.data?.let {
                    it.quotes?.map { result ->
                        binding.textResultConvert.text = "R$ ${result.value}"

                    }
                }
                resource.error?.let { showError(it) }
            })
    }

    private fun callConverter() {
        if (!viewModel.currencySource.value?.name.isNullOrEmpty() &&
            !viewModel.currencyDestination.value?.name.isNullOrEmpty() &&
            binding.editValueConversion.text.isNotEmpty()
        ) {
            converter(
                viewModel.currencySource.value?.name.toString(),
                viewModel.currencyDestination.value?.name.toString(),
                binding.editValueConversion.text.toString()
            )
        }
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