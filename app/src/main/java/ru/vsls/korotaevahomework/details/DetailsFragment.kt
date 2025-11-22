package ru.vsls.korotaevahomework.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vsls.korotaevahomework.R

class DetailsFragment : Fragment() {

    private var loanId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            loanId = it.getInt(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    companion object {
        const val ARG_ID = "loanId"

        @JvmStatic
        fun newInstance(paramId: Int) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, paramId)
                }
            }
    }
}