package com.bluerayws.avit.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bluerayws.avit.dataclass.UserData
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.Gson

const val GOOGLE_SIGN_IN = 101
const val FACEBOOK_LOGIN = 111
const val GOOGLE_LOGIN = 222

class SocialMediaLogin {

    private lateinit var mContext: Activity
    private lateinit var callbackManager: CallbackManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var onSocialMediaLoginListener: OnSocialMediaLoginListener

    fun initializeSocialLogin(mContext: Activity) {
        this.mContext = mContext
        initializeGoogleSignIn(mContext)
        initializeFacebookSignIn()
    }

    private fun initializeGoogleSignIn(mContext: Activity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestId()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(mContext, gso)
    }

    private fun initializeFacebookSignIn() {
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    onSocialMediaLoginListener.onSocialLoginCancel()
                }

                override fun onError(error: FacebookException) {
                    onSocialMediaLoginListener.onSocialLoginFailure(error)
                }

                override fun onSuccess(result: LoginResult) {
                    val request =
                        GraphRequest.newMeRequest(result.accessToken) { _, response ->
                            serializeFacebookJson(response)
                        }
                    val parameters = Bundle()
                    parameters.putString(
                        "fields",
                        "id,name,link,email,birthday,gender,picture.type(large),location"
                    )
                    request.parameters = parameters
                    request.executeAsync()
                }

            })
    }

    fun signOutWithGoogle() {
        mGoogleSignInClient.signOut()
    }

    fun signOutFacebook() {
        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/permissions/",
                null,
                HttpMethod.DELETE,
                {
                    AccessToken.setCurrentAccessToken(null)
                    LoginManager.getInstance().logOut()
                }
            ).executeAsync()
        }
    }

    fun signInWithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        mContext.startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }

    fun signInWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(
            mContext,
            listOf(
                "email", "public_profile", "user_friends",
                "user_birthday", "user_location", "user_gender", "user_link"
            )
        )
    }

    fun facebookCallbackOnActivityResults(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun googleTaskOnActivityResults(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GOOGLE_SIGN_IN && resultCode == Activity.RESULT_OK) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        } else {
            onSocialMediaLoginListener.onSocialLoginCancel()
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val googleAccount = completedTask.getResult(ApiException::class.java)
            val email = googleAccount.email
            val fullName = googleAccount.displayName
            onSocialMediaLoginListener.onSocialLoginSuccess(email, fullName, GOOGLE_LOGIN)
        } catch (e: ApiException) {
            onSocialMediaLoginListener.onSocialLoginFailure(e)
        }
    }

    private fun serializeFacebookJson(response: GraphResponse?) {
        val gson = Gson()
        val facebookUserAccount = gson.fromJson(response?.rawResponse, UserData::class.java)
        val email = facebookUserAccount.email
        val fullName = facebookUserAccount.name_en
        Log.d("email",email)
        onSocialMediaLoginListener.onSocialLoginSuccess(email, fullName, FACEBOOK_LOGIN)
    }

    fun registerLoginListener(socialMediaLoginListener: OnSocialMediaLoginListener) {
        this.onSocialMediaLoginListener = socialMediaLoginListener
    }

    interface OnSocialMediaLoginListener {
        fun onSocialLoginSuccess(userEmail: String?, userFullName: String?, loginType: Int)
        fun onSocialLoginFailure(exception: Exception)
        fun onSocialLoginCancel()
    }
}