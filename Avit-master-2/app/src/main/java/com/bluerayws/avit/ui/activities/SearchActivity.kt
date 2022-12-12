package com.bluerayws.avit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.FavoriteClick
import com.bluerayws.avit.adapters.SearchingOfCategoriesAdapter
import com.bluerayws.avit.adapters.SearchingOfProductsAdapter
import com.bluerayws.avit.databinding.ActivitySearchBinding
import com.bluerayws.avit.dataclass.CategoriesList
import com.bluerayws.avit.dataclass.ProductsOfSearching
import com.bluerayws.avit.ui.home.HomeFragment

class SearchActivity : AppCompatActivity(){  //,SearchView.OnQueryTextListener{

    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvSearch: RecyclerView
    private lateinit var adapter: SearchingOfProductsAdapter
    private lateinit var searchView: SearchView


    private lateinit var categoryVM : CategoryViewModel
    private val categoryRepo = CMainRepo
    private val language: String = "ar"
    private var productsList : List<ProductsOfSearching>? = null
    private var categoriesList : List<CategoriesList>? = null
    private var searchText: String = ""
    private var searchOfProductsAdapter: SearchingOfProductsAdapter?= null
    private var searchOfCategoriesAdapter: SearchingOfCategoriesAdapter?= null


    companion object{
        var ComeFromCategory = false
    }


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


        if(!ComeFromCategory) {

            let {
                searchingOfProductsApi()
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
        }

        else {
            let {
                searchingOfCategoriesApi()


                binding.searchBtn.setOnClickListener {
                    searchCategories(binding.searchEt.text.toString())
                }

                binding.searchEt.setOnEditorActionListener { v, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        searchProducts(v.text.toString())
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
            }
        }

//        categoryVM.searchOfCategories(language, name = )

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



    private fun searchingOfProductsApi(){

        val lm = GridLayoutManager(this, 2)
        val token = HomeActivity.tokenObj


        categoryVM.getProductsOfSearchingResponse().observe(this){ result ->
            when(result){
                is NetworkResults.Success -> {

                    productsList = result.data.products


                    binding.rvSearch.layoutManager = lm
                    searchOfProductsAdapter  = SearchingOfProductsAdapter(productsList!!, applicationContext, object : FavoriteClick{
                        override fun onItemClicked(position: Int) {
                            if (token == "") {
                                Toast.makeText(
                                    applicationContext,
                                    "You can't add items to your wish list, please register or login if you have an account!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {

                                Toast.makeText(
                                    applicationContext,
                                    result.data.msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                                categoryVM.getRequestProduct(
                                    language,
                                    result.data.products[position].id,
                                    "Bearer $token"
                                )
                            }
                        }

                    })
                    binding.rvSearch.adapter = searchOfProductsAdapter


                }

                is NetworkResults.Error -> {
                    Toast.makeText(applicationContext, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }

    }





    private fun searchCategories(searchTerm: String) {
        HelperUtils.hideKeyBoard(this)
        if (searchTerm.isNotEmpty()) {
            categoryVM.searchOfCategories(language, searchTerm)
        } else
            Toast.makeText(applicationContext, "empty", Toast.LENGTH_SHORT).show()
    }


    private fun searchingOfCategoriesApi(){

        val catLm = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val token = HomeActivity.tokenObj


        categoryVM.getCategoriesOfSearchingResponse().observe(this){ result ->
            when(result){
                is NetworkResults.Success -> {

                    categoriesList = result.data.CategoriesList


                    binding.rvSearch.layoutManager = catLm
                    searchOfCategoriesAdapter  = SearchingOfCategoriesAdapter(categoriesList!!, applicationContext) { it ->
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        intent.putExtra("category_id", it.id)
                        startActivity(intent)
                    }
                    binding.rvSearch.adapter = searchOfCategoriesAdapter


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