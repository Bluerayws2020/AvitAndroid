package com.bluerayws.avit.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.FragmentForgetPassBinding


class ForgetPassFragment : DialogFragment() {


    private var binding: FragmentForgetPassBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgetPassBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window!!.setBackgroundDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.shape_dialog_raduis
            )
        )

    }

}
