package com.bluerayws.avit.ui.activities

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.Api.ApiClient
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.Repo.CMainRepo
import com.bluerayws.avit.Repo.NetworkResults
import com.bluerayws.avit.ViewModel.CategoryViewModel
import com.bluerayws.avit.ViewModel.CategroyViewModelFactory
import com.bluerayws.avit.adapters.*
import com.bluerayws.avit.databinding.ActivityProductBinding
import com.bluerayws.avit.dataclass.*
import com.denzcoskun.imageslider.ImageSlider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var imageSlider: ImageSlider
    private lateinit var rvColor: RecyclerView
    private lateinit var rvSize: RecyclerView
    private lateinit var rvSimilar: RecyclerView
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var similarAdapter: RelatedListAdapter
    private lateinit var sizeAdapter: SizeAdapter
    private var sizeList: List<Sizes>? = null
    private var colorList: List<Colors>? = null
    var relatedProductList : List<RelatedProductsItems>? = null


    private var adapter: ProductAdapter? = null
    private lateinit var categoryVM : CategoryViewModel
    private val categoryRepo = CMainRepo
    private val language: String = "ar"
    private var homeList: List<ProductsDataMain>? = null


    private var colorId: String ?= null
    private var sizeId: String ?= null


//    Log.d("token home: 2", "onViewCreated: $token")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        categoryVM = ViewModelProvider(this, CategroyViewModelFactory(categoryRepo))[CategoryViewModel::class.java]

        setContentView(binding.root)

        rvColor = binding.rvColor
        rvSize = binding.rvSize
        rvSimilar = binding.rvSimilar

        setSupportActionBar(binding.productToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        addToBag()

        let {

            val sharedPreferences =
                applicationContext?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)

            val token = sharedPreferences?.getString("access_token", "")
            val productId = intent.getStringExtra("product_id")

            getProductsDetailsById()
            categoryVM.getProductsDetails(language, productId.toString())

            binding.favouriteClick.setOnClickListener {
                if (token == "") {
                    Toast.makeText(
                        applicationContext,
                        "You can't add items to your wish list, please register or login if you have an account!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    categoryVM.getRequestProduct(language, productId.toString(), "Bearer $token")
                }
            }
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
            val productId = intent.getStringExtra("product_id")

            // way 1
//            getRelatedProductsApi()
//            categoryVM.getRelatedProduct(Product(language, listOf(productId!!)))

            // way 2
            similarListApi()


        }


        val sharedPreferences =
            applicationContext?.getSharedPreferences(HelperUtils.SHARED_PREF, Context.MODE_PRIVATE)

        val token = sharedPreferences?.getString("access_token", "")
        val productId = intent.getStringExtra("product_id")
        val deviceId = HelperUtils.getAndroidID(applicationContext)


        if (token == ""){
            addToBag()
        }


        binding.btnAddItem.setOnClickListener{


            if(colorId == null && sizeId == null){
                Toast.makeText(applicationContext, "You have to choose the color and the size you want", Toast.LENGTH_SHORT).show()
            }
            else  if(colorId == null){
                Toast.makeText(applicationContext, "You have to choose the color you want", Toast.LENGTH_SHORT).show()
            }
            else if(sizeId == null){
                Toast.makeText(applicationContext, "You have to choose the size you want", Toast.LENGTH_SHORT).show()
            }
            else if(token == ""){
                categoryVM.addToGuestCart(language, productId.toString(),"1", colorId.toString(), sizeId.toString(), deviceId)
            }
            else{
                categoryVM.addToBag(language, productId.toString(),"1", colorId.toString(), sizeId.toString(),"Bearer $token")
            }

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    private fun getRelatedProductsApi(){

        val relatedLM = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.HORIZONTAL, false)

        var relatedList : List<RelatedProductsItems>?= null


        categoryVM.getRelatedProductsResponse().observe(this){ result ->
            when(result){
                is NetworkResults.Success -> {

                    relatedList = result.data.related_products
                    Log.d("Related List:  ", "getRelatedProductsApi: " + relatedList.toString())

                    binding.rvSimilar.layoutManager = relatedLM
//                    val similarAdapter = SimilarItemAdapter(relatedList!!, applicationContext)
                    binding.rvSimilar.adapter = similarAdapter


                }

                is NetworkResults.Error -> {
                    Toast.makeText(applicationContext, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }


    }



    private fun getProductsDetailsById(){

        val sizeLM = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.HORIZONTAL, false)
        val colorLM = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.HORIZONTAL, false)
        categoryVM.getProductsDetailsResponse().observe(this){ result ->
            when(result){
                is NetworkResults.Success -> {

                    sizeList = result.data.product_details.sizes
                    colorList = result.data.product_details.colors

                    binding.rvSize.layoutManager = sizeLM
                    sizeAdapter = SizeAdapter(sizeList!!, applicationContext, object : AddressClicked{
                        override fun onClick(position: Int) {
                            sizeId = sizeList!![position].id.toString()
                        }

                    })
                    binding.rvSize.adapter = sizeAdapter

                    binding.tvProductName.text = result.data.product_details.name_ar
                    binding.tvItemDescription.text = result.data.product_details.description_ar
                    binding.tvPrice.text = result.data.product_details.sale_price

                    colorList = result.data.product_details.colors
                    binding.rvColor.layoutManager = colorLM
                    colorAdapter = ColorAdapter(colorList!!, applicationContext, object : AddressClicked{
                        override fun onClick(position: Int) {
                            colorId = colorList!![position].id.toString()
                        }

                    })

                    binding.rvColor.adapter = colorAdapter

                    binding.tvDelivary.text = result.data.product_details.design_id.toString()

                    binding.tvProductName2.text = result.data.product_details.name_en
                    binding.tvItemDescription2.text = result.data.product_details.description_ar
                    binding.tvProductCode.text = result.data.product_details.currency_code


                    binding.tvFabric.text = result.data.product_details.material_en
                    binding.tvSizeDetails.text = result.data.product_details.design_id

//                    if(result.data.product_details.quantity_available == "0"){
//                        Toast.makeText(applicationContext, "You can't buy this item now, because the quantity is over", Toast.LENGTH_SHORT).show()
//                    }


//                    Glide.with(applicationContext)
//                        .load(result.data.product_details.image)
//                        .placeholder(R.drawable.img_artboard68)
//                        .error(R.drawable.img_artboard68)
//                        .into(binding.imageSlider)

//                    val imageList = ArrayList<SlideModel>()
////                    imageList.addAll(result.data.product_details.product_images)
//                    imageList.add(SlideModel(result.data.product_details.image, "lol"))
//
////                    imageList.add(SlideModel(result.data.product_details.product_images[0],"pp"))
////                    imageList.add(SlideModel(result.data.product_details.product_images[1], "oo"))
////                    imageList.add(SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct."))
////                    imageList.add(SlideModel("https://bit.ly/3fLJf72", "And people do that."))
//
////                    val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
////                    imageSlider.setImageList(imageList)
//                    binding.imageSlider.setImageList(imageList)

                }

                is NetworkResults.Error -> {
                    Toast.makeText(applicationContext, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }


    }


    private fun addToBag(){

        categoryVM.addToBagResponse().observe(this){ result ->
            when(result){
                is NetworkResults.Success -> {

                    Toast.makeText(applicationContext, result.data.msg, Toast.LENGTH_SHORT).show()
                }

                is NetworkResults.Error -> {
                    Toast.makeText(applicationContext, result.exception.toString(), Toast.LENGTH_LONG).show()
                    result.exception.printStackTrace()
                }
            }
        }


    }


    private fun similarListApi(){

        val productId = intent.getStringExtra("product_id")

        val relatedProducts =
            ApiClient.retrofitService
                .getRelatedProducts((Product(language, listOf(productId!!))))

        val token = HomeActivity.tokenObj

        relatedProducts.enqueue(

            object : Callback<RelatedProducts> {
                override fun onResponse(
                    call: Call<RelatedProducts>,
                    response: Response<RelatedProducts>
                ) {


                    if (response.body()?.status == true) {

                        Log.d("List: ", "onResponse: " + response.body()!!.related_products)

                        relatedProductList = response.body()!!.related_products

                        Log.d("List: ", "onResponse: " + (relatedProductList?.size))


                        val lm = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.HORIZONTAL, false)
                        rvSimilar.layoutManager = lm
                        similarAdapter = RelatedListAdapter(relatedProductList!!, applicationContext, object : FavoriteClick{
                            override fun onItemClicked(position: Int) {
                                if(token == ""){
                                    Toast.makeText(applicationContext,
                                        "You can't add items to your wish list, please register or login if you have an account!",
                                        Toast.LENGTH_SHORT).show()
                                }
                                else{
                                categoryVM.getRequestProduct(language, response.body()!!.related_products[position].id, "Bearer $token")
                            }
                            }

                        })
                        rvSimilar.adapter = similarAdapter

                        Log.i("TAG", "related length: " + relatedProductList!!.size)

                    }
                    else
                    {
                        Log.e(this.toString(), "onResponse:")
                    }
                }

                override fun onFailure(call: Call<RelatedProducts>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                }

            }


        )
    }


}
