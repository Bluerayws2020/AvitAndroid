package com.bluerayws.avit.ViewModel

import androidx.lifecycle.*
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.dataclass.CategoriesMain
import com.bluerayws.avit.dataclass.ProductsHomeMain
import kotlinx.coroutines.launch


class CategoryViewModel(private val categoryRepo:CMainRepo):ViewModel (){

    private val getCategoriesLiveData= MutableLiveData<NetworkResults<CategoriesMain>>()
    private val getProductsByCateIdLiveData= MutableLiveData<NetworkResults<ProductsHomeMain>>()



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


    fun getCategoriesResponse() = getCategoriesLiveData
    fun getProductsByCateIdResponse() = getProductsByCateIdLiveData


}