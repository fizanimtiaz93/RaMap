package tabs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.ramap.R;

import java.util.ArrayList;

import flickr.FlickrFetchr;
import flickr.GalleryItem;
import flickr.ThumbnailDownloader;

/**
 * Created by Joe's Desktop on 12/14/2014.
 */
public class FlickrFragment extends Fragment{

    GridView mGridView;
    ArrayList<GalleryItem> mItems;
    ThumbnailDownloader mThumbnailThread;
    String textToSearch = "Keating Hall"; // this does not need to be hardcoded. See comment below for how to make it change w/ intent

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        setHasOptionsMenu(true); //-- for options menu
        //Tell activity to this fragment will respond to option menu.

        updateItems();

        mThumbnailThread = new ThumbnailDownloader(new Handler());
        mThumbnailThread.start();

        // This needs to be passed in the intent
        // Intent i = new Intent( getActivity())
        // i.putExtra(buildingName);
        // startActivity(i);
        // textToSearch = getIntent().getStringExtra(Activity_That_Called_Intent.String_That_Contains_package_id_of_That_Activity);


    }

    public void updateItems() {
        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.flickr_activity, container, false);

        mGridView = (GridView)v.findViewById(R.id.gridView);

        setupAdapter();

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailThread.quit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailThread.clearQueue();
    }

    // -- for options menu
    @Override
    @TargetApi(11)
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_photo_gallery, menu);

        //If we comment out the following code, we use a regular **search dialog activity**.
        ///*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // pull out the SearchView
            MenuItem searchItem = menu.findItem(R.id.menu_item_search);

            SearchView searchView = (SearchView)searchItem.getActionView();

            // get the data from our searchable.xml as a SearchableInfo
            SearchManager searchManager = (SearchManager)getActivity()
                    .getSystemService(Context.SEARCH_SERVICE);

            ComponentName name = getActivity().getComponentName();
            //Returns complete component name of this activity.

            SearchableInfo searchInfo = searchManager.getSearchableInfo(name);


            searchView.setSearchableInfo(searchInfo);
        }
        //*/

    }

    void setupAdapter() {
        if (getActivity() == null || mGridView == null) return;

        if (mItems != null) {
            mGridView.setAdapter(new GalleryItemAdapter(mItems));
        } else {
            mGridView.setAdapter(null);
        }
    }

    private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<GalleryItem>> {
        @Override
        protected ArrayList<GalleryItem> doInBackground(Void... params) {
            Activity activity = getActivity();
            if (activity == null)
                return new ArrayList<GalleryItem>();

            String query1 = "Fordham University ";
            String query2 = " Bronx NY";
            String fullQuery = query1 + textToSearch + query2;

            return new FlickrFetchr().search(fullQuery);
        }

        @Override
        protected void onPostExecute(ArrayList<GalleryItem> items) {
            mItems = items;
            setupAdapter();
        }
    }

    private class GalleryItemAdapter extends ArrayAdapter<GalleryItem> {
        public GalleryItemAdapter(ArrayList<GalleryItem> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.flickr_item, parent, false);
            }

            GalleryItem item = getItem(position);
            ImageView imageView = (ImageView)convertView
                    .findViewById(R.id.gallery_item_imageView);
            imageView.setImageResource(R.drawable.brian_up_close);
            mThumbnailThread.queueThumbnail(imageView, item.getUrl());

            return convertView;
        }
    }

}
