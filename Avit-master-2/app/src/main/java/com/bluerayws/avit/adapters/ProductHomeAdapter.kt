package com.bluerayws.avit.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.databinding.ItemFavoriteBinding
import com.bluerayws.avit.dataclass.ProductsDataMain
import com.bluerayws.avit.dataclass.ProductsHomeMain
import com.bluerayws.avit.dataclass.customer_wishlist_request
import com.bluerayws.avit.ui.activities.ProductActivity
import com.bluerayws.avit.ui.login.StartActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductHomeAdapter(private val list: List<ProductsDataMain>, private val context: Context, private val favClick: FavoriteClick) :
    RecyclerView.Adapter<ProductHomeAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.binding.productName.text = data.name_ar
//        holder.binding.productPrice.text = data.name_ar


        holder.binding.favouriteClick.setOnClickListener{
            favClick.onItemClicked("ar", data.id)
//            data.wishlist = 1
        }
        Log.d("WIsh List: ", "onBindViewHolder: " + data.wishlist)

        if (data.wishlist == 1) {

            holder.binding.favouriteClick.buttonDrawable =
                ContextCompat.getDrawable(
                    context,
                    com.bluerayws.avit.R.drawable.ic_favorite_heart_selected
                )
            Log.d("dataWishlist", data.wishlist.toString())
        } else {
            holder.binding.favouriteClick.buttonDrawable =
                ContextCompat.getDrawable(
                    context,
                    com.bluerayws.avit.R.drawable.ic_favorite_heart_un_selected
                )
            Log.d("NotDataWishlist", data.wishlist.toString())

        }

        holder.binding.textView11.text = data.wishlist.toString()


        holder.binding.root.setOnClickListener {
//            click.onItemClicked()

            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("product_id", data.id)
            context.startActivity(intent)

//            notifyDataSetChanged()

            Toast.makeText(context, data.id, Toast.LENGTH_SHORT).show()
            Log.i("TAG", "TITLE " + data.id)

        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class Holder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}





//val _lang = "ar"
//val lang = _lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//
//val preferences =
//    context.getSharedPreferences(
//        HelperUtils.SHARED_PREF,
//        Context.MODE_PRIVATE
//    )
//
//val token = preferences?.getString("access_token", "access_token")
//Log.i("ac click", "tokken: " + token)
//
//val pro_id = data.id
//Log.i("fac click", "pro_id: " +  pro_id)
//val prod_num = pro_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//
//
//let {
//    val _lang = "ar"
//    val lang = _lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//
//    val preferences =
//        context.getSharedPreferences(
//            HelperUtils.SHARED_PREF,
//            Context.MODE_PRIVATE
//        )
//
//    val token = preferences?.getString("access_token", "access_token")
//    Log.i("TAG", "tokken: " + token)
//
//    val pro_id = data.id
//    Log.i("TAG", "pro_id: " +  pro_id)
//    val prod_num = pro_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//
//
//    Log.i("TAG", "fav_pprod: " + pro_id + " tokenn " + token)
//
//
//    val wishList_request = ApiInstanse.retrofitService.customerWishListRequest(
//        "Bearer $token", lang,
//        prod_num
//    )
//
//    Log.i("TAG", "outside favClick: ")
//
//    holder.binding.favouriteClick.setOnClickListener{
//        Log.i("TAG", "inside favClick: ")
//
//        if( token == "access_token" ){
//            val intent = Intent(context, StartActivity::class.java)
//            Toast.makeText(context, "Please login if you have an account or Register!", Toast.LENGTH_SHORT).show()
//            context.startActivity(intent)
//
//
//
//        }
//        else {
//            wishList_request.clone().enqueue(
//                object : Callback<customer_wishlist_request> {
//                    override fun onResponse(
//                        call: Call<customer_wishlist_request>,
//                        response: Response<customer_wishlist_request>
//                    ) {
//                        if (response.body()?.status == true) {
//                            Log.i("TAG", "added: " + response.body()!!.msg)
//
//                            if (response.body()?.msg?.contains("removed") == true) {
//                                data.wishlist = 2
//                                Log.i("REMOVED", "onResponse: " + data.wishlist)
//
//                                holder.binding.textView11.text = data.wishlist.toString()
//
//                                holder.binding.favouriteClick.buttonDrawable =
//                                    ContextCompat.getDrawable(
//                                        context,
//                                        com.bluerayws.avit.R.drawable.ic_favorite_heart_un_selected
//                                    )
//
//                                Log.i("TAG", "sure un selected: " + data.wishlist)
//
//                            }
//
////                                    Log.i("msg", "onResponse: " + response.body()?.msg)
//                            else if (response.body()?.msg?.contains("added") == true){
//                                data.wishlist = 1
//
//                                holder.binding.textView11.text = data.wishlist.toString()
//
//                                Log.i("ADDED", "onResponse: " + data.wishlist)
//
//                                holder.binding.favouriteClick.buttonDrawable =
//                                    ContextCompat.getDrawable(
//                                        context,
//                                        com.bluerayws.avit.R.drawable.ic_favorite_heart_selected
//                                    )
//
//                                Log.i("TAG", "sure selected: " + data.wishlist)
//
//                            }
//
//                            Toast.makeText(
//                                context,
//                                response.body()!!.msg,
//                                Toast.LENGTH_SHORT
//                            ).show()
////                                    data.wishlist = data.wishlist.dec()
////                                    Log.i("OUT", "onResponse: " + data.wishlist)
//
//                        }
//                    }
//
//                    override fun onFailure(
//                        call: Call<customer_wishlist_request>,
//                        t: Throwable
//                    ) {
//                        Log.i("TAG", "onFailure: " + t.message)
//                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            )
//        }
//    }
//    Log.i("OUT", "onResponse: " + data.wishlist)
//
//}
//
//
//Log.i("dec", "onBindViewHolder: " + data.wishlist)
