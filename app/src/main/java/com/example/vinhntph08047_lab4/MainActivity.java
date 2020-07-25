package com.example.vinhntph08047_lab4;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.OnItemClickListener, SearchView.OnQueryTextListener {
    private List<RootModel.Photos.Photo> list = new ArrayList<>();
    private RecyclerView rv;
    private RecycleViewAdapter recycleViewAdapter;
    private int page = 1;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configToolbar();
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        swipeRefreshLayout = findViewById(R.id.sr);
        callAPI();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            page++;
            callAPI();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void configToolbar() {

    }

    private void callAPI() {
        APIService apiService = RetrofitService.getInstance().create(APIService.class);
        apiService.getData(String.valueOf(page)).enqueue(new Callback<RootModel>() {
            @Override
            public void onResponse(Call<RootModel> call, Response<RootModel> response) {
                if (response.isSuccessful()) {
                    RootModel rootModel = response.body();
                    list = rootModel.getPhotos().getPhoto();
                    recycleViewAdapter = new RecycleViewAdapter(MainActivity.this, list, MainActivity.this::onImageClicked);
                    rv.setAdapter(recycleViewAdapter);
                    rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                }
            }

            @Override
            public void onFailure(Call<RootModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onImageClicked(RootModel.Photos.Photo photo) {
        Intent intent = new Intent(this, DetailAcitivy.class);
        intent.putExtra("KEY", photo.getUrlL());
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}