package com.bluerayws.avit.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bluerayws.avit.Repo.CMainRepo

class CategroyViewModelFactory (private val cat_arr_repo:CMainRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return CategoryViewModel(cat_arr_repo) as T
    }



}