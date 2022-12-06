package com.bluerayws.avit.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemFavoriteBinding
import com.bluerayws.avit.dataclass.BrandItemsMain
import com.bluerayws.avit.dataclass.Categories
import com.bluerayws.avit.dataclass.ProductsDataMain
import com.bluerayws.avit.ui.activities.ProductActivity

class ProductsOfBrandAdapter(private val list: List<BrandItemsMain>, private val context: Context
, private val favClick: FavoriteClick
) :
    RecyclerView.Adapter<ProductsOfBrandAdapter.Holder>() {



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding =
                ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val data = list[position]
            holder.binding.productName.text = data.name_ar
        holder.binding.productPrice.text = data.sale_price


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
