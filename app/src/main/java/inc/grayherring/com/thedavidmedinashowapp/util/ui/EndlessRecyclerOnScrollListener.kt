package inc.grayherring.com.thedavidmedinashowapp.util.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



// tinker this later tbh i dont think this need to be complex @ all i want to know when im @ the top or the bottem of the lsit
abstract class EndlessRecyclerOnScrollListener(private val linearLayoutManager: LinearLayoutManager) :
  RecyclerView.OnScrollListener() {

  private var previousTotal = 0 // The total number of items in the dataset after the last load
  private var loading = true // True if we are still waiting for the last set of data to load.
  private var firstVisibleItem: Int = 0
  private var visibleItemCount: Int = 0
  private var totalItemCount: Int = 0

  private var current_page = 1

  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    super.onScrolled(recyclerView, dx, dy)

    visibleItemCount = recyclerView.childCount
    totalItemCount = linearLayoutManager.itemCount
    firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false
        previousTotal = totalItemCount
      }
    }
    if (!loading) {
      // End has been reached

      // Do something
      current_page++

      onLoadMore(current_page)

      loading = true
    }
  }

  abstract fun onLoadMore(current_page: Int)

}

fun endlessScrollListener(linearLayoutManager: LinearLayoutManager, onLoadMore: (Int) -> Unit) =
  object : EndlessRecyclerOnScrollListener(linearLayoutManager) {
    override fun onLoadMore(current_page: Int) {
      onLoadMore(current_page)
    }

  }

