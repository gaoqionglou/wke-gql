package com.wke.gql.net.retrofit;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface RxCityService {
    @GET("{country}")
    public Observable<List<City>> getAllCity(@Path("country") String country);

    @HTTP(method = "GET", path = "{country}", hasBody = false)
    public Observable<List<City>> getAllCity2(@Path("country") String country);
}
