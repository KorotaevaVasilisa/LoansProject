package ru.vsls.korotaevahomework.form

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import ru.vsls.korotaevahomework.App
import ru.vsls.korotaevahomework.R
import javax.inject.Inject

class FormFragment : Fragment() {
    private var period: Int? = null
    private var percent: Double? = null
    private var amount: Int? = null
    private var nameText: TextView? = null
    private var surnameText: TextView? = null
    private var numberText: TextView? = null
    private var button: Button? = null

    @Inject
    lateinit var viewModelFactory: FormViewModel.Factory

    private lateinit var viewModel: FormViewModel

    private val component by lazy {
        (requireActivity().application as App).component
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
        val layoutName = view.findViewById<TextInputLayout>(R.id.fir_name)
        val layoutSurname = view.findViewById<TextInputLayout>(R.id.fir_surname)
        val layoutNumber = view.findViewById<TextInputLayout>(R.id.fir_number)

        nameText?.addTextChangedListener {
            validateRussianText(nameText?.text?.toString(), layoutName)
        }

        surnameText?.addTextChangedListener {
            validateRussianText(surnameText?.text?.toString(), layoutSurname)
        }

        numberText?.addTextChangedListener {
            validateNumberText(numberText?.text?.toString(), layoutNumber)
        }

        setupToolbar(view)

        val button = view.findViewById<Button>(R.id.confirm_button)
        button.setOnClickListener {
            val name = nameText?.text?.toString()
            val surname = surnameText?.text?.toString()
            val number = numberText?.text?.toString()

            if (name == null || surname == null || number == null) return@setOnClickListener

            val isNameValid = validateRussianText(name, layoutName)
            val isSurnameValid = validateRussianText(surname, layoutSurname)
            val isNumberValid = validateNumberText(number, layoutNumber)

            if (isNameValid && isSurnameValid && isNumberValid) {
                viewModel.registerLoan(name, surname, number)
            }
        }
    }

    fun validateNumberText(text: String?, textInputLayout: TextInputLayout): Boolean {
        return when {
            text.isNullOrEmpty() -> {
                textInputLayout.error = getString(R.string.empty_field)
                false
            }

            text.length != 11 -> {
                textInputLayout.error = getString(R.string.non_valid_number)
                false
            }

            else -> {
                textInputLayout.error = null
                true
            }
        }
    }

    fun validateRussianText(text: String?, textInputLayout: TextInputLayout): Boolean {
        return when {
            text.isNullOrEmpty() -> {
                textInputLayout.error = getString(R.string.empty_field)
                false
            }

            !text.matches(Regex("^[а-яА-ЯёЁ\\s-]+$")) -> {
                textInputLayout.error = getString(R.string.non_valid_text)
                false
            }

            else -> {
                textInputLayout.error = null
                true
            }
        }
    }

    private fun setupToolbar(view: View) {
        val toolbar =
            view.findViewById<MaterialToolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
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