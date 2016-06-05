package com.myf.health.api.forum;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.myf.health.bean.NewsBean;
import com.myf.health.bean.NewsDetailBean;
import com.myf.health.constants.Constants;
import com.myf.health.constants.Urls;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by MaoYouFeng on 2016/5/24.
 */
public class NewsApi {
    Type type = new TypeToken<List<NewsBean>>() {
    }.getType();
    Gson gson = new GsonBuilder()
            //各种配置
            .registerTypeAdapter(type, new JsonDeserializer<List<NewsBean>>() {
                @Override
                public List<NewsBean> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    String str;
                    JsonElement je = null;
                    List<NewsBean> list = new ArrayList<NewsBean>();
                    str = json.toString();
                    System.out.println(str);
                    Iterator it = json.getAsJsonObject().entrySet().iterator();
                    if (it.hasNext()) {
                        Map.Entry<String, JsonElement> entry = (Map.Entry<String, JsonElement>) it.next();
                        je = entry.getValue();
                    }
                    System.out.println(typeOfT.toString());
                    Gson gson = new Gson();
                    if (je == null)
                        return null;
                    list = gson.fromJson(je.getAsJsonArray(), typeOfT);
                    System.out.println(list.size());
                    return list;
                }
            })
            .create();
    NewsService newsService;

    public NewsApi(OkHttpClient mOkHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Urls.HOST)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        newsService = retrofit.create(NewsService.class);
    }

    public Observable<List<NewsBean>> getTopNews(int pageIndex) {
        return newsService.getTopNews(pageIndex).subscribeOn(Schedulers.io());
    }

    public Observable<List<NewsBean>> getNews(int id, int pageIndex) {
        if (id == Constants.NEWS_TYPE_TOP)
            return getTopNews(pageIndex);
        String strId = Urls.IDS[id];
        return newsService.getNews(strId, pageIndex).subscribeOn(Schedulers.io());
    }

    public Observable<NewsDetailBean> getNewsDetail(int id, int pageIndex) {
        String strId = Urls.IDS[id];
        return newsService.getNewsDetail(strId, pageIndex).subscribeOn(Schedulers.io());
    }
}
