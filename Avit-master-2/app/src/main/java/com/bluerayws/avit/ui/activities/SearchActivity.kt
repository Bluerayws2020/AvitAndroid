package com.bluerayws.avit.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.FavoriteClick
import com.bluerayws.avit.adapters.SearchingAdapter
import com.bluerayws.avit.databinding.ActivitySearchBinding
import com.bluerayws.avit.dataclass.ProductsOfSearching

class SearchActivity : AppCompatActivity(){  //,SearchView.OnQueryTextListener{

    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvSearch: RecyclerView
    private lateinit var adapter: SearchingAdapter
    private lateinit var searchView: SearchView


    private lateinit var categoryVM : CategoryViewModel
    private val categoryRepo = CMainRepo
    private val language: String = "ar"
    private var list : List<ProductsOfSearching>? = null
    private var searchText: String = ""
    private var searchingAdapter: SearchingAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryVM = ViewModelProvider(this, CategroyViewModelFactory(categoryRepo))[CategoryViewModel::class.java]


        setSupportActionBar(binding.searchToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        rvSearch = binding.rvSearch
//        searchView = binding.searchView

        //search adapter
        let {
            searchApi()
//            categoryVM.getProductsOfSearching(language, "E")


            binding.searchBtn.setOnClickListener {
                searchProducts(binding.searchEt.text.toString())
            }

            binding.searchEt.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchProducts(v.text.toString())
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }


        }

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                rvSearch.visibility = View.VISIBLE
//                return true
//            }

//            override fun onQueryTextChange(newText: String?): Boolean {
//                rvSearch.visibility = View.GONE
//                return true
//            }
//        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    private fun searchProducts(searchTerm: String) {
        HelperUtils.hideKeyBoard(this)
        if (searchTerm.isNotEmpty()) {
//            binding.messageSearch.hide()
//            binding.progressBarSearch.show()
            categoryVM.getProductsOfSearching(language, searchTerm)
        } else
            Toast.makeText(applicationContext, "empty", Toast.LENGTH_SHORT).show()
    }

    private fun searchApi(){

        val lm = GridLayoutManager(this, 2)
        val token = HomeActivity.tokenObj


        categoryVM.getProductsOfSearchingResponse().observe(this){ result ->
            when(result){
                is NetworkResults.Success -> {

                    list = result.data.products


                    binding.rvSearch.layoutManager = lm
                    searchingAdapter  = SearchingAdapter(list!!, applicationContext, object : FavoriteClick{
                        override fun onItemClicked(position: Int) {
                            Toast.makeText(applicationContext, result.data.msg, Toast.LENGTH_SHORT).show()
                            categoryVM.getRequestProduct(language, result.data.products[position].id, "Bearer $token")
                        }

                    })
                    binding.rvSearch.adapter = searchingAdapter


                }

                is NetworkResults.Error -> {
                    Toast.makeText(applicationContext, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }

    }

//    override fun onQueryTextSubmit(p0: String?): Boolean {
//        return false
//    }
//
//    override fun onQueryTextChange(p0: String?): Boolean {
//        val text: String? = p0
////        searchingAdapter (text)
//        return false
//    }
}