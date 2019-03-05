package inc.grayherring.com.thedavidmedinashowapp.ui

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseActivity
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var poopLogRepository: PoopLogRepository
  private val navController by lazy { findNavController(R.id.my_nav_host_fragment) }
  private val bindings
      by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(bindings.toolbar)
    setupActionBarWithNavController(navController)
    setupWithNavController(bindings.navView, navController)
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

}
