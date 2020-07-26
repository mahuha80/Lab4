package com.example.vinhntph08047_lab4.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinhntph08047_lab4.R;
import com.example.vinhntph08047_lab4.activity.DetailAcitivy;
import com.example.vinhntph08047_lab4.adapter.RecycleViewAdapter;
import com.example.vinhntph08047_lab4.model.RootModel;
import com.example.vinhntph08047_lab4.net.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailGalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailGalleryFragment extends Fragment implements RecycleViewAdapter.OnItemClickListener {
    public static final String TAG = DetailGalleryFragment.class.getName();
    private static final String ARG_PARAM1 = "param1";
    private List<RootModel.Photos.Photo> list = new ArrayList<>();
    private Context context;
    private String gallery_id;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecycleViewAdapter recycleViewAdapter;
    private RecyclerView rv;


    public DetailGalleryFragment() {
    }

    public static DetailGalleryFragment newInstance(String param1) {
        DetailGalleryFragment fragment = new DetailGalleryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gallery_id = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv_detail_gallery);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Disposable disposable = RetrofitService
                .getInstance()
                .getGalleries(gallery_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onSuccess, this::onFailure);
        compositeDisposable.add(disposable);
    }

    private void onSuccess(RootModel rootModel) {
        list = rootModel.getPhotos().getPhoto();
        recycleViewAdapter = new RecycleViewAdapter(context, list, this::onImageClicked);
        rv.setAdapter(recycleViewAdapter);
        rv.setLayoutManager(new GridLayoutManager(context, 2));
    }

    private void onFailure(Throwable throwable) {
        Toast.makeText(context, "Please connect wifi :)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(context, "destroy view", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        Toast.makeText(context, "destroy ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClicked(RootModel.Photos.Photo photo) {
        Intent intent = new Intent(getActivity(), DetailAcitivy.class);
        intent.putExtra("KEY", photo.getUrlL());
        startActivity(intent);
    }
}