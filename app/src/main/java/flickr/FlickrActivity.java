package flickr;

import android.app.SearchManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.ramap.R;

/**
 * Created by Joe's Desktop on 12/8/2014.
 */
public class FlickrActivity extends SingleFragmentActivity{
    private static final String TAG = "PhotoGalleryActivity";

    @Override
    public Fragment createFragment() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onNewIntent(Intent intent) {

        PhotoGalleryFragment fragment = (PhotoGalleryFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            Log.i(TAG, "!!!!!!!! PhotoGalleryActivity, ---> Received a new search query: " + query);

            // Use SharedPreference to save the query. This SharedPreference is shared within this whole app.
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putString(FlickrFetchr.PREF_SEARCH_QUERY, query)
                    .commit();
        }

        fragment.updateItems();
        //search items.
    }
}
