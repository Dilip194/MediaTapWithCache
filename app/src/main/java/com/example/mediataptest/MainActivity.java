package com.example.mediataptest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mediataptest.constant.Constants;
import com.example.mediataptest.mediaModel.MediaModel;
import com.example.mediataptest.mediaPresenterListener.ServiceCompleteListener;
import com.example.mediataptest.rest.ApiClient;
import com.example.mediataptest.rest.ApiInterface;
import com.example.mediataptest.rest.ApiService;
import com.example.mediataptest.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements ServiceCompleteListener, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    MediaModel mediaModel = null;
    ActivityMainBinding mainBinding;
    MediaAdapter mediaAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");


        new ApiService(this, MainActivity.this);
        mainBinding.includeFile.swipeLayout.setOnRefreshListener(this);


        LinearLayoutManager linearLayout = new LinearLayoutManager(MainActivity.this);
        mainBinding.includeFile.listItem.setLayoutManager(linearLayout);
        mediaAdapter = new MediaAdapter(this,mediaModel);
        mainBinding.includeFile.listItem.setAdapter(mediaAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
        return true;
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
        mediaAdapter.notifyChange(mediaModel);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onRefresh() {
        new ApiService(this, MainActivity.this);
    }
}
