package com.myf.health.api.forum;

import com.myf.health.bean.NewsBean;
import com.myf.health.bean.NewsDetailBean;
import com.myf.health.constants.Urls;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by MaoYouFeng on 2016/5/24.
 */
public interface NewsService {

    @GET(Urls.TOP_URL+Urls.TOP_ID+"/{page}"+Urls.END_URL)
    Observable<List<NewsBean>> getTopNews(@Path("page") int pageIndex);

    @GET(Urls.COMMON_URL+"{id}"+"/{page}"+Urls.END_URL)
    Observable<List<NewsBean>> getNews(@Path("id") String id, @Path("page") int pageIndex);

    @GET(Urls.NEW_DETAIL+"{id}"+"/{page}"+Urls.END_URL)
    Observable<NewsDetailBean> getNewsDetail(@Path("id") String id, @Path("page") int pageIndex);
}
