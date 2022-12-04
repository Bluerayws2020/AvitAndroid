package com.bluerayws.avit.Api

import com.bluerayws.avit.dataclass.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {


    @Multipart
    @POST("customer/register")
    suspend fun getRegister(
        @Part("provider_status") provider_status: RequestBody,
        @Part("lang") lang: RequestBody,
        @Part("name_ar") name_ar: RequestBody,
        @Part("name_en") name_en: RequestBody,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") password_conf: RequestBody,
        @Part("country_id") country_id: RequestBody,
        @Part("region_id") region_id: RequestBody,


    ): CustomerRegister




    @Multipart
    @POST("customer/login")
    suspend fun getLogin(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("lang") lang: RequestBody


        ): CustomerLogin



    @Multipart
    @POST("customer/getCustomerProfile")
    suspend fun getCustomerInfo(
        @Part("lang") lang: RequestBody,
        @Header ("Authorization") auth:String


    ): CustomerInfo



    @Multipart
    @POST("customer/updateCustomerProfile")
    suspend fun updateCustomerInfo(
        @Part("lang") lang: RequestBody,
        @Part("name_ar") name_ar: RequestBody,
        @Part("name_en") name_en: RequestBody,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("country_id") country_id: RequestBody,
        @Part("region_id") region_id: RequestBody,
        @Part("address") address: RequestBody?,
        @Header ("Authorization") auth:String,
        @Part image: MultipartBody.Part?,
        @Part("profile_photo_path") imageName: RequestBody?


    ): UpdateCustomerInfo




    @Multipart
    @POST("customer/resetPassword")
    suspend fun resetPassword(
        @Part("lang") lang: RequestBody,
        @Part("current_password") current_password: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") password_confirmation: RequestBody,
        @Header ("Authorization") auth:String


    ): ResetPassword



    @Multipart
    @POST("customer/getLocationsCustomer")
    suspend fun getLocationsCustomers(
        @Part("lang") lang: RequestBody,
        @Header ("Authorization") auth:String

    ): LocationsCustomers




    @Multipart
    @POST("frontend/getAboutUs")
    suspend fun getAboutUs(
        @Part("lang") lang: RequestBody

    ): AboutUsMain



    @Multipart
    @POST("frontend/getTermAndCondition")
    suspend fun getTermsConditionsMain(
        @Part("lang") lang: RequestBody

    ): TermsConditionsMain




    @Multipart
    @POST("frontend/getFaqs")
    suspend fun getPopularQuestions(
        @Part("lang") lang: RequestBody

    ): PopularQuestionsMain





    @Multipart
    @POST("frontend/getDelivery")
    suspend fun getDelivery(
        @Part("lang") lang: RequestBody

    ): DeliveryMain




    @Multipart
    @POST("frontend/getPaymentMethods")
    suspend fun getPaymentMethods(
        @Part("lang") lang: RequestBody

    ): PaymentMethodsMain




    @Multipart
    @POST("frontend/getGategories")
    suspend fun getCategories(
        @Part("lang") lang: RequestBody

    ): CategoriesMain




    @Multipart
    @POST("frontend/getProductsOfCategoryNo")
    suspend fun getProductsByCatId(
        @Part("lang") lang: RequestBody,
        @Part("category_id") category_id: RequestBody,
//        @Header ("Authorization") auth:String


    ): ProductsHomeMain














    // old api
    @Multipart
    @POST("api/frontend/getCountries")
    suspend fun getCountries(
        @Part("lang") lang: RequestBody

    ): Countries_main

    @Multipart
    @POST("api/frontend/getGategories")
    suspend fun getCategory(@Part("lang") language: RequestBody): Response<Category>

    @Multipart
    @POST("api/frontend/getRegions")
    suspend fun getRegions(
        @Part("lang") lang: RequestBody,
        @Part("country_id") country_id: RequestBody

    ): Regions_main


//    https://avit2.br-ws.com/api/frontend/getProductDetails


    @Multipart
    @POST("api/frontend/getProductDetails")
     fun getProductDetails(
        @Part("lang") lang: RequestBody,
        @Part("product_number") product_num: RequestBody


    ): Call<ProductDetails_main> //model



     @Multipart
     @POST("api/customer/customerWishlist")
     fun getCustomerWishList(
         @Part("lang") lang: RequestBody


     ) : Call<customer_wishlist>


     @Multipart
     @POST("api/customer/customerWishlistRequest")
     fun customerWishListRequest(
         @Part("lang") lang: RequestBody,
         @Part("product_id") prod_id: RequestBody


     ) : customer_wishlist_request



    @Multipart
    @Headers(value = ["Accept: application/json", "Content-type:application/json"])
    @POST("api/customer/getCustomerProfile")
    fun getCustomerProfile(
        @Part("lang") lang: RequestBody,



    ) : Call<Customer_Profile>

}