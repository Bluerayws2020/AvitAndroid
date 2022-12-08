package com.bluerayws.avit.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.bluerayws.avit.Helper.HelperUtils
import com.bluerayws.avit.R
import com.bluerayws.avit.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    companion object {
        var ComeFromLogin = false
        lateinit var tokenObj: String
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_bag) {
            navController.navigate(R.id.bagFragment)
        } else if (id == android.R.id.home) {
            navController.popBackStack()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)

        val sharedPreferences = getSharedPreferences(HelperUtils.SHARED_PREF, MODE_PRIVATE)

        binding.toolbar.setOnClickListener {
            binding.bottomNavView.selectedItemId = R.id.homeFragment

            sharedPreferences.edit().apply {
                putString("category_id", "0")
            }.apply()
        }

        // get category id from CategoryAdapter0.kt using putExtra
//        val cat_id = intent.getStringExtra("cat_id")
//        Log.i("TAG", "cat_id: " + cat_id)



        val token = sharedPreferences.getString("access_token", "defaultname")
        if (token != null) {
            tokenObj = token
        }
        Toast.makeText(applicationContext, token.toString(), Toast.LENGTH_SHORT).show()
        Log.i("TAG", "token: $token")
        Log.d("tokken", "onCreate: $tokenObj")

    }
}