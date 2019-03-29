package android.firebase.main

import android.firebase.R
import android.firebase.feature.list.view.ListsFragmentDirections
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(this, navController)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            Timber.e("sdffdg")
            navigate(menuItem)
        }
        setupWithNavController(navigationView, navController)
    }

    private fun navigate(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {

            R.id.menu_logout -> navigateToLogout()
        }

        menuItem.isChecked = true
        drawerLayout.closeDrawers()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(R.id.nav_host_fragment), drawerLayout)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun navigateToLogout() {
        //TODO param
        val action = ListsFragmentDirections.actionListsFragmentToAuthFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }
}
