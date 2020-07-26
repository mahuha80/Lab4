package com.example.vinhntph08047_lab4.activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.vinhntph08047_lab4.R;
import com.example.vinhntph08047_lab4.adapter.LoadMoreAdapter;
import com.example.vinhntph08047_lab4.fragment.CategoryFragment;
import com.example.vinhntph08047_lab4.model.RootModel;
import com.example.vinhntph08047_lab4.net.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LoadMoreAdapter.OnItemClickListener, SearchView.OnQueryTextListener {
    private List<RootModel.Photos.Photo> list = new ArrayList<>();
    private RecyclerView rv;
    private LoadMoreAdapter loadMoreAdapter;
    private int page = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean isLoading = false;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configToolbar();
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progress_main);
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
        switch (item.getItemId()) {
            case R.id.action_home:
                swipeRefreshLayout.setRefreshing(true);
                callAPI();
                new Handler().postDelayed(() -> {
                    swipeRefreshLayout.setRefreshing(false);
                }, 1500);
                break;
            case R.id.category:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, CategoryFragment.newInstance(), CategoryFragment.TAG)
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .addToBackStack(null).commit();
                break;
            case R.id.fb:
                goToFacebook();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToFacebook() {
        try {
            getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/vinhnt0111"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
        list = rootModel.getPhotos().getPhoto();
        loadMoreAdapter = new LoadMoreAdapter(MainActivity.this, list, MainActivity.this::onImageClicked);
        rv.setAdapter(loadMoreAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(gridLayoutManager);
        rv.setHasFixedSize(true);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isLoading = true;
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == list.size() - 1) {
                    loadMore();
                }
//                int currentItem = gridLayoutManager.getChildCount();
//                int totalItem = gridLayoutManager.getItemCount();
//                int scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition() + 5;
//                if (isLoading && (currentItem + scrollOutItems == totalItem)) {
//                    loadMore();
//                }
            }
        });
    }

    private void loadMore() {
        page++;
        Disposable disposable = RetrofitService.getInstance().getData(String.valueOf(page))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::loadMoreSuccess, this::loadmoreFailure);
        compositeDisposable.add(disposable);
    }

    private void loadmoreFailure(Throwable throwable) {
        Toast.makeText(this, "End of galleries", Toast.LENGTH_SHORT).show();
    }

    private void loadMoreSuccess(RootModel rootModel) {
        List<RootModel.Photos.Photo> list = rootModel.getPhotos().getPhoto();
        progressBar.setVisibility(View.INVISIBLE);
        isLoading = false;
        loadMoreAdapter.addItem(list);
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

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                }
            }
        }
        if (!getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
        }
        super.onBackPressed();
    }
}