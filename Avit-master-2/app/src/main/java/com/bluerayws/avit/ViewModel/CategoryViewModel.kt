package com.bluerayws.avit.ViewModel

import androidx.lifecycle.*
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.dataclass.*
import kotlinx.coroutines.launch


class CategoryViewModel(private val categoryRepo:CMainRepo):ViewModel (){

    private val getCategoriesLiveData= MutableLiveData<NetworkResults<CategoriesMain>>()
    private val getProductsByCateIdLiveData= MutableLiveData<NetworkResults<ProductsHomeMain>>()
    private val getProductsDetailsLiveData= MutableLiveData<NetworkResults<ProductDetailsMain>>()
    private val getRelatedProductsLiveData= MutableLiveData<NetworkResults<RelatedProducts>>()
    private val getRequestProductsLiveData= MutableLiveData<NetworkResults<RequestWishlist>>()
    private val getProductWishListLiveData= MutableLiveData<NetworkResults<ProductWishListMain>>()
    private val addToBagLiveData= MutableLiveData<NetworkResults<AddToBag>>()
    private val getCustomerCartLiveData= MutableLiveData<NetworkResults<CustomerCart>>()
    private val removeFromCartLiveData= MutableLiveData<NetworkResults<DeleteFromCart>>()
    private val getBrandsLiveData= MutableLiveData<NetworkResults<BrandItems>>()
    private val getProductsByBrandIdLiveData= MutableLiveData<NetworkResults<BrandsMain>>()
    private val getSimilarItemsLiveData= MutableLiveData<NetworkResults<SimilarItemsMain>>()



    fun getCategories(
        language: String
    ) {
        viewModelScope.launch {
            getCategoriesLiveData.value =  categoryRepo.getCategories(language)

        }
    }

    fun getProductsByCateId(
        language: String,
        categoryId: String
    ) {
        viewModelScope.launch {
            getProductsByCateIdLiveData.value =  categoryRepo.getProductsByCateId(language, categoryId)

        }
    }



    fun getProductsDetails(
        language: String,
        productId: String
    ) {
        viewModelScope.launch {
            getProductsDetailsLiveData.value =  categoryRepo.getProductsDetails(language, productId)

        }
    }



    fun getRelatedProduct(
        product: Product

    ) {
        viewModelScope.launch {
            getRelatedProductsLiveData.value =  categoryRepo.getRelatedProduct(product)

        }
    }



    fun getRequestProduct(
        language: String,
        productId: String,
        token: String
    ) {
        viewModelScope.launch {
            getRequestProductsLiveData.value =  categoryRepo.getRequestProduct(language, productId, "Bearer $token")

        }
    }
//    getProductWishList


    fun getProductWishList(
        language: String,
        token: String
    ) {
        viewModelScope.launch {
            getProductWishListLiveData.value =  categoryRepo.getProductWishList(language, "Bearer $token")

        }
    }
//    addToBag


    fun addToBag(
        language: String,
        product_id: String,
        quantity: String,
        color_id: String,
        size_id: String,
        token: String
    ) {
        viewModelScope.launch {
            addToBagLiveData.value =  categoryRepo.addToBag(language, product_id, quantity, color_id, size_id,"Bearer $token")

        }
    }


//    getCustomerCart

    fun getCustomerCart(
        language: String,
        token: String
    ) {
        viewModelScope.launch {
            getCustomerCartLiveData.value =  categoryRepo.getCustomerCart(language, "Bearer $token")

        }
    }


//    removeFromCart

    fun removeFromCart(
        language: String,
        cartItemId: String,
        token: String
    ) {
        viewModelScope.launch {
            removeFromCartLiveData.value =  categoryRepo.removeFromCart(language, cartItemId,"Bearer $token")

        }
    }


    fun getBrands(
        language: String
    ) {
        viewModelScope.launch {
            getBrandsLiveData.value =  categoryRepo.getBrands(language)

        }
    }

//    getProductsByBrandId

    fun getProductsByBrandId(
        language: String,
        brandId: String
    ) {
        viewModelScope.launch {
            getProductsByBrandIdLiveData.value =  categoryRepo.getProductsByBrandId(language, brandId)

        }
    }

//    getSimilarItems


    fun getSimilarItems(
        language: String,
        token: String
    ) {
        viewModelScope.launch {
            getSimilarItemsLiveData.value =  categoryRepo.getSimilarItems(language, "Bearer $token")

        }
    }








    fun getCategoriesResponse() = getCategoriesLiveData
    fun getProductsByCateIdResponse() = getProductsByCateIdLiveData
    fun getProductsDetailsResponse() = getProductsDetailsLiveData
    fun getRelatedProductsResponse() = getRelatedProductsLiveData
    fun getRequestProductsResponse() = getRequestProductsLiveData
    fun getProductWishListResponse() = getProductWishListLiveData
    fun addToBagResponse() = addToBagLiveData
    fun getCustomerCartResponse() = getCustomerCartLiveData
    fun deleteFromCartResponse() = removeFromCartLiveData
    fun getBrandsResponse() = getBrandsLiveData
    fun getProductsByBrandIdResponse() = getProductsByBrandIdLiveData
    fun getSimilarItemsResponse() = getSimilarItemsLiveData


}