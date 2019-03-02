package inc.grayherring.com.thedavidmedinashowapp.ui

import android.os.Bundle

import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseActivity
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject
import androidx.navigation.ui.NavigationUI.navigateUp

class MainActivity : BaseActivity(){

    @Inject
    lateinit var poopLogRepository: PoopLogRepository
    private val navController by lazy { findNavController(R.id.my_nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
        setupWithNavController(nav_view, navController)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navigateUp(navController, drawer_layout)

}
