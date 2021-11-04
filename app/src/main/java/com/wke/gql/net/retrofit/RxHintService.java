package com.wke.gql.net.retrofit;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface RxHintService {
    //    {"hint":"托运行李最好挂上姓名牌哦~"}
    @GET("")
    public Observable<Hint> getHint();
}
