package olucasmoro.android.desafiodqrtech.presenter.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import olucasmoro.android.desafiodqrtech.databinding.ConverterFragmentBinding
import olucasmoro.android.desafiodqrtech.presenter.iu.ConvertViewModel
import olucasmoro.android.desafiodqrtech.presenter.util.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConverterFragment : Fragment() {

    private var _binding: ConverterFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ConvertViewModel by sharedViewModel<ConvertViewModel>()

    private var validator: Validator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ConverterFragmentBinding.inflate(inflater, container, false)
        observer()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        setupListeners()
    }

    private fun setupListeners() {

        addDefaultValidation(binding.iptLayout)

        viewModel.currencySource.value?.name?.let { currencyName ->
            binding.buttonSourceCurrency.text = currencyName
        }
        viewModel.currencyDestination.value?.name?.let { currencyName ->
            binding.buttonDestinationCurrency.text = currencyName
        }

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
            setupButtonConverter()
        }
    }

    private fun setupButtonConverter() {
        val isValid = validateAllFields()
        if (isValid) {
            callConverter()
        }
    }

    private fun validateAllFields(): Boolean {
        var isValid = true
        if (validator?.isValid() != true) {
            isValid = false
        }
        return isValid
    }

    private fun converter(source: String, destiny: String, value: String) {
        viewModel.converterValue(source, destiny, value)
            .observe(viewLifecycleOwner, { resource ->
                resource.data?.let {
                    it.quotes?.map { resultApi ->
                        try {
                            val result = resultApi.value.toDouble() * value.toDouble()
                            binding.textResultConvert.text = "R$ ${result.round(2)}"
                        } catch (e: Exception) {
                            showError(ERROR_CONVERT)
                        }
                    }
                }
                resource.error?.let { showError(it) }
            })
    }

    private fun observer() {
        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.rootLayout, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }
    }

    private fun callConverter() {
        if (viewModel.verifyFields()) {
            converter(
                viewModel.currencySource.value?.name.toString(),
                viewModel.currencyDestination.value?.name.toString(),
                binding.editValueConversion.text.toString()
            )
        }
    }

    private fun addDefaultValidation(textInputCampo: TextInputLayout) {
        validator = DefaultValidator(textInputCampo)
        try {
            textInputCampo.editText!!.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        (validator as DefaultValidator).isValid()
                    }
                }
        } catch (e: Exception) {
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