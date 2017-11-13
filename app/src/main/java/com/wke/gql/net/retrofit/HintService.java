package com.wke.gql.net.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;



public interface HintService {
//    {"hint":"托运行李最好挂上姓名牌哦~"}
    @GET("https://3g.csair.com/CSMBP/data/flightstatus/gethint.do?APPTYPE=touch")
    public Call<Hint> getHint();
}
