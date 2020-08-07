package com.example.vinhntph08047_lab4.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinhntph08047_lab4.R;
import com.example.vinhntph08047_lab4.adapter.CategoryAdapter;
import com.example.vinhntph08047_lab4.model.GalleriesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements CategoryAdapter.OnItemClickListener {
    public static final String TAG = CategoryFragment.class.getName();
    private CategoryAdapter categoryAdapter;
    private Context context;
    private RecyclerView recyclerView;
    private List<GalleriesModel> galleriesModels = new ArrayList<>();


    public CategoryFragment() {
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateItem();
        recyclerView = view.findViewById(R.id.rvCategory);
        categoryAdapter = new CategoryAdapter(context, galleriesModels, this::onCategoryClicked);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void populateItem() {
        galleriesModels.add(new GalleriesModel("72157662388372890", "Your Best Shot 2015: Starry Night"));
        galleriesModels.add(new GalleriesModel("72157662518421935", "Monochrome\n"));
        galleriesModels.add(new GalleriesModel("72157661862021010", "Your Best Shot 2015: Less is More\n"));
        galleriesModels.add(new GalleriesModel("72157661841075321", "Easy as Pie\n"));
        galleriesModels.add(new GalleriesModel("72157661264572079", "Christmas Cookies\n"));
        galleriesModels.add(new GalleriesModel("72157715102362628", "Summer 2020"));
        galleriesModels.add(new GalleriesModel("72157714905095181", "Pride 2020"));
        galleriesModels.add(new GalleriesModel("72157714941979913", "Flickr Friday - Spoon"));
        galleriesModels.add(new GalleriesModel("72157714206344417", "Bugs"));
        galleriesModels.add(new GalleriesModel("72157714192312496", "Spring Flowers"));
        galleriesModels.add(new GalleriesModel("72157714011589476", "Flickr Friday - I Stay Home"));
        galleriesModels.add(new GalleriesModel("72157713917091727", "Flickr Friday - Pet Love"));
        galleriesModels.add(new GalleriesModel("72157712544669811", "Close Up: A Your Best Shot 2019 Gallery"));
        galleriesModels.add(new GalleriesModel("72157712270801873", "Top Photos From The UK in 2019"));
    }

    @Override
    public void onCategoryClicked(GalleriesModel galleriesModel) {
        getChildFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.container_category, DetailGalleryFragment.newInstance(galleriesModel.getGalleries_id()))
                .addToBackStack(DetailGalleryFragment.TAG)
                .commit();
    }
}