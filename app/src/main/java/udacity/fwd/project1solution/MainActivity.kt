package udacity.fwd.project1solution

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import timber.log.Timber
import udacity.fwd.project1solution.databinding.ActivityMainBinding

/*
* I declare that I used both the following images from the following links.
* Because I do not know where to attribute both images, I added the attribution here.
*
*
* https://flyclipart.com/downloadpage/images/shoe-shop-pos-software-in-bangladesh-300405.png/300405
* https://www.svgrepo.com/download/196305/shoe.svg
*
* */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.graph_loginFragment,
                R.id.graph_welcomeFragment,
                R.id.graph_shoeListFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()//NavigationUI.navigateUp(navController,drawerLayout)
    }


}