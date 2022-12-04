package com.bluerayws.avit.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.bluerayws.avit.Api.ApiClient
import com.bluerayws.avit.adapters.ItemClicked
import com.bluerayws.avit.databinding.FragmentFavoriteBinding
import com.bluerayws.avit.dataclass.*
import com.bluerayws.avit.ui.activities.ProductActivity
import com.bluerayws.avit.ui.activities.SearchActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FavoriteFragment : Fragment(), ItemClicked {

    private var binding: FragmentFavoriteBinding? = null


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

        binding?.search?.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
        }

        val sharedPreferences : SharedPreferences?= activity?.getPreferences(Context.MODE_PRIVATE);

        val token = sharedPreferences?.getString("access_token", "defaultname")
//        Toast.makeText(getApplicationContext(), token.toString(), Toast.LENGTH_SHORT).show()
        Log.i("TAG", "token: " + token)

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




        val wishList = ArrayList<customer_wishlist>()

        val lang = "ar"
        val body = lang.toRequestBody("ar".toMediaTypeOrNull())

        val fav_list = ApiClient.retrofitService.getCustomerWishList(body)

        fav_list.enqueue(
            object : Callback<customer_wishlist> {



                override fun onResponse(
                    call: Call<customer_wishlist>,
                    response: Response<customer_wishlist>
                ) {

                Log.i("", "onResponse: ")
                if (response.body()?.status == true) {



                    response.body()!!.Product_Wishlist.iterator().forEach {
                        wishList.add( customer_wishlist(
                            response.body()!!.status, response.body()!!.errNum, response.body()!!.msg,
                        response.body()!!.Product_Wishlist) // add pd_id to product wishlist
                        )}

                    val lm = LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false)

//                    val adapter = ProductAdapter(wishList, this@FavoriteFragment)
//                    binding?.rvFavorite?.layoutManager = lm
//                    binding?.rvFavorite?.adapter = adapter

                    Log.i("TAG", "color length: " + fav_list.toString())   ///print length of the list

                }
                else
                {
//                        Log.d("pidd",response.body()!!.product_detail.pd_id)
                    Log.e(this.toString(), "onResponse: Lol" )
                }
                }

                override fun onFailure(call: Call<customer_wishlist>, t: Throwable) {
//                        Toast.makeText(this@FavoriteFragment, t.message, Toast.LENGTH_LONG).show()
                    Log.i("TAG", "onFailure: " + t.message)
                 }

            })

    }



    override fun onItemClicked() {
        startActivity(Intent(requireActivity(), ProductActivity::class.java))
    }



    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


}