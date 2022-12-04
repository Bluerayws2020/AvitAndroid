package com.bluerayws.avit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluerayws.avit.R
import com.bluerayws.avit.adapters.ProductAdapter
import com.bluerayws.avit.adapters.ItemClicked
import com.bluerayws.avit.databinding.ActivitySearchBinding
import com.bluerayws.avit.dataclass.TestClass

class SearchActivity : AppCompatActivity(), ItemClicked {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvSearch: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.searchToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        rvSearch = binding.rvSearch
        searchView = binding.searchView

        //search adapter
        let {

            val lm = GridLayoutManager(this, 2)
            rvSearch.layoutManager = lm
            val list = java.util.ArrayList<TestClass>()
            list.add(TestClass("name", R.drawable.ic_facebook_seq))
            list.add(TestClass("name", R.drawable.ic_snapchat))
            list.add(TestClass("name", R.drawable.ic_tiktok))
            list.add(TestClass("name", R.drawable.ic_whatsapp))
            list.add(TestClass("name", R.drawable.ic_instagram))
            list.add(TestClass("name", R.drawable.ic_youtube))
            list.add(TestClass("name", R.drawable.ic_linkedin))
            list.add(TestClass("name", R.drawable.ic_twitter_circle))
//            adapter = ProductAdapter(list, this)

            rvSearch.adapter = adapter

        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                rvSearch.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rvSearch.visibility = View.GONE
                return true
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked() {
        startActivity(Intent(this, ProductActivity::class.java))
    }
}