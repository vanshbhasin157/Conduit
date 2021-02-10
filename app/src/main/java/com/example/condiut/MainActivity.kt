package com.example.condiut

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.condiut.databinding.ActivityMainBinding
import com.example.condiut.extensions.loadImage
import com.google.android.material.navigation.NavigationView
import models.entities.User

class MainActivity : AppCompatActivity() {

    companion object {
        const val AUTH_PREF = "auth_pref"
        const val AUTH_TOKEN = "auth_token"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_feed,
                R.id.nav_my_feed,
                R.id.nav_auth,
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        sharedPreferences.getString(AUTH_TOKEN, null)?.let { t ->
            authViewModel.getCurrentUser(t)
        }

        authViewModel.user.observe({ lifecycle }) {
            updateMenu(it)

            it?.token?.let { t ->
                sharedPreferences.edit {
                    putString(AUTH_TOKEN, t)
                    var userNameView = findViewById<TextView>(R.id.navHeaderUsername)
                    var email = findViewById<TextView>(R.id.navHeaderEmail)
                    var imageView = findViewById<ImageView>(R.id.navImageView)
                    imageView.loadImage(
                        it.image
                            ?: "https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png",
                        true
                    )

                    userNameView.text = it.username
                    email.text = it.email


                }
            } ?: kotlin.run {
                sharedPreferences.edit {
                    remove(AUTH_TOKEN)
                    var userNameView = findViewById<TextView>(R.id.navHeaderUsername)
                    var email = findViewById<TextView>(R.id.navHeaderEmail)
                    var imageView = findViewById<ImageView>(R.id.navImageView)
                    userNameView.text = "username"
                    email.text = "user@email.com"
                    imageView.loadImage("https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png", true)
                    Toast.makeText(this@MainActivity, "Logged Out", Toast.LENGTH_SHORT).show()
                }
            }
            navController.navigateUp()
        }
    }


    private fun updateMenu(user: User?) {
        when (user) {
            is User -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.menu_main_user)
            }
            else -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.menu_main_guest)

            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                authViewModel.logout()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}