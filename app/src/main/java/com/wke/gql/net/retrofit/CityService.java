package com.wke.gql.net.retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface CityService {
    @GET("{country}")
    public Call<List<City>> getAllCity(@Path("country") String country);

    @HTTP( method = "GET",path = "{country}",hasBody = false)
    public Call<List<City>> getAllCity2(@Path("country") String country);
}
