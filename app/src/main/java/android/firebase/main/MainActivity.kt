package android.firebase.main

import android.content.Intent
import android.firebase.R
import android.firebase.auth.view.AuthActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()

        startActivity(Intent(this, AuthActivity::class.java))
    }

    private fun setupNavigation() {
        val navController = findNavController(this, R.id.nav_host_fragment)
        setupActionBarWithNavController(this, navController, drawerLayout)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
        setupWithNavController(navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(this, R.id.nav_host_fragment), drawerLayout)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
