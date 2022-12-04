package com.bluerayws.avit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.databinding.ItemSocialMediaBinding
import com.bluerayws.avit.dataclass.TestClass
import com.squareup.picasso.Picasso

class SocialMediaAdapter(val list: ArrayList<TestClass>, val context: Context) :
    RecyclerView.Adapter<SocialMediaAdapter.Holder>() {
    class Holder(val binding: ItemSocialMediaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemSocialMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
//        holder.binding.image.setImageDrawable(context.getDrawable(data.phone))
        Picasso.get().load(data.phone).placeholder(data.phone).into(holder.binding.image)

        //      Picasso.get().load("https://www.google.com/imgres?imgurl=https%3A%2F%2Fim0-tub-ru.yandex.net%2Fi%3Fid%3D84dbd50839c3d640ebfc0de20994c30d%26n%3D27%26h%3D480%26w%3D480&imgrefurl=https%3A%2F%2Fm.yandex.com%2Fimages%2F&tbnid=PPawKcBaPexwaM&vet=12ahUKEwjgo7fpsIb0AhVSNRoKHZiVBqEQMygKegUIARDgAQ..i&docid=r0-PMNxspQFYEM&w=480&h=480&q=image&ved=2ahUKEwjgo7fpsIb0AhVSNRoKHZiVBqEQMygKegUIARDgAQ").into(holder.binding.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}