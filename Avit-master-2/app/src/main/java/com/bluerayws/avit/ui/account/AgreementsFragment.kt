package com.bluerayws.avit.ui.account

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CustomerViewModel
import com.bluerayws.avit.adapters.ExpandAdapter
import com.bluerayws.avit.databinding.FragmentAgreementsBinding

class AgreementsFragment : Fragment() {

    private var binding: FragmentAgreementsBinding? = null
    private var navController: NavController? = null
    private var adapter: ExpandAdapter? = null
    private val userVM by viewModels<CustomerViewModel>()
    private val language = "ar"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgreementsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        getTermsConditionsApi()
        userVM.getTermsConditionsMain(language)


        binding?.tvAgreementsTitle?.paintFlags =
            binding?.tvAgreementsTitle?.paintFlags!! or Paint.UNDERLINE_TEXT_FLAG
        binding?.ivBack?.setOnClickListener {
            navController?.popBackStack()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    private fun getTermsConditionsApi() {
        userVM.getTermsConditionsResponse().observe(viewLifecycleOwner){result ->

            when (result) {
                is NetworkResults.Success -> {
                    Toast.makeText(requireContext(), result.data.msg.toString(), Toast.LENGTH_SHORT).show()

                    val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                    binding?.rvAgreement?.layoutManager = lm
                    adapter = ExpandAdapter(result.data.termsAndConditions, requireContext())
                    binding?.rvAgreement?.adapter = adapter

                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }


}
