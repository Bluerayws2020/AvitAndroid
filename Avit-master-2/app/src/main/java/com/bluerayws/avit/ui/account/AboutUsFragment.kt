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
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CustomerViewModel
import com.bluerayws.avit.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {
    private var binding: FragmentAboutUsBinding? = null
    private var navController: NavController? = null
    private val userVM by viewModels<CustomerViewModel>()
    private val language = "ar"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding?.tvAboutUsTitle?.paintFlags =
            binding?.tvAboutUsTitle?.paintFlags?.or(Paint.UNDERLINE_TEXT_FLAG)!!

        aboutUsApi()

        binding?.ivBack?.setOnClickListener {
            navController?.popBackStack()
        }

        userVM.getAboutUs(language)


    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    private fun aboutUsApi() {
        userVM.getAboutUsResponse().observe(viewLifecycleOwner){result ->

            when (result) {
                is NetworkResults.Success -> {
                    Toast.makeText(requireContext(), result.data.msg.toString(), Toast.LENGTH_SHORT).show()

                       if(HelperUtils.getLang(context) == "ar") {
                           binding?.textView4?.text = result.data.aboutUs.about_us_ar.toString()
                       }
                    else {
                        binding?.textView4?.text  = result.data.aboutUs.about_us_en.toString()
                    }

                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }

}