package ru.vsls.korotaevahomework.form

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.vsls.korotaevahomework.App
import ru.vsls.korotaevahomework.R
import javax.inject.Inject

class FormFragment : Fragment() {
    private var period: Int? = null
    private var percent: Double? = null
    private var amount: Int? = null

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