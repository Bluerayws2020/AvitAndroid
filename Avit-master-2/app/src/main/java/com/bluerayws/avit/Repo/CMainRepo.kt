package com.bluerayws.avit.Repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bluerayws.avit.Api.ApiClient
import com.bluerayws.avit.dataclass.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Header
import retrofit2.http.Part
import java.io.File

object CMainRepo {

    //   gitting live data
    private val categoryliveData = MutableLiveData<Category>()

    // get arr
    val categoryArray: LiveData<Category>
        get() = categoryliveData


    suspend fun getCategroyRepo() {
        val language = "ar"
        val languages = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        val result = ApiClient.retrofitService.getCategory(languages)

        if (result.body() != null) {
            categoryliveData.postValue(result.body())
            Log.d("done", result.body().toString())

        } else {
            Log.d("err", result.body().toString())
//            Toast.makeText(this,"Err",Toast.LENGTH_LONG).show()
        }



    }

    suspend fun userSignUp(
        provider_status: String,
        language: String,
        name_ar: String,
        name_en: String,
        username: String,
        email: String,
        phone: String,
        password: String,
        conPassword: String,
        country_id: String,
        region_id: String,

        ): NetworkResults<CustomerRegister> {
        return withContext(Dispatchers.IO) {
            val provider_statusBody = provider_status.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val name_enBody = name_en.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val name_arBody = name_ar.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val phoneBody = phone.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordBody = password.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val confirmPasswordBody = conPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val country_idBody = country_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val region_idBody = region_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val usernameBody = username.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val emailBody = email.toRequestBody("multipart/form-data".toMediaTypeOrNull())


            try {
                val results = ApiClient.retrofitService.getRegister(
                    provider_statusBody,
                    langBody,
                    name_arBody,
                    name_enBody,
                    usernameBody,
                    emailBody,
                    phoneBody,
                    passwordBody,
                    confirmPasswordBody,
                    country_idBody,
                    region_idBody
                )

                NetworkResults.Success(results)

            } catch (e: Exception) {
                NetworkResults.Error(e)
            }
        }
    }




    suspend fun userLogIn(
        email: String,
        password: String,
        language: String

        ): NetworkResults<CustomerLogin> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordBody = password.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val emailBody = email.toRequestBody("multipart/form-data".toMediaTypeOrNull())


            try {
                val results = ApiClient.retrofitService.getLogin(
                    emailBody,
                    passwordBody,
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Login User Error Repo: ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }





    suspend fun userInfo(
        language: String,
        token: String

    ): NetworkResults<CustomerInfo> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getCustomerInfo(
                    langBody,
                    "Bearer $token"
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("User Info Error Repo: ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }


    suspend fun updateUserInfo(
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
        image: File?

        ): NetworkResults<UpdateCustomerInfo> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val nameArBody = name_ar.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val nameEnBody = name_en.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val usernameBody = username.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val emailBody = email.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val phoneBody = phone.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val countryIdBody = country_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val regionIdBody = region_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val addressBody = address?.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            var imageReq: MultipartBody.Part? = null
            image?.let {
                imageReq = MultipartBody.Part.createFormData(
                    "profile_photo_path",
                    it.name,
                    it.asRequestBody("image/*".toMediaTypeOrNull())
                )
            }
            val nameImage = image?.name?.toRequestBody("multipart/form-data".toMediaTypeOrNull())



            try {
                val results = ApiClient.retrofitService.updateCustomerInfo(
                    langBody,
                    nameArBody,
                    nameEnBody,
                    usernameBody,
                    emailBody,
                    phoneBody,
                    countryIdBody,
                    regionIdBody,
                    addressBody!!,
                    "Bearer $token",
                    imageReq,
                    nameImage
                )

                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Update User Info Error Repo: ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }

//    resetPassword

    suspend fun getResetPassword(
        language: String,
        currentPassword: String,
        password: String,
        passwordConfirmation: String,
        token: String

    ): NetworkResults<ResetPassword> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val currentPasswordBody = currentPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordBody = password.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordConfirmationBody = passwordConfirmation.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.resetPassword(
                    langBody,
                    currentPasswordBody,
                    passwordBody,
                    passwordConfirmationBody,
                    "Bearer $token"
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Reset Password Error Repo : ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }



    suspend fun getLocationsCustomers(
        language: String,
        token: String

    ): NetworkResults<LocationsCustomers> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getLocationsCustomers(
                    langBody,
                    "Bearer $token"
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Locations Customers Error Repo : ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }



    suspend fun getAboutUs(
        language: String

    ): NetworkResults<AboutUsMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getAboutUs(
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("About Us Error Repo : ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }


    suspend fun getTermsConditionsMain(
        language: String

    ): NetworkResults<TermsConditionsMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getTermsConditionsMain(
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Terms and Conditions Error Repo : ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }


    suspend fun getPopularQuestions(
        language: String

    ): NetworkResults<PopularQuestionsMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getPopularQuestions(
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Popular Questions Error Repo : ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }



    suspend fun getDelivery(
        language: String

    ): NetworkResults<DeliveryMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getDelivery(
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Delivery Error Repo : ", e.message.toString())

                NetworkResults.Error(e)
            }
        }
    }



    suspend fun getPaymentMethods(
        language: String

    ): NetworkResults<PaymentMethodsMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getPaymentMethods(
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Payment Methods Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }



    suspend fun getCategories(
        language: String

    ): NetworkResults<CategoriesMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getCategories(
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Categories Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }


    suspend fun getProductsByCateId(
        language: String,
        categoryId: String

    ): NetworkResults<ProductsHomeMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val categoryIdBody = categoryId.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getProductsByCatId(
                    langBody,
                    categoryIdBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Categories Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }



}
