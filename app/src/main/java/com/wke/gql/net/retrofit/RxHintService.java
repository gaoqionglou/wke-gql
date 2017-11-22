package com.wke.gql.net.retrofit;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface RxHintService {
    //    {"hint":"托运行李最好挂上姓名牌哦~"}
    @GET("https://3g.csair.com/CSMBP/data/flightstatus/gethint.do?APPTYPE=touch")
    public Observable<Hint> getHint();
}
