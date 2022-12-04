package com.bluerayws.avit.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bluerayws.avit.Api.ApiClient
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.SplashActivity
import com.bluerayws.avit.ViewModel.CustomerViewModel
import com.bluerayws.avit.databinding.FragmentAccountBinding
import com.bluerayws.avit.dataclass.Customer_Profile
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {

    private var binding: FragmentAccountBinding? = null
    private var navController: NavController? = null
    private val userVM by viewModels<CustomerViewModel>()
    private val language = "ar"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val sharedPreferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
        val token = sharedPreferences?.getString("access_token", "defaultname")
        Log.d("token home: 2", "onViewCreated: $token")

        customerInfoApi()
        userVM.userInfo(language, "Bearer $token")

        binding?.btnPersonalInfo?.setOnClickListener {
            navController?.navigate(R.id.personalInfoFragment)

        }
        binding?.btnMyOrder?.setOnClickListener {
            navController?.navigate(R.id.myOrderFragment)
        }
        binding?.btnHelpCenter?.setOnClickListener {
            navController?.navigate(R.id.helpCenterFragment)
        }
        binding?.btnAboutUs?.setOnClickListener {
            navController?.navigate(R.id.aboutUsFragment)
        }

        binding?.btnAgreements?.setOnClickListener {
            navController?.navigate(R.id.agreementsFragment)
        }

        binding?.btnLogout?.setOnClickListener {
            logout()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    private fun customerInfoApi(){
        userVM.getUserInfoResponse().observe(viewLifecycleOwner){result ->

            when (result) {
                is NetworkResults.Success -> {
                    Toast.makeText(requireContext(), result.data.success.toString(), Toast.LENGTH_SHORT).show()
                    binding?.tvUserName?.text = result.data.data.username



                    Glide.with(requireContext())
                        .load(result.data.data.profile_photo)
                        .placeholder(R.drawable.ic_profile_user_account)
                        .error(R.drawable.ic_profile_user_account)
                        .into(binding?.circleImageView!!)


                }
                is NetworkResults.Error -> {

                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }



    private fun logout() {

        val preferences =
            context?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)
        preferences?.edit()?.remove("access_token")?.apply()
        preferences?.edit()?.clear()?.apply()

        startActivity(Intent(requireActivity(), SplashActivity::class.java))


    }
}