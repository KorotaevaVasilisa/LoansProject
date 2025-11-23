package ru.vsls.korotaevahomework.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vsls.korotaevahomework.R

class ResultFragment : Fragment() {
    private var paramSuccess: Boolean? = null
    private var paramAmount: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramSuccess = it.getBoolean(ARG_SUCCESS)
            paramAmount = it.getInt(ARG_AMOUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    companion object {
        const val ARG_SUCCESS = "success"
        const val ARG_AMOUNT = "amount"

        @JvmStatic
        fun newInstance(success: Boolean, amount: Int) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_SUCCESS, success)
                    putInt(ARG_AMOUNT, amount)
                }
            }
    }
}