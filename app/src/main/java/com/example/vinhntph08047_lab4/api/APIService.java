package com.example.vinhntph08047_lab4.api;

import com.example.vinhntph08047_lab4.model.RootModel;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    String BASE_URL = "https://www.flickr.com/";

    @GET("services/rest/?method=flickr.favorites.getList&api_key=41de2f631e450643c7933eb12a0c1242&user_id=189412766%40N05&extras=views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&per_page=14&format=json&nojsoncallback=1")
    Single<RootModel> getData(@Query("page") String page);

    @GET("services/rest/?method=flickr.galleries.getPhotos&api_key=41de2f631e450643c7933eb12a0c1242&per_page=&extras=views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&format=json&nojsoncallback=1&per_page=30")
    Single<RootModel> getGalleries(@Query("gallery_id") String galleryId);
}
