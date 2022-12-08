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
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.http.Part
import java.io.File


object CMainRepo {


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
            val provider_statusBody =
                provider_status.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val name_enBody = name_en.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val name_arBody = name_ar.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val phoneBody = phone.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordBody = password.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val confirmPasswordBody =
                conPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull())
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
            val currentPasswordBody =
                currentPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordBody = password.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordConfirmationBody =
                passwordConfirmation.toRequestBody("multipart/form-data".toMediaTypeOrNull())

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
//    getProductsDetails


    suspend fun getProductsDetails(
        language: String,
        productId: String

    ): NetworkResults<ProductDetailsMain> {
        return withContext(Dispatchers.IO) {
            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val productIdBody = productId.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getProductsDetails(
                    langBody,
                    productIdBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Product Details Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }


//    suspend fun getRelatedProduct(
//        product: Product
//
//
//    ): NetworkResults<RelatedProducts> {
//        return withContext(Dispatchers.IO) {
//
////            val productIdBody = productId.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//
//            try {
//                val results = ApiClient.retrofitService.getRelatedProducts(
//                    product
//                )
//
//
//                NetworkResults.Success(results)
//
//            } catch (e: Exception) {
//                Log.d("Related Products Error Repo : ", e.message.toString())
//                NetworkResults.Error(e)
//
//            }
//        }
//    }


    suspend fun getRequestProduct(
        language: String,
        productId: String,
        token: String

    ): NetworkResults<RequestWishlist> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val productIdBody = productId.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getRequestProduct(
                    langBody,
                    productIdBody,
                    token
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Request Products Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }



    suspend fun getProductWishList(
        language: String,
        token: String

    ): NetworkResults<ProductWishListMain> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getProductWishList(
                    langBody,
                    token
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Product WishList Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }


    suspend fun addToBag(
        language: String,
        product_id: String,
        quantity: String,
        color_id: String,
        size_id: String,
        token: String


    ): NetworkResults<AddToBag> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val proIdBody = product_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val quantityBody = quantity.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val colorIdBody = color_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val sizeIdBody = size_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.addToBag(
                    langBody,
                    proIdBody,
                    quantityBody,
                    colorIdBody,
                    sizeIdBody,
                    token
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Add to Bag Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }



    suspend fun getCustomerCart(
        language: String,
        token: String


    ): NetworkResults<CustomerCart> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getCustomerCart(
                    langBody,
                    token
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Customer Cart Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }



    suspend fun removeFromCart(
        language: String,
        cartItemId: String,
        token: String


    ): NetworkResults<DeleteFromCart> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val cartItemIdBody = cartItemId.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.deleteFromCart(
                    langBody,
                    cartItemIdBody,
                    token
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Delete Item Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }

//    getBrands


    suspend fun getBrands(
        language: String

    ): NetworkResults<BrandItems> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getBrands(
                    langBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Brand Items Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }

//    getProductsByBrandId


    suspend fun getProductsByBrandId(
        language: String,
        brandId: String

    ): NetworkResults<BrandsMain> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val brandIdBody = brandId.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getProductsByBrandId(
                    langBody,
                    brandIdBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Products of Brand Id Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }
//    getSimilarItems


    suspend fun getSimilarItems(
        language: String,
        token: String

    ): NetworkResults<SimilarItemsMain> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getSimilarItems(
                    langBody,
                    token
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Similar Items Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }


//    getProductsOfSearching

    suspend fun getProductsOfSearching(
        language: String,
        name: String

    ): NetworkResults<SearchOfProducts> {
        return withContext(Dispatchers.IO) {

            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val nameBody = name.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getProductsOfSearching(
                    langBody,
                    nameBody
                )


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Products Of Searching Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }

//    updateCartQuantity

    suspend fun updateCartQuantity(
        language: String,
        productId: String,
        quantity: Int,
        colorId: String,
        sizeId: String,
        token: String

    ): NetworkResults<UpdateCartQuantity> {
        return withContext(Dispatchers.IO) {

//            val langBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//            val productIdBody = productId.toRequestBody("application/json".toMediaTypeOrNull())
//            val quantityBody = quantity.toRequestBody("application/json".toMediaTypeOrNull())
//            val colorIdBody = colorId.toRequestBody("application/json".toMediaTypeOrNull())
//            val sizeIdBody = sizeId.toRequestBody("application/json".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.updateCartQuantity(
                    UpdateQuantity(language,
                    listOf(
                        UpdateData(
                            productId, quantity, colorId, sizeId
                        )
                    )
                    ), token)


                NetworkResults.Success(results)

            } catch (e: Exception) {
                Log.d("Update Cart Quantity Error Repo : ", e.message.toString())
                NetworkResults.Error(e)

            }
        }
    }


}
