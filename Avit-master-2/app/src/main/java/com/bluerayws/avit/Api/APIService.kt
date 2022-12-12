package com.bluerayws.avit.Api

import com.bluerayws.avit.dataclass.*
import com.google.gson.JsonObject
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
        @Header("Authorization") auth: String


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
        @Header("Authorization") auth: String,
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
        @Header("Authorization") auth: String


    ): ResetPassword


    @Multipart
    @POST("customer/getLocationsCustomer")
    suspend fun getLocationsCustomers(
        @Part("lang") lang: RequestBody,
        @Header("Authorization") auth: String

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


    @Multipart
    @POST("frontend/getProductDetails")
    suspend fun getProductsDetails(
        @Part("lang") lang: RequestBody,
        @Part("product_number") product_number: RequestBody,


        ): ProductDetailsMain


    @Headers("Content-Type: application/json")
    @POST("customer/relatedProduct")
    fun getRelatedProducts(
        @Body product: Product?

    ): Call<RelatedProducts>


    @Multipart
    @POST("customer/customerWishlistRequest")
    suspend fun getRequestProduct(
        @Part("lang") lang: RequestBody,
        @Part("product_id") product_id: RequestBody,
        @Header("Authorization") auth: String

    ): RequestWishlist


    @Multipart
    @POST("customer/customerWishlist")
    suspend fun getProductWishList(
        @Part("lang") lang: RequestBody,
        @Header("Authorization") auth: String

    ): ProductWishListMain


    @Multipart
    @POST("customer/addToCart")
    suspend fun addToBag(
        @Part("lang") lang: RequestBody,
        @Part("product_id") product_id: RequestBody,
        @Part("quantity") quantity: RequestBody,
        @Part("color_id") color_id: RequestBody,
        @Part("size_id") size_id: RequestBody,
        @Header("Authorization") auth: String


    ): AddToBag


    @Multipart
    @POST("customer/getCart")
    suspend fun getCustomerCart(
        @Part("lang") lang: RequestBody,
        @Header("Authorization") auth: String

    ): CustomerCart


//    DeleteFromCart

    @Multipart
    @POST("customer/deleteFromCart")
    suspend fun deleteFromCart(
        @Part("lang") lang: RequestBody,
        @Part("cart_temp_id") cart_temp_id: RequestBody,
        @Header("Authorization") auth: String

    ): DeleteFromCart


    @Multipart
    @POST("frontend/getBrands")
    suspend fun getBrands(
        @Part("lang") lang: RequestBody

    ): BrandItems


    @Multipart
    @POST("frontend/getProductsOfCategoryNoORBrandNo")
    suspend fun getProductsByBrandId(
        @Part("lang") lang: RequestBody,
        @Part("brand_id") brand_id: RequestBody

    ): BrandsMain


    @Multipart
    @POST("customer/similarProductsOfWishlist")
    suspend fun getSimilarItems(
        @Part("lang") lang: RequestBody,
        @Header("Authorization") auth: String

    ): SimilarItemsMain


//    SearchOfProducts


    @Multipart
    @POST("frontend/searchOfProducts")
    suspend fun getProductsOfSearching(
        @Part("lang") lang: RequestBody,
        @Part("name") name: RequestBody

    ): SearchOfProducts


//    UpdateCartQuantity

    @Headers("Content-Type: application/json")
    @POST("customer/updateCartQuantity")
    suspend fun updateCartQuantity(
        @Body data: UpdateQuantity?,
        @Header("Authorization") auth: String


    ): UpdateCartQuantity


//    Stores


    @Multipart
    @POST("frontend/getStores")
    suspend fun getStores(
        @Part("lang") lang: RequestBody

    ): Stores

//    SearchOfCategories




    @Multipart
    @POST("frontend/searchOfCategories")
    suspend fun searchOfCategories(
        @Part("lang") lang: RequestBody,
        @Part("name") name: RequestBody

    ): SearchOfCategories




    // Guest Cases :-


    @Multipart
    @POST("guest/guestCart")
    suspend fun getGuestCart(
        @Part("lang") lang: RequestBody,
        @Part("device_id") device_id: RequestBody

    ): GuestCart


    @Multipart
    @POST("guest/guestAddToCart")
    suspend fun addToGuestCart(
        @Part("lang") lang: RequestBody,
        @Part("product_id") product_id: RequestBody,
        @Part("quantity") quantity: RequestBody,
        @Part("color_id") color_id: RequestBody,
        @Part("size_id") size_id: RequestBody,
        @Part("device_id") device_id: RequestBody

    ): AddToBag


    @Multipart
    @POST("guest/guestDeleteFromCart")
    suspend fun deleteFromGuestCart(
        @Part("lang") lang: RequestBody,
        @Part("cart_temp_id") cart_temp_id: RequestBody

    ): DeleteFromCart

//    guest/guestUpdateCartQuantity


    @Headers("Content-Type: application/json")
    @POST("guest/guestUpdateCartQuantity")
    suspend fun updateGuestCartQuantity(
        @Body data: UpdateGuestCartsQuantity?

    ): UpdateCartQuantity


}