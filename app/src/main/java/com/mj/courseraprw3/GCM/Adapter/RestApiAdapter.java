package com.mj.courseraprw3.GCM.Adapter;

import com.mj.courseraprw3.GCM.RestAPI.ConstantesRestAPI;
import com.mj.courseraprw3.GCM.RestAPI.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leyenda on 10/26/16.
 */

public class RestApiAdapter {

    public Endpoints establecerConexionRestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Endpoints.class);
    }
}
