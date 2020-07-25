package com.example.vinhntph08047_lab4;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.OnItemClickListener, SearchView.OnQueryTextListener {
    private List<RootModel.Photos.Photo> list = new ArrayList<>();
    private RecyclerView rv;
    private RecycleViewAdapter recycleViewAdapter;
    private int page = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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

    @SuppressLint("CheckResult")
    private void callAPI() {
        Observable<RootModel> observable = RetrofitService.getInstance().getData(String.valueOf(page));
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailure);
        compositeDisposable.add(disposable);
    }

    private void onSuccess(RootModel rootModel) {
        Toast.makeText(this, Thread.currentThread().toString(), Toast.LENGTH_SHORT).show();
        list = rootModel.getPhotos().getPhoto();
        recycleViewAdapter = new RecycleViewAdapter(MainActivity.this, list, MainActivity.this::onImageClicked);
        rv.setAdapter(recycleViewAdapter);
        rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

    private void onFailure(Throwable throwable) {
        Toast.makeText(this, "Please connect wifi", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}