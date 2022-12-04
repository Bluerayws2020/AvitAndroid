package com.bluerayws.avit.dataclass

import com.google.gson.annotations.SerializedName

data class TestClass(val name: String, val phone: Int)
data class TestClass2(val lat: String, val lon: String)
//data class Colors(val color: String)


//data class Size(
//    val size: String,
//    val size_id: String
//)




//{
//    "status": true,
//    "errNum": "S000",
//    "msg": "",
//
//}


//data class MessageModel(
//    @SerializedName("status") val status: Boolean,
//    @SerializedName("errNum") val errNum: String,
//    @SerializedName("msg") val msg: String
//)


data class CustomerRegister(
    @SerializedName("status") val status: Boolean,
    @SerializedName("errNum") val errNum: String,
    @SerializedName("msg") val msg: String,
    @SerializedName("user_data") val user_data: UserData

    )


data class CustomerLogin(
    @SerializedName ("status")val  status: Boolean,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("errNum") val error: String,
    @SerializedName ("user_data") val user_info: UserData

    )


data class UserData(
    @SerializedName("id") val id: Int,
    @SerializedName("access_token") val access_token: String,
    @SerializedName("name_ar") val name_ar: String,
    @SerializedName("name_en") val name_en: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("country_id") val country_id: String,
    @SerializedName("country_name_en") val country_name_en: String,
    @SerializedName("country_name_ar") val country_name_ar: String,
    @SerializedName("region_id") val region_id: String,
    @SerializedName("region_name_en") val region_name_en: String,
    @SerializedName("region_name_ar") val region_name_ar: String,
//    @SerializedName("address") val address: String,
    @SerializedName("user_status") val user_status: String,
//    @SerializedName("provider_id") val provider_id: String,
//    @SerializedName("provider") val provider: String,
    @SerializedName("provider_status") val provider_status: String,
//    @SerializedName("profile_photo_path") val profile_photo_path: String,

    )



data class CustomerInfo(
    @SerializedName ("status")val  status: Int,
    @SerializedName ("success") val success: Boolean,
    @SerializedName ("request_date") val request_date: String,
    @SerializedName ("data") val data: CustomerData
)



data class CustomerData(
    @SerializedName("id") val id: Int,
    @SerializedName("id_erp") val id_erp: String,
    @SerializedName("name_ar") val name_ar: String,
    @SerializedName("name_en") val name_en: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("country_id") val country_id: String,
    @SerializedName("region_id") val region_id: String,
    @SerializedName("address") val address: String,
    @SerializedName("user_status") val user_status: String,
    @SerializedName("point") val point: Int,
    @SerializedName("provider_status") val provider_status: Int,
    @SerializedName("update_status") val update_status: Int,
//    @SerializedName("provider") val provider: Int,
//    @SerializedName("provider_id") val provider_id: Int,
    @SerializedName("created_by") val created_by: Int,
//    @SerializedName("email_verified_at") val email_verified_at: Int,
    @SerializedName("country_name_en") val country_name_en: String,
    @SerializedName("country_name_ar") val country_name_ar: String,
    @SerializedName("city_name_en") val city_name_en: String,
    @SerializedName("city_name_ar") val city_name_ar: String,
    @SerializedName("profile_photo") val profile_photo: String

    )


data class UpdateCustomerInfo(
    @SerializedName ("status")val  status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("customer_profile_data") val customer_profile_data: UpdateCustomerData
)

data class UpdateCustomerData(
    @SerializedName("id") val id: Int,
    @SerializedName("id_erp") val id_erp: String,
    @SerializedName("name_ar") val name_ar: String,
    @SerializedName("name_en") val name_en: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("country_id") val country_id: String,
    @SerializedName("region_id") val region_id: String,
    @SerializedName("address") val address: String,
    @SerializedName("profile_photo_path") val profile_photo_path: String,
    @SerializedName("user_status") val user_status: String,
    @SerializedName("point") val point: Int,
    @SerializedName("provider_status") val provider_status: Int,
    @SerializedName("provider") val provider: Int,
    @SerializedName("provider_id") val provider_id: Int,

)



data class ResetPassword(
    @SerializedName ("status")val  status: Boolean,
    @SerializedName ("errNum") val error: String,
    @SerializedName ("msg") val msg: String
)



data class LocationsCustomers(
    @SerializedName ("status")val  status: Int,
    @SerializedName ("success") val success: Boolean,
    @SerializedName ("request_date") val request_date: String,
    @SerializedName ("data") val data: List<LocationsData>

)

data class LocationsData(
    @SerializedName("address") val address: String,
    @SerializedName("phone") val phone: Int

    )



data class AboutUsMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("aboutUs") val aboutUs: AboutUs
)

data class AboutUs(
    @SerializedName ("about_us_ar") val about_us_ar: String,
    @SerializedName ("about_us_en") val about_us_en: String
)


data class TermsConditionsMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("termsAndConditions") val termsAndConditions: List<TermsConditions>
)

data class TermsConditions(
    @SerializedName ("term_and_condition_title_ar") val term_and_condition_title_ar: String,
    @SerializedName ("term_and_condition_title_en") val term_and_condition_title_en: String,
    @SerializedName ("term_and_condition_des_ar") val term_and_condition_des_ar: String,
    @SerializedName ("term_and_condition_des_en") val term_and_condition_des_en: String
)


data class HelpModel(
    val title: String,
    val image: Int
    )


data class PopularQuestionsMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("faqs") val faqs: List<Faqs>


)

data class Faqs(
    @SerializedName ("title_ar") val title_ar: String,
    @SerializedName ("title_en") val title_en: String,
    @SerializedName ("answer_ar") val answer_ar: String,
    @SerializedName ("answer_en") val answer_en: String


)



data class DeliveryMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("delivery") val delivery: Delivery

)


data class Delivery(
    @SerializedName ("image") val image: String,
    @SerializedName ("details_en") val details_en: String,
    @SerializedName ("details_ar") val details_ar: String


)


data class PaymentMethodsMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("payment_methods") val payment_methods: List<PaymentMethods>


)

data class PaymentMethods(
    @SerializedName ("title_ar") val title_ar: String,
    @SerializedName ("title_en") val title_en: String,
    @SerializedName ("answer_ar") val answer_ar: String,
    @SerializedName ("answer_en") val answer_en: String


)



data class CategoriesMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("categories") val categories: List<Categories>

)



data class Categories(
    @SerializedName ("id") val id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("description_ar") val description_ar: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("order_in_menu") val order_in_menu: String,
    @SerializedName ("image") val image: String,
    @SerializedName ("show_in_menu_status") val show_in_menu_status: String,
    var isSelected:Boolean = false

)



data class ProductsHomeMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("meta") val meta: MetaMain,
    @SerializedName ("products_data") val products_data: List<ProductsDataMain>,


    )

data class MetaMain(
    @SerializedName ("totalPages") val totalPages: Int,
    @SerializedName ("currentPage") val currentPage: Int,
    @SerializedName ("totalRecords") val totalRecords: Int,
    @SerializedName ("recordsOnCurrentPage") val recordsOnCurrentPage: Int,
    @SerializedName ("recordFrom") val recordFrom: Int,
    @SerializedName ("recordTo") val recordTo: Int,


    )


data class ProductsDataMain(
    @SerializedName ("id") val id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") var name_en: String,
    @SerializedName ("material_en") val material_en: String,
    @SerializedName ("material_ar") val material_ar: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("description_ar") val description_ar: String,
    @SerializedName ("image") val image: String,
    @SerializedName ("wishlist") var wishlist: Int


)














// old Api
data class Category(
    val status: String,
    val msg: String,
    val categories :List<Category_Array>

)
data class Category_Array(
    @SerializedName ("id")val  cid: String,
    @SerializedName ("name_ar") val name_ar: String

    )




data class Register(
    @SerializedName ("id")val  cid: Int,
    @SerializedName ("name_ar")val  name_ar: String,
    @SerializedName ("name_en")val  name_en: String,
    @SerializedName ("username")val  username: String,
    @SerializedName ("email")val  email: String,
    @SerializedName ("phone")val  phone: String,
    @SerializedName ("access_token") val access_token: String


)


data class Countries_main(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("Countries") val country: Countries


)


data class Countries(
    @SerializedName ("id") val couId: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("shipping_cost") val shipping_cost: String


)



data class Regions_main(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("Regions") val Region: Regions


)


data class Regions(
    @SerializedName ("country_id") val couId: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("shipping_cost") val shipping_cost: String


)



data class ProductDetails_main(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("product_details") val product_detail: Product_Main


)

data class Product_Main(

    @SerializedName ("id") val pd_id: String,
        //sale_price
    @SerializedName ("sale_price") val sale_price: String,
    @SerializedName ("name_en") val pname: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("category_id") val category_id: String,
    @SerializedName ("design_id") val design_id: String,


    @SerializedName ("colors") val Colors: List<colors_main>,
    @SerializedName("sizes") val  Sizes: List<sizes_main>

        //@SerializedName ("product_images") val product_images: product_images,
        //@SerializedName ("product_small_images") val product_small_image: product_small_images
)

data class colors_main(

    @SerializedName ("id") val col_id: String,
    @SerializedName ("color_name_ar") val color_name_ar: String,
    @SerializedName ("color_name_en") val color_name_en: String


)


data class sizes_main(
    @SerializedName ("id") val sz_id: String,
    @SerializedName ("size_name_ar") val size_name_ar: String,
    @SerializedName ("size_name_en") val size_name_en: String


)


data class customer_wishlist(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("Product_Wishlist") val Product_Wishlist: List<Product_Main>

)



data class customer_wishlist_request(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String

)




data class Customer_Profile(
    @SerializedName ("status") val status: Int,
    @SerializedName ("success") val success: Boolean,
    @SerializedName ("request_date") val request_date: String,
    @SerializedName ("data") val data: Customer_Info


)




data class Customer_Info(
    @SerializedName("id") val id: Int,
    @SerializedName("id_erp") val id_erp: String,
    @SerializedName("name_ar") val name_ar: String,
    @SerializedName("name_en") val name_en: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("country_id") val country_id: String,
    @SerializedName("region_id") val region_id: String,
    @SerializedName("address") val address: String,
    @SerializedName("user_status") val user_status: String,
    @SerializedName("provider") val provider: String,
    @SerializedName("provider_id") val provider_id: String,
    @SerializedName("created_by") val created_by: Int,
    @SerializedName("email_verified_at") val email_verified_at: String,
    @SerializedName("country_name_en") val country_name_en: String,
    @SerializedName("country_name_ar") val country_name_ar: String,
    @SerializedName("city_name_en") val city_name_en: String,
    @SerializedName("city_name_ar") val city_name_ar: String,
    @SerializedName("profile_photo") val profile_photo: String,
)
