package ru.vsls.korotaevahomework.form.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.App
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.form.presentation.model.FormState
import ru.vsls.utils.ErrorType
import ru.vsls.utils.getErrorMessage
import javax.inject.Inject

class FormFragment : Fragment() {
    private var period: Int? = null
    private var percent: Double? = null
    private var amount: Int? = null
    private var nameText: TextView? = null
    private var surnameText: TextView? = null
    private var numberText: TextView? = null
    private var button: Button? = null
    var layoutName: TextInputLayout? = null
    var layoutSurname: TextInputLayout? = null
    var layoutNumber: TextInputLayout? = null

    @Inject
    lateinit var viewModelFactory: FormViewModel.Factory

    private lateinit var viewModel: FormViewModel

    private val component by lazy {
        (requireActivity().application as App).component.formComponent()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            amount = it.getInt(ARG_AMOUNT)
            period = it.getInt(ARG_PERIOD)
            percent = it.getDouble(ARG_PERCENT)
        }

        viewModel = viewModelFactory.create(amount, percent, period)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameText = view.findViewById(R.id.ed_name)
        surnameText = view.findViewById(R.id.ed_surname)
        numberText = view.findViewById(R.id.ed_number)
        button = view.findViewById(R.id.confirm_button)
        layoutName = view.findViewById<TextInputLayout>(R.id.fir_name)
        layoutSurname = view.findViewById<TextInputLayout>(R.id.fir_surname)
        layoutNumber = view.findViewById<TextInputLayout>(R.id.fir_number)

        nameText?.addTextChangedListener {
            viewModel.onNameChanged(nameText?.text?.toString() ?: "")
        }

        surnameText?.addTextChangedListener {
            viewModel.onSurnameChanged(surnameText?.text?.toString() ?: "")
        }

        numberText?.addTextChangedListener {
            viewModel.onPhoneChanged(numberText?.text?.toString() ?: "")
        }

        setupToolbar(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                updating(state)
            }
        }

        val context = requireContext()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errors.collect { type ->
                val message = getErrorMessage(type, context)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }

        button?.setOnClickListener {
            viewModel.registerLoan()
        }
    }

    private fun updating(state: FormState) {
        button?.isEnabled = !state.isLoading
        layoutName?.error = getErrorText(state.nameError)
        layoutSurname?.error = getErrorText(state.surnameError)
        layoutNumber?.error = getErrorText(state.phoneError)
    }

    private fun getErrorText(errorType: ErrorType?): String? {
        if (errorType == ErrorType.EMPTY_FIELD)
            return getErrorMessage(ErrorType.EMPTY_FIELD, requireContext())
        if (errorType == ErrorType.NON_RUSSIAN_SYMBOLS)
            return getErrorMessage(ErrorType.NON_RUSSIAN_SYMBOLS, requireContext())
        if (errorType == ErrorType.NON_VALID_PHONE)
            return getErrorMessage(ErrorType.NON_VALID_PHONE, requireContext())
        return null
    }

    private fun setupToolbar(view: View) {
        val toolbar =
            view.findViewById<MaterialToolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            viewModel.popBackStack()
        }
    }

    companion object {
        const val ARG_PERIOD = "period"
        const val ARG_PERCENT = "percent"
        const val ARG_AMOUNT = "amount"

        @JvmStatic
        fun newInstance(amount: Int, percent: Double, period: Int) =
            FormFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_AMOUNT, amount)
                    putDouble(ARG_PERCENT, percent)
                    putInt(ARG_PERIOD, period)
                }
            }
    }
}