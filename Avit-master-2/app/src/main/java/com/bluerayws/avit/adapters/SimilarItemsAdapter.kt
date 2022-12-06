package com.bluerayws.avit.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ItemFavoriteBinding
import com.bluerayws.avit.dataclass.ProductWishlist
import com.bluerayws.avit.dataclass.SimilarItems
import com.bluerayws.avit.ui.activities.ProductActivity
import com.bumptech.glide.Glide

class SimilarItemsAdapter(private val list: List<SimilarItems>,
                          private val context: Context, private val flag_fav :Int, private val favoriteClick: FavoriteClick) :
    RecyclerView.Adapter<SimilarItemsAdapter.Holder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding =
                ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val data = list[position]


            if (flag_fav == 1) {
                holder.binding.favouriteClick.buttonDrawable =
                    ContextCompat.getDrawable(context,com.bluerayws.avit.R.drawable.ic_favorite_heart_selected)
            }


            holder.binding.productName.text = data.product_name_en
            holder.binding.productPrice.text = data.product_sale_price


        Glide.with(context)
            .load(data.image)
            .placeholder(R.drawable.img_artboard68)
            .error(R.drawable.img_artboard68)
            .into(holder.binding.imageView4)



            holder.binding.root.setOnClickListener {

                val intent = Intent(context, ProductActivity::class.java)
                intent.putExtra("product_id", data.product_id)
                context.startActivity(intent)
            }

            holder.binding.favouriteClick.setOnClickListener {
                favoriteClick.onItemClicked("ar", data.product_id)

            }


        }

        override fun getItemCount(): Int {
            return list.size
        }

        class Holder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)


    }