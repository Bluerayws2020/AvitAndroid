package com.bluerayws.avit.ui.login

import android.R
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.Helper.HelperUtils.hideKeyBoard
import com.bluerayws.avit.Repo.NetworkRepository
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CustomerViewModel
import com.bluerayws.avit.databinding.ActivityMainBinding
import com.bluerayws.avit.databinding.ActivitySignUpBinding
import com.bluerayws.avit.dataclass.CustomerRegister
import com.bluerayws.avit.dataclass.Register
import com.bluerayws.avit.dataclass.UserData
import com.bluerayws.avit.ui.activities.HomeActivity
import com.bumptech.glide.Glide
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response.error
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException



const val LOGIN_OTP = 1

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var pass: EditText
//    private val socialMediaLogin by inject<SocialMediaLogin>()


    //    lateinit var mainBinding: ActivityMainBinding
    lateinit var callbackManager: CallbackManager

//    val fbImage = imageView
//    val fbLogin = imageView

//    final View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
//    Bitmap bmp;//<= retrieve/create your bitmap here


    private val userVM by viewModels<CustomerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(binding.root)



        name = binding.name
        email = binding.email
        phone = binding.phoneNumber
        pass = binding.password

//        binding.facebookSignInButton.setOnClickListener(this)


//        mainBinding = ActivitySignUpBinding.
//        FacebookSdk.sdkInitialize(applicationContext)
        callbackManager = CallbackManager.Factory.create()

//        binding.imageFacebook.setReadPermissions(listOf("email","public_profile","user_gender",
//        "user_birthday","user_friends"))

//        val btn_login_facebook = binding.imageFacebook

//        btn_login_facebook.setReadPermissions("email")


//        btn_login_facebook.registerCallback(callbackManager, object :FacebookCallback<LoginResult>{
//            override fun onSuccess(result: LoginResult?) {
//                val graphRequest = GraphRequest.newMeRequest(result?.accessToken){
//                    `object`,response ->
//                    getFacebookData(`object`)
////                    Log.i("TAG", "onSuccess:" + object)
//
//                }
//
//                val parameters = Bundle()
//                parameters.putString("fields", "email")
//                graphRequest.parameters = parameters
//                graphRequest.executeAsync()
//
//                Log.i("TAG", "onSuccess:" + parameters.toString())
//
//
//            }
//
//            override fun onCancel() {
//                Log.i("TAG", "onCancel:")
//
//            }
//
//            override fun onError(error: FacebookException?) {
//                Log.i("TAG", "onError: ")
//            }
//
//        })


        userVM.getSignUpResponse().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    Toast.makeText(this, result.data.msg, Toast.LENGTH_LONG).show()
                    if (result.data.status) {

                        saveUserData(result.data.user_data)

                        Log.i("TAG", "ya rab: " + result.data.user_data.access_token)
                        Toast.makeText(applicationContext, result.data.user_data.access_token, Toast.LENGTH_SHORT).show()
                        val intentHome = Intent(this, HomeActivity::class.java)
                        startActivity(intentHome)
                        finishAffinity()

                    } else {
//                        binding.progressSignUp.hide()
//                        binding.signUpBtn.show()
                        Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).show()
                    }
                }
                is NetworkResults.Error -> {
//                    binding.progressSignUp.hide()
//                    binding.signUpBtn.show()
                    Log.i("TAG", "Network  ->   Error: ")
//                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }


        binding.textInputphone.setEndIconOnClickListener {

        }
//Aya
// Favorite Page with two Api  one for favorite item other for Related item
//        if token was empty ==> Redirect on  home page
//        else send token on header  with lang as param

//      FaceBook Login
//        Profile ==> view user profile main profile ===>aya
        //        Profile ==> view user profile  details profile  ===>aya
//        wep view paypal
//        my order


//M.jaber
//        faovirete btn ==>  m.j
//        pay fort + cash on delivery ==> m.j
//        About us
//        logout
//        Help Center
//        Agreement


//        binding.imageFacebook.setOnClickListener {
//            Snackbar.make(binding.imageFacebook, "facebook", Snackbar.LENGTH_LONG).show()
//
//            printHashKey()
//// tMbNhXF3AZAAfSqGT3dnifSHS5Y=
//        }

        binding.imageGoogle.setOnClickListener {
            Snackbar.make(binding.imageGoogle, "google", Snackbar.LENGTH_LONG).show()
        }

        binding.signUp.setOnClickListener {

            HelperUtils.hideKeyBoard(this)
//            if (isInputValid()) {
//                binding.progressSignUp.show()
//                binding.signUpBtn.hide()
            userVM.userSignUp(
                "2",
                "ar",
                binding.password.text.toString(),
                binding.password.text.toString(),
                binding.name.text.toString(),
                binding.phoneNumber.text.toString(),
                binding.name.text.toString(),
                "1-1---1-1-1---1--1~1", "1-1---1-1-1---1--1~1", binding.name.text.toString(),
                binding.email.text.toString()

            )
        }
//        language: String,
//        password: String,
//        name_en: String,
//        phone: String,
//        name_ar: String,
//        country_id: String,
//        region_id: String,
//        username: String,
//        email: String,


//            when {
//                TextUtils.isEmpty(name.text) ->
//                    name.error = getString(R.string.fill_yor_name)
//                TextUtils.isEmpty(email.text) ->
//                    email.error = getString(R.string.fill_yor_email)
//                TextUtils.isEmpty(phone.text) ->
//                    phone.error = getString(R.string.fill_yor_phone)
//                TextUtils.isEmpty(pass.text) ->
//                    pass.error = getString(R.string.fill_yor_pass)
//                else -> {
//
//
//
//
//                    Snackbar.make(binding.imageGoogle, "GO", Snackbar.LENGTH_LONG).show()
//
//
//                }
//            }

    }

    private fun getFacebookData(obj: JSONObject) {

//        val profilePic = "https://graph.facebook.com/${obj?.getString("id")}/picture?width=200%height=200"

//        Glide.with(this)
//            .load(profilePic)
//            .into(binding.imageFacebook)

//        val name = obj?.getString("name")
        val birthday = obj?.getString("birthday")
        val gender = obj?.getString("gender")
        val total_count = obj?.getJSONObject("friends")
            .getJSONObject("summary")?.getString("total_count")


        val email = obj?.getString("email")


        Log.d("emailss", email)

//        binding.name.text = "NAME: ${name}"

//        binding.email.text = "EMAIL: ${email}"

    }

//    private fun hideSocialProgress() {
//        binding.progressFacebookLogin.hide()
//        binding.progressGoogleLogin.hide()
//        binding.facebookSignInButton.show()
//        binding.googleSignInButton.show()
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun saveUserData(user: UserData) {
        val sharedPreferences = getSharedPreferences(HelperUtils.SHARED_PREF, MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putString("access_token", user.access_token)
            Log.i("TAG", "saveUserData: " + user.username)
        }.apply()
    }

    fun printHashKey() {
        try {
            val info: PackageInfo =
                this.packageManager.getPackageInfo(this.packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.d("TAG", "printHashKey:  Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.d("TAG", "printHashKey: ", e)
        } catch (e: Exception) {
            Log.d("TAG", "printHashKey: ", e)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.signUp.id -> {
                val intentSignUp = Intent(this, SignUpActivity::class.java)
                startActivity(intentSignUp)
            }

            binding.facebookSignInButton.id -> {
//                socialMediaLogin.signInWithFacebook()
            }
        }
    }

}


