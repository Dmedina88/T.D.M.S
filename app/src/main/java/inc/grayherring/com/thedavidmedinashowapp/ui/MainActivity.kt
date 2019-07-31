package inc.grayherring.com.thedavidmedinashowapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseActivity
import inc.grayherring.com.thedavidmedinashowapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), OnNavigationItemSelectedListener {

  private val navController by lazy { findNavController(R.id.my_nav_host_fragment) }
  private val bindings by lazy {
    DataBindingUtil.setContentView<ActivityMainBinding>(
      this,
      R.layout.activity_main
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(bindings.toolbar)
    setupWithNavController(bindings.toolbar, navController, bindings.drawerLayout)
    setupWithNavController(bindings.navView, navController)
    bindings.navView.setNavigationItemSelectedListener(this)
   // bindings.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

  }

  override fun onBackPressed() {
    if (bindings.drawerLayout.isDrawerOpen(GravityCompat.START)) {
      bindings.drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onSupportNavigateUp(): Boolean =
    navigateUp(navController, bindings.drawerLayout)

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when {
      item.itemId == R.id.nav_license -> {
        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
      }
      else -> item.onNavDestinationSelected(navController)
    }
    bindings.drawerLayout.closeDrawer(GravityCompat.START)
    return true
  }

}
