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
    @SerializedName ("products_data") val products_data: List<ProductsDataMain>


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



data class ProductDetailsMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("product_details") val product_details: ProductDetails

)

data class ProductDetails(
    @SerializedName ("id") val id: String,
    @SerializedName ("category_id") val category_id: String,
    @SerializedName ("design_id") val design_id: String,
    @SerializedName ("brand_id") val brand_id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("description_ar") val description_ar: String,
    @SerializedName ("sale_price") val sale_price: String,
    @SerializedName ("on_sale_price") val on_sale_price: String,
//    @SerializedName ("on_sale_price_status") val on_sale_price_status: String,
    @SerializedName ("material_en") val material_en: String,
    @SerializedName ("material_ar") val material_ar: String,
    @SerializedName ("Ironing_and_washing_information_en") val Ironing_and_washing_information_en: String,
    @SerializedName ("Ironing_and_washing_information_ar") val Ironing_and_washing_information_ar: String,
    @SerializedName ("quantity_available") val quantity_available: String,
    @SerializedName ("quantity_limit") val quantity_limit: String,
    @SerializedName ("image") val image: String,
    @SerializedName ("erp_created_at") val erp_created_at: String,
    @SerializedName ("erp_updated_at") val erp_updated_at: String,
    @SerializedName ("currency_code") val currency_code: String,
    @SerializedName ("colors") val colors: List<Colors>,
    @SerializedName ("sizes") val sizes: List<Sizes>,
    @SerializedName ("product_images") val product_images: List<ProductImages>,
    @SerializedName ("product_small_images") val product_small_images: List<ProductImages>,

    )


data class Colors(
    @SerializedName ("id") val id: String,
    @SerializedName ("color_name_ar") val color_name_ar: String,
    @SerializedName ("color_name_en") val color_name_en: String

    )

data class Sizes(
    @SerializedName ("id") val id: String,
    @SerializedName ("size_name_ar") val size_name_ar: String,
    @SerializedName ("size_name_en") val size_name_en: String

    )

data class ProductImages(
    @SerializedName ("image") val image: String,
    @SerializedName ("color_id") val color_id: String

    )



data class RelatedProducts(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("related_products") val related_products: List<RelatedProductsItems>

    )


data class RelatedProductsItems(
    @SerializedName ("id") val id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("description_ar") val description_ar: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("image") val image: String

    )


data class Product(
    @SerializedName ("lang") val lang: String,
    @SerializedName ("product_ids") val product_ids: List<String>
)



data class RequestWishlist(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String

    )





data class ProductWishListMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("Product_Wishlist") val Product_Wishlist: List<ProductWishlist>

    )


data class ProductWishlist(
    @SerializedName ("product_id") val product_id: String,
    @SerializedName ("id") val id: Int,
    @SerializedName ("product_name_ar") val product_name_ar: String,
    @SerializedName ("product_name_en") val product_name_en: String,
    @SerializedName ("category_name_ar") val category_name_ar: String,
    @SerializedName ("category_name_en") val category_name_en: String,
    @SerializedName ("image") val image: String,
    @SerializedName ("product_currency_code") val product_currency_code: String,
    @SerializedName ("product_sale_price") val product_sale_price: String,
    @SerializedName ("product_on_sale_price") val product_on_sale_price: String,
    @SerializedName ("product_on_sale_price_status") val product_on_sale_price_status: String

    )




data class AddToBag(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,

    )



data class CustomerCart(
    @SerializedName ("status") val status: Int,
    @SerializedName ("success") val success: Boolean,
    @SerializedName ("customer_cart_header") val customer_cart_header: CustomerCartHeader,
    @SerializedName ("customer_cart_data") val customer_cart_data: List<CustomerCartData>

    )

data class CustomerCartHeader(
    @SerializedName ("subTotal") val subTotal: String,
    @SerializedName ("tax") val tax: String,
    @SerializedName ("promoCode") val promoCode: String,
    @SerializedName ("endTotal") val endTotal: String,

    )

data class CustomerCartData(
    @SerializedName ("id") val id: Int,
    @SerializedName ("user_id") val user_id: Int,
    @SerializedName ("user_type") val user_type: String,
    @SerializedName ("device_id") val device_id: String,
    @SerializedName ("product_id") val product_id: String,
    @SerializedName ("quantity") val quantity: String,
    @SerializedName ("size_id") val size_id: String,
    @SerializedName ("color_id") val color_id: String,
    @SerializedName ("created_at") val created_at: String,
    @SerializedName ("updated_at") val updated_at: String,
    @SerializedName ("itemPrice") val itemPrice: String,
    @SerializedName ("itemSubTotal") val itemSubTotal: String,
    @SerializedName ("itemTax") val itemTax: String,
    @SerializedName ("itemEndTotal") val itemEndTotal: String,
    @SerializedName ("product_name_en") val product_name_en: String,
    @SerializedName ("product_name_ar") val product_name_ar: String,
    @SerializedName ("product_image") val product_image: String,
    @SerializedName ("size_name_en") val size_name_en: String,
    @SerializedName ("size_name_ar") val size_name_ar: String,
    @SerializedName ("color_name_en") val color_name_en: String,
    @SerializedName ("color_name_ar") val color_name_ar: String,
    @SerializedName ("wishlist") val wishlist: String

    )



data class DeleteFromCart(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String

    )



data class BrandItems(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("brands") val brands: List<Brands>

    )

data class Brands(
    @SerializedName ("id") val id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("image") val image: String,
    @SerializedName ("url") val url: String,
    var isSelected:Boolean = false

)


data class BrandsMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("meta") val meta: MetaMain,
    @SerializedName ("products_data") val products_data: List<BrandItemsMain>


)


data class BrandItemsMain(
    @SerializedName ("id") val id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") var name_en: String,
    @SerializedName ("sale_price") var sale_price: String,
    @SerializedName ("on_sale_price") var on_sale_price: String,
    @SerializedName ("material_en") val material_en: String,
    @SerializedName ("material_ar") val material_ar: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("description_ar") val description_ar: String,
    @SerializedName ("image") val image: String,
    @SerializedName ("wishlist") var wishlist: Int

)



data class SimilarItemsMain(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("products_data") val products_data: List<SimilarItems>

    )



data class SimilarItems(
    @SerializedName ("product_id") val product_id: String,
    @SerializedName ("product_name_ar") val product_name_ar: String,
    @SerializedName ("product_name_en") var product_name_en: String,
    @SerializedName ("product_sale_price") var product_sale_price: String,
    @SerializedName ("product_on_sale_price") var product_on_sale_price: String,
    @SerializedName ("category_name_ar") val category_name_ar: String,
    @SerializedName ("category_name_en") val category_name_en: String,
    @SerializedName ("image") val image: String

    )




data class SearchOfProducts(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("products") val products: List<ProductsOfSearching>

    )


data class ProductsOfSearching(
    @SerializedName ("id") val id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("description_ar") val description_ar: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("image") val image: String

    )



data class UpdateCartQuantity(
    @SerializedName ("status") val status: Int,
    @SerializedName ("success") val errNum: Boolean,
    @SerializedName ("message") val msg: String,
)



data class UpdateQuantity(
    @SerializedName ("lang") val lang: String,
    @SerializedName ("data") val data: List<UpdateData>
)


data class UpdateGuestCartsQuantity(
    @SerializedName ("lang") val lang: String,
    @SerializedName ("device_id") val device_id: String,
    @SerializedName ("data") val data: List<UpdateData>
)

data class UpdateData(
    @SerializedName ("product_id") val product_id: String,
    @SerializedName ("quantity") val quantity: Int,
    @SerializedName ("color_id") val color_id: String,
    @SerializedName ("size_id") val size_id: String,

    )




data class Stores(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("stores") val stores: List<StoreDetails>

    )

data class StoreDetails(
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("address_ar") val address_ar: String,
    @SerializedName ("address_en") val address_en: String,
    @SerializedName ("url") val url: String,
    @SerializedName ("phone") val phone: String

    )





data class SearchOfCategories(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("categories") val CategoriesList: List<CategoriesList>

    )


data class CategoriesList(
    @SerializedName ("id") val id: String,
    @SerializedName ("name_ar") val name_ar: String,
    @SerializedName ("name_en") val name_en: String,
    @SerializedName ("description_ar") val description_ar: String,
    @SerializedName ("description_en") val description_en: String,
    @SerializedName ("show_in_menu_status") val show_in_menu_status: String,
    @SerializedName ("order_in_menu") val order_in_menu: String,
    @SerializedName ("image") val image: String

    )





// Guest Cases:

data class GuestCart(
    @SerializedName ("status") val status: Boolean,
    @SerializedName ("errNum") val errNum: String,
    @SerializedName ("msg") val msg: String,
    @SerializedName ("customer_cart") val CategoriesList: List<CustomerCartData>

    )


data class GuestCartData(
    @SerializedName ("id") val id: Int,
    @SerializedName ("user_id") val user_id: Int,
    @SerializedName ("user_type") val user_type: String,
    @SerializedName ("device_id") val device_id: String,
    @SerializedName ("product_id") val product_id: String,
    @SerializedName ("quantity") val quantity: String,
    @SerializedName ("size_id") val size_id: String,
    @SerializedName ("color_id") val color_id: String,
    @SerializedName ("created_at") val created_at: String,
    @SerializedName ("updated_at") val updated_at: String,
    @SerializedName ("itemPrice") val itemPrice: String,
    @SerializedName ("itemSubTotal") val itemSubTotal: String,
    @SerializedName ("itemTax") val itemTax: String,
    @SerializedName ("itemEndTotal") val itemEndTotal: String,
    @SerializedName ("product_name_en") val product_name_en: String,
    @SerializedName ("product_name_ar") val product_name_ar: String,
    @SerializedName ("product_image") val product_image: String,
    @SerializedName ("size_name_en") val size_name_en: String,
    @SerializedName ("size_name_ar") val size_name_ar: String,
    @SerializedName ("color_name_en") val color_name_en: String,
    @SerializedName ("color_name_ar") val color_name_ar: String

    )