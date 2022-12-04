package com.bluerayws.avit.ViewModel

import androidx.lifecycle.MutableLiveData
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bluerayws.avit.dataclass.*
import kotlinx.coroutines.launch
import retrofit2.Call
import java.io.File

class CustomerViewModel(application: Application): AndroidViewModel(application) {

    //    private val language = HelperUtils.getLang(application.applicationContext)
//    private val deviceId = HelperUtils.getAndroidID(application.applicationContext)
    private val repo = CMainRepo

    private val registerMessageLiveData = MutableLiveData<NetworkResults<CustomerRegister>>()
    private val loginMessageLiveData = MutableLiveData<NetworkResults<CustomerLogin>>()
    private val userInfoMessageLiveData = MutableLiveData<NetworkResults<CustomerInfo>>()
    private val updateUserInfoMessageLiveData = MutableLiveData<NetworkResults<UpdateCustomerInfo>>()
    private val resetPasswordLiveData = MutableLiveData<NetworkResults<ResetPassword>>()
    private val getLocationsCustomerLiveData = MutableLiveData<NetworkResults<LocationsCustomers>>()
    private val getAboutUsLiveData= MutableLiveData<NetworkResults<AboutUsMain>>()
    private val getTermsConditionsLiveData= MutableLiveData<NetworkResults<TermsConditionsMain>>()
    private val getPopularQuestionsLiveData= MutableLiveData<NetworkResults<PopularQuestionsMain>>()
    private val getDeliveryLiveData= MutableLiveData<NetworkResults<DeliveryMain>>()
    private val getPaymentMethodsLiveData= MutableLiveData<NetworkResults<PaymentMethodsMain>>()


    fun userSignUp(
        provider_status: String,
        language: String,
        password: String,
        cpassword: String,
        name_en: String,
        phone: String,
        name_ar: String,
        country_id: String,
        region_id: String,
        username: String,
        email: String,
        ) {

        viewModelScope.launch {
            registerMessageLiveData.value = repo.userSignUp(provider_status,
            language, name_ar, name_en, username, email, phone, password,cpassword,  country_id, region_id

            )
        }

    }


     fun userLogin(
         email: String,
         password: String,
         language: String

    ) {

        viewModelScope.launch {
             loginMessageLiveData.value =  repo.userLogIn(email, password, language)
        }
    }



    fun userInfo(
        language: String,
        token: String

    ) {
        viewModelScope.launch {
            userInfoMessageLiveData.value =  repo.userInfo(language, "Bearer $token")

        }
    }



    fun updateUserData(
        language: String,
        name_ar: String,
        name_en: String,
        username: String,
        email: String,
        phone: String,
        country_id: String,
        region_id: String,
        address: String?,
        token: String,
        profileImage: File? = null,

    ) {
        viewModelScope.launch {
            updateUserInfoMessageLiveData.value = repo.updateUserInfo(language,name_ar,name_en,
                username, email, phone, country_id, region_id, address!!, "Bearer $token", profileImage)

        }
    }


    fun resetUserPassword(
        language: String,
        currentPassword: String,
        newPassword: String,
        confirmationPassword: String,
        token: String

    ) {
        viewModelScope.launch {
            resetPasswordLiveData.value =  repo.getResetPassword(language, currentPassword, newPassword, confirmationPassword,"Bearer $token")

        }
    }


    fun getLocationsCustomers(
        language: String,
        token: String

    ) {
        viewModelScope.launch {
            getLocationsCustomerLiveData.value =  repo.getLocationsCustomers(language,"Bearer $token")

        }
    }


    fun getAboutUs(
        language: String
    ) {
        viewModelScope.launch {
            getAboutUsLiveData.value =  repo.getAboutUs(language)

        }
    }



    fun getTermsConditionsMain(
        language: String
    ) {
        viewModelScope.launch {
            getTermsConditionsLiveData.value =  repo.getTermsConditionsMain(language)

        }
    }



    fun getPopularQuestions(
        language: String
    ) {
        viewModelScope.launch {
            getPopularQuestionsLiveData.value =  repo.getPopularQuestions(language)

        }
    }



    fun getDelivery(
        language: String
    ) {
        viewModelScope.launch {
            getDeliveryLiveData.value =  repo.getDelivery(language)

        }
    }



    fun getPaymentMethods(
        language: String
    ) {
        viewModelScope.launch {
            getPaymentMethodsLiveData.value =  repo.getPaymentMethods(language)

        }
    }






    fun getSignUpResponse() = registerMessageLiveData
    fun getLoginResponse() = loginMessageLiveData
    fun getUserInfoResponse() = userInfoMessageLiveData
    fun getUpdateUserInfoResponse() = updateUserInfoMessageLiveData
    fun getResetPasswordResponse() = resetPasswordLiveData
    fun getLocationsCustomersResponse() = getLocationsCustomerLiveData
    fun getAboutUsResponse() = getAboutUsLiveData
    fun getTermsConditionsResponse() = getTermsConditionsLiveData
    fun getPopularQuestionsResponse() = getPopularQuestionsLiveData
    fun getDeliveryResponse() = getDeliveryLiveData
    fun getPaymentMethodsResponse() = getPaymentMethodsLiveData

}