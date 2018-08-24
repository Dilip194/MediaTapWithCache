package com.example.mediataptest;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mediataptest.mediaModel.MediaModel;
import com.example.mediataptest.mediaModel.Page;
import com.example.mediataptest.mediaPresenterListener.RecycleViewClickListener;
import com.example.mediataptest.mediaPresenterListener.ServiceCompleteListener;
import com.example.mediataptest.rest.ApiService;
import com.example.mediataptest.databinding.ActivityMainBinding;
import com.example.mediataptest.utils.NetworkService;
import com.ncornette.cache.OkCacheControl;

import java.util.ArrayList;
import java.util.List;

import static android.widget.GridLayout.HORIZONTAL;

public class MainActivity extends AppCompatActivity implements ServiceCompleteListener, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener,OkCacheControl.NetworkMonitor,RecycleViewClickListener {

    MediaModel mediaModel = null;
    ActivityMainBinding mainBinding;
    MediaAdapter mediaAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mainBinding.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setShowHideAnimationEnabled(true);
        actionBar.setTitle("");

        new ApiService(this, MainActivity.this);
        mainBinding.includeFile.swipeLayout.setOnRefreshListener(this);

        initView();
    }

    private void removeProgress() {
        if(null != mainBinding.includeFile.swipeLayout){
            mainBinding.includeFile.swipeLayout.setRefreshing(false);
        }

    }

    private void initView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(MainActivity.this);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        mainBinding.includeFile.listItem.addItemDecoration(itemDecor);
        mainBinding.includeFile.listItem.setLayoutManager(linearLayout);
        mediaAdapter = new MediaAdapter(this, mediaModel);
        mainBinding.includeFile.listItem.setAdapter(mediaAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                mediaAdapter.animateTo(mediaModel.query.getPages());
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskCompleteListener(MediaModel response) {
        this.mediaModel = response;
        removeProgress();
        mediaAdapter.animateTo(mediaModel.query.getPages());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.isEmpty()){
            mediaAdapter.animateTo(mediaModel.query.getPages());
        }else{
            List<Page> list = filter(mediaModel.query.getPages(), newText);
            mediaAdapter.animateTo(list);
            mainBinding.includeFile.listItem.scrollToPosition(0);
        }

        return true;
    }

    private List<Page> filter(List<Page> models, String query) {
        query = query.toLowerCase();

        final List<Page> filteredModelList = new ArrayList<>();
        for (Page model : models) {

            if(model != null){
                final String name = model.getTitle().toLowerCase();
                if (name.contains(query)) {
                    filteredModelList.add(model);
                }
            }

        }
        return filteredModelList;
    }

    @Override
    public void onRefresh() {
        new ApiService(this, MainActivity.this);
    }

    @Override
    public boolean isOnline() {
        return NetworkService.isNetworkAvailable(getApplicationContext());
    }

    @Override
    public void onRecyclerViewClickedListener(String name) {
        String url = "https://en.wikipedia.org/wiki/"+name;
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}
