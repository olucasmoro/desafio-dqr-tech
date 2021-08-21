package olucasmoro.android.desafiodqrtech.presenter.util

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class DefaultValidator(
    private val textInputField: TextInputLayout
) : Validator {

    var field: EditText? = textInputField.editText

    override fun isValid(): Boolean {
        if (!validateRequiredField()) return false
        removeError()
        return true
    }

    private fun validateRequiredField(): Boolean {
        val text: String = field?.text.toString()
        if (text.isEmpty() || text.isBlank()) {
            textInputField.error = ENTER_THE_VALUE
            return false
        }
        return true
    }

    fun removeError() {
        textInputField.error = null
        textInputField.isErrorEnabled = false
    }
}