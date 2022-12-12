package com.bluerayws.avit.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.bluerayws.avit.Api.ApiClient
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.*
import com.bluerayws.avit.databinding.FragmentFavoriteBinding
import com.bluerayws.avit.dataclass.*
import com.bluerayws.avit.ui.activities.HomeActivity
import com.bluerayws.avit.ui.activities.ProductActivity
import com.bluerayws.avit.ui.activities.SearchActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.FieldPosition
import java.util.*

class FavoriteFragment : Fragment(), ItemClicked {

    private var binding: FragmentFavoriteBinding? = null
    private lateinit var categoryVM : CategoryViewModel
    private val categoryRepo = CMainRepo
    private val language: String = "ar"
//    private var homeList: List<ProductsDataMain>? = null
    private var wishList : List<ProductWishlist> ?= null
    private var similarList : List<SimilarItems> ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryVM = ViewModelProvider(this, CategroyViewModelFactory(categoryRepo))[CategoryViewModel::class.java]

//        binding?.search?.setOnClickListener {
//            startActivity(Intent(requireActivity(), SearchActivity::class.java))
//        }


        val token = HomeActivity.tokenObj
        getProductWishListResponse()
        removeProductToWishList()
        getSimilarItemsApi()



        categoryVM.getProductWishList(language, "Bearer $token")
        categoryVM.getSimilarItems(language, "Bearer $token")


    }



    override fun onItemClicked() {
        startActivity(Intent(requireActivity(), ProductActivity::class.java))
    }



    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


//    getProductWishListResponse

    private fun getProductWishListResponse(){

        val lm = GridLayoutManager(requireContext(), 2)

        categoryVM.getProductWishListResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {

                    val token = HomeActivity.tokenObj

                    wishList = result.data.Product_Wishlist
                    binding?.rvFavorite?.layoutManager = lm
                    val productAdapter = ProductAdapter(wishList!!, requireContext(), 1, object : FavoriteClick{
                        override fun onItemClicked(position: Int) {
                            if(token == ""){
                                Toast.makeText(requireContext(),
                                    "You can't add items to your wish list, please register or login if you have an account!",
                                    Toast.LENGTH_SHORT).show()
                            }
                            else {
                                categoryVM.getRequestProduct(
                                    language,
                                    result.data.Product_Wishlist[position].product_id,
                                    "Bearer $token"
                                )
                            }
                        }

                    })
                    binding?.rvFavorite?.adapter = productAdapter

                }
                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }


    private fun removeProductToWishList(){


        val token = HomeActivity.tokenObj

        categoryVM.getRequestProductsResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {

                    if(token == ""){
                        Toast.makeText(requireContext(), "You can't add items to your wish list, please register or login if you have an account!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(requireContext(), result.data.msg, Toast.LENGTH_SHORT).show()
                    }
                }

                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }
//    getSimilarItemsResponse

    private fun getSimilarItemsApi(){

        val lm = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        categoryVM.getSimilarItemsResponse().observe(viewLifecycleOwner){ result ->
            when(result){
                is NetworkResults.Success -> {

                    val token = HomeActivity.tokenObj

                    similarList = result.data.products_data
                    binding?.rvSimilarProducts?.layoutManager = lm
                    val similarItemsAdapter = SimilarItemsAdapter(similarList!!, requireContext(), object : FavoriteClick{
                        override fun onItemClicked(position: Int) {
                            if(token == ""){
                                Toast.makeText(requireContext(), "You can't add items to your wish list, please register or login if you have an account!", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                categoryVM.getRequestProduct(
                                    language,
                                    result.data.products_data[position].product_id,
                                    "Bearer $token"
                                )
                            }
                        }

                    })
                    binding?.rvSimilarProducts?.adapter = similarItemsAdapter

                }
                is NetworkResults.Error -> {
                    Toast.makeText(context, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }
    }


}



















//        val lm = GridLayoutManager(activity, 2)
//        val data = TestClass("name", 787663762)
//        val list = ArrayList<TestClass>()
//        list.add(TestClass("name", 787663762))
//        list.add(TestClass("name", 787663762))
//        list.add(TestClass("name", 787663762))
//        list.add(TestClass("name", 787663762))
//        list.add(TestClass("name", 787663762))
//
//
//        val adapter = ProductAdapter(list, this)
//        binding?.rvFavorite?.layoutManager = lm
//
//        binding?.rvFavorite?.adapter = adapter





//        val lang = "ar"
//        val body = lang.toRequestBody("ar".toMediaTypeOrNull())
//
//        val fav_list = ApiClient.retrofitService.getCustomerWishList(body)
//
//        fav_list.enqueue(
//            object : Callback<customer_wishlist> {
//
//
//
//                override fun onResponse(
//                    call: Call<customer_wishlist>,
//                    response: Response<customer_wishlist>
//                ) {
//
//                Log.i("", "onResponse: ")
//                if (response.body()?.status == true) {
//
//
//
//                    response.body()!!.Product_Wishlist.iterator().forEach {
//                        wishList.add( customer_wishlist(
//                            response.body()!!.status, response.body()!!.errNum, response.body()!!.msg,
//                        response.body()!!.Product_Wishlist) // add pd_id to product wishlist
//                        )}
//
//                    val lm = LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false)
//
////                    val adapter = ProductAdapter(wishList, this@FavoriteFragment)
////                    binding?.rvFavorite?.layoutManager = lm
////                    binding?.rvFavorite?.adapter = adapter
//
//                    Log.i("TAG", "color length: " + fav_list.toString())   ///print length of the list
//
//                }
//                else
//                {
////                        Log.d("pidd",response.body()!!.product_detail.pd_id)
//                    Log.e(this.toString(), "onResponse: Lol" )
//                }
//                }
//
//                override fun onFailure(call: Call<customer_wishlist>, t: Throwable) {
////                        Toast.makeText(this@FavoriteFragment, t.message, Toast.LENGTH_LONG).show()
//                    Log.i("TAG", "onFailure: " + t.message)
//                 }
//
//            })