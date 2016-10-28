package com.example.god.springboot_rest_android;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by GOD on 2016-10-28.
 */

public interface TestService {

    String BASE_URL = "http://192.168.0.3:8080/app/";

    @GET("test")
    Call<List<Test>> getList();
    //Call<ResponseBody> getList();

    //@GET("test/{no}")
    //Call<List<Test>> getComment(@Path("no") int no);
    //Call<ResponseBody> getComment(@Path("no") int no);

    //@DELETE("test/{no}")
    //Call<ResponseBody> getComment(@Path("no") int no);

    class Factory {

        public static final int CONNECT_TIMEOUT = 15;
        public static final int WRITE_TIMEOUT   = 15;
        public static final int READ_TIMEOUT    = 15;
        private static OkHttpClient client;
        private static TestService service;

        public static TestService getInstance(Context context) {

            if (service == null) {
                client = new OkHttpClient().newBuilder()
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)  //연결 타임아웃 시간 설정
                        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)      //쓰기 타임아웃 시간 설정
                        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)        //읽기 타임아웃 시간 설정
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(TestService.class);
                return service;
            } else {
                return service;
            }
        }


    }
}
