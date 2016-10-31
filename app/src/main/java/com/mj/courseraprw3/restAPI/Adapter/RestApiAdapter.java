package com.mj.courseraprw3.restAPI.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mj.courseraprw3.restAPI.ConstantesRestApi;
import com.mj.courseraprw3.restAPI.EndpointsApi;
import com.mj.courseraprw3.restAPI.deserializador.AccountDeserealizador;
import com.mj.courseraprw3.restAPI.deserializador.AccountDeserealizador2;
import com.mj.courseraprw3.restAPI.deserializador.LikeDeserealizador;
import com.mj.courseraprw3.restAPI.deserializador.LikeSetDeserealizador;
import com.mj.courseraprw3.restAPI.deserializador.PetDeserealizador;
import com.mj.courseraprw3.restAPI.model.AccountResponse;
import com.mj.courseraprw3.restAPI.model.LikeSetResponse;
import com.mj.courseraprw3.restAPI.model.LikesResponse;
import com.mj.courseraprw3.restAPI.model.PetResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leyenda on 10/17/16.
 */

public class RestApiAdapter {
    public EndpointsApi stablishConnectionApiInstagram(Gson gson){
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(ConstantesRestApi.ROOT_URL)
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson buildGsonDeserializerMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new PetDeserealizador());

        return  gsonBuilder.create();
    }

    public Gson buildGsonDeserializerAccount(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new AccountDeserealizador());

        return  gsonBuilder.create();
    }

    public Gson buildGsonDeserializerAccount2(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AccountResponse.class, new AccountDeserealizador2());

        return  gsonBuilder.create();
    }

    public Gson buildGsonDeserializarlikes(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LikesResponse.class, new LikeDeserealizador());

        return  gsonBuilder.create();
    }

    public Gson buildGsonDeserializarlikesSet(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LikeSetResponse.class, new LikeSetDeserealizador());

        return  gsonBuilder.create();
    }
}
