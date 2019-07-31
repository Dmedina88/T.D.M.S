package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


data class SimplePagerItem(val fragment: Fragment, @StringRes val title: Int)

 class SimplePagerAdapter(fragmentManager: FragmentManager, val context: Context,val data : List<SimplePagerItem>) :
  FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  override fun getItem(position: Int): Fragment =
    data[position].fragment

  override fun getCount() = data.size

  override fun getPageTitle(position: Int): CharSequence? =
    context.getString(data[position].title)

}
