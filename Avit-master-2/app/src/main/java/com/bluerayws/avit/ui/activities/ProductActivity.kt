package com.bluerayws.avit.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Api.ApiClient
import com.bluerayws.avit.R
import com.bluerayws.avit.adapters.ColorAdapter
import com.bluerayws.avit.adapters.SimilarItemAdapter
import com.bluerayws.avit.adapters.SizeAdapter
import com.bluerayws.avit.databinding.ActivityProductBinding
import retrofit2.Callback
import com.bluerayws.avit.dataclass.*
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Response


class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var imageSlider: ImageSlider
    private lateinit var rvColor: RecyclerView
    private lateinit var rvSize: RecyclerView
    private lateinit var rvSimilar: RecyclerView
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var similarAdapter: SimilarItemAdapter
    private lateinit var sizeAdapter: SizeAdapter
    val sizeList = ArrayList<sizes_main>()
    val colorList = ArrayList<colors_main>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)

        setContentView(binding.root)

        rvColor = binding.rvColor
        rvSize = binding.rvSize
        rvSimilar = binding.rvSimilar

        setSupportActionBar(binding.productToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val lang = "ar"
//        val proNum = "1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484"
//        val body = lang.toRequestBody("ar".toMediaTypeOrNull())
//        val prod_num = proNum.toRequestBody("1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484".toMediaTypeOrNull())
//
//
//        val args = Bundle()
//        args.putString("product_number", "1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484")
//
//
//
//
//
//        //  4. get sizes from putString
//        val list = ArrayList<Product_Main>()



        //Slider Image
        let {
            imageSlider = binding.imageSlider
            val imageList = ArrayList<SlideModel>() // Create image list
            imageList.add(SlideModel(R.drawable.img_artboard68))
            imageList.add(SlideModel(R.drawable.img_artboard69))
            imageList.add(SlideModel(R.drawable.img_artboard70))
            imageList.add(SlideModel(R.drawable.img_artboard72))
            imageList.add(SlideModel(R.drawable.img_artboard73))

            imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
        }


        //COLOR Adapter
        let {

            val lang = "ar"
            val proNum = "1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484"
            val body = lang.toRequestBody("ar".toMediaTypeOrNull())
            val prod_num = proNum.toRequestBody("1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484".toMediaTypeOrNull())


            val args = Bundle()
            args.putString("product_number", "1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484")


//            val list = ArrayList<Colors>()

            val service_color = ApiClient.retrofitService.getProductDetails(body, prod_num)

            service_color.enqueue(
                object : Callback<ProductDetails_main> {


                    override fun onFailure(call: Call<ProductDetails_main>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<ProductDetails_main>,
                        response: Response<ProductDetails_main>)

                    {
//                    Log.d("pid",response.body()!!.product_detail.pd_id)

                        Log.i("", "onResponse: ")
                        if (response.body()?.status == true) {



                        response.body()!!.product_detail.Colors.iterator().forEach {
                            colorList.add(colors_main(it.col_id, it.color_name_ar, it.color_name_en))
                        }

                            val lm = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.VERTICAL, false)

                            colorAdapter = ColorAdapter(colorList)
                            rvColor.layoutManager = lm
                            rvColor.adapter = colorAdapter

                            Log.i("TAG", "color length: " + colorList.size)

                        }
                        else
                        {
//                        Log.d("pidd",response.body()!!.product_detail.pd_id)
                            Log.e(this.toString(), "onResponse: Lol" )
                        }
                    }

                })




//            list.add(Colors("#FFBB86FC"))
//            list.add(Colors("#fce205"))
//            list.add(Colors("#C80000"))
//            list.add(Colors("#0000C8"))
//            list.add(Colors("#484947"))
//            colorAdapter = ColorAdapter(list)
//            rvColor.layoutManager = lm
//            rvColor.adapter = colorAdapter

        }

        //Size Adapter

        let {


            // 1.  init retrofit

            // 2. retrofit Enqueue
            // 3. convert lang and pId to request body from String

            val lang = "ar"
            val proNum = "1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484"
            val body = lang.toRequestBody("ar".toMediaTypeOrNull())
            val prod_num = proNum.toRequestBody("1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484".toMediaTypeOrNull())


            val args = Bundle()
            args.putString("product_number", "1-1---1-1-1---1--1~0557A844-66AF-4E73-83C3-7171203C6484")


            val service_size =
                ApiClient.retrofitService
                    .getProductDetails(body, prod_num)


            service_size.enqueue(
            object : Callback<ProductDetails_main> {


                override fun onFailure(call: Call<ProductDetails_main>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ProductDetails_main>,
                    response: Response<ProductDetails_main>)

                {
//                    Log.d("pid",response.body()!!.product_detail.pd_id)

                    Log.i("", "onResponse: ")
                    if (response.body()?.status == true) {

                        Log.d("pid",response.body()!!.product_detail.pd_id)

                binding.tvProductName.text =  response.body()!!.product_detail.pname
                binding.tvPrice.text =  response.body()!!.product_detail.sale_price
                binding.tvItemDescription.text =  response.body()!!.product_detail.description_en
                binding.tvProductName2.text =  response.body()!!.product_detail.description_en

                    response.body()!!.product_detail.Sizes.iterator().forEach {
                            sizeList.add(sizes_main(it.sz_id,it.size_name_ar,it.size_name_en))
                        }



                        val lm = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.HORIZONTAL, false)

                        sizeAdapter = SizeAdapter(sizeList)
                        rvSize.layoutManager = lm
                        rvSize.adapter = sizeAdapter



                        Log.i("TAG", "Size length: " + sizeList.size)

                    }
                    else
                    {
//                        Log.d("pidd",response.body()!!.product_detail.pd_id)
                        Log.e(this.toString(), "onResponse: Lol" )
                    }
                }

            })



        }

        //Size RadioButton
        let {
            binding.radioUS.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.radioUS.setTextColor(Color.WHITE)
                } else {
                    binding.radioUS.setTextColor(Color.BLACK)
                }
            }
            binding.radioEU.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.radioEU.setTextColor(Color.WHITE)
                } else {
                    binding.radioEU.setTextColor(Color.BLACK)
                }
            }
            binding.radioUK.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.radioUK.setTextColor(Color.WHITE)
                } else {
                    binding.radioUK.setTextColor(Color.BLACK)
                }
            }
        }
        //similar Adapter
        let {
            val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            rvSimilar.layoutManager = lm
            val list = java.util.ArrayList<TestClass>()
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            list.add(TestClass("name", 787663762))
            similarAdapter = SimilarItemAdapter(list)
            rvSimilar.adapter = similarAdapter

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}


//                        for (siz in sizeList.size.toString()) {
//                            sizeList.add(
//                                sizes_main(
//                                    response.body()!!.product_detail.sizes[siz.code].sz_id,
//                                    response.body()!!.product_detail.sizes[siz.code].size_name_en,
//                                    response.body()!!.product_detail.sizes.get(siz.code).size_name_ar
//                                )
//                            )
//
//                        }


//                        for (siz in sizeAdapter.list.iterator().) {
//                            sizeList.add(
//                                sizes_main(
//                                    response.body()!!.product_detail.sizes[siz.code].sz_id,
//                                    response.body()!!.product_detail.sizes[siz.code].size_name_en,
//                                    response.body()!!.product_detail.sizes.get(siz.code).size_name_ar
//                                )
//                            )
//
//                        }

//                        it.sizeAdapter.list.iterator().forEach {
//                            sizeList.add(sizes_main(it.sz_id, it.size_name_ar, it.size_name_en))
////                            Log.i("TAG", "yooshi: " + response.body()!!.product_detail.pdSizes[0].sz_id)
//                        }

// add size which got it from api by putString
//            list.add()
//            list.add(Size("12"))
//            list.add(Size("16"))
//            list.add(Size("20"))
//            list.add(Size("24"))
//            list.add(Size("28"))
