package com.bluerayws.avit.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CustomerViewModel
import com.bluerayws.avit.databinding.ActivitySignInBinding
import com.bluerayws.avit.dataclass.UserData
import com.bluerayws.avit.ui.activities.HomeActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var phoneNumber: EditText
    private lateinit var password: EditText
    private val userVM by viewModels<CustomerViewModel>()
    private val language = "ar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phoneNumber = binding.phoneNumber
        password = binding.password

            binding.forgetPass.setOnClickListener {

                val fragment = ForgetPassFragment()
                fragment.show(supportFragmentManager, "sad")
            }


        userVM.getLoginResponse().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    Toast.makeText(applicationContext, result.data.toString(), Toast.LENGTH_LONG).show()

                    saveUserData(result.data.user_info)

                    val intentHome = Intent(this, HomeActivity::class.java)
                    HomeActivity.ComeFromLogin = true
                    startActivity(intentHome)
                    finishAffinity()
                }
                is NetworkResults.Error -> {

                    Toast.makeText(applicationContext, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }


            }

        }

        binding.signinBtn.setOnClickListener {
            create_login()
        }
    }



    private fun create_login(){
        val testPhNum = binding.phoneNumber.text.toString()
        val testPass = binding.password.text.toString()

        userVM.userLogin(testPhNum, testPass, language)
    }



    private fun saveUserData(user: UserData) {

        val sharedPreferences = getSharedPreferences(HelperUtils.SHARED_PREF, MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putString("access_token", user.access_token)
            Log.i("TAG", "access_token: " + user.access_token)

        }.apply()

    }



}




















//            binding.signinBtn.setOnClickListener {
//                Toast.makeText(applicationContext, "?Hellpo", Toast.LENGTH_SHORT).show()
//                Log.i("TAG", "aya is a great one: ")
//
//            }



//        val test_login = findViewById<Button>(R.id.signin_btn)
//        test_login.setOnClickListener {
//            Log.i("TAG", "lolll: " )
//        }




// enqueue to add object to save user function
//                    val _lang = "ar"
//                    val _email = binding.phoneNumber.text.toString()
//                    val _password = binding.password.text.toString()
//
//                    val lang = _lang.toRequestBody("ar".toMediaTypeOrNull())
//                    val email = RequestBody.create("text/plain;charset=utf-8".toMediaTypeOrNull(), _email)
//                    val passwordd = RequestBody.create("text/plain;charset=utf-8".toMediaTypeOrNull(), _password)
//
//
//                    val user_info = ApiInstanse.retrofitService.getLogin(lang, email, passwordd)

//                    user_info.enqueue(
//                        object : Callback<Login_main>{
//                            override fun onResponse(
//                                call: Call<Login_main>,
//                                response: Response<Login_main>
//                            ) {
//                                val user = response.body()?.Regester?.let {
//                                    Register(
//                                        it.cid, it.name_en, it.name_ar, it.username, it.email, it.phone, it.access_token)
//                                } // how to get the register data
//
//                                if (user != null) {
//                                    saveUserData(user)
//                                    Log.i("TAG", "LOLLL: " + user.access_token)
//                                }
//                                else
//                                {
//                                    Log.i("TAG", "NULLLLLLLL: ")
//                                }
//                            }
//
//                            override fun onFailure(call: Call<Login_main>, t: Throwable) {
//                                TODO("Not yet implemented")
//                            }
//
//
//                        })
