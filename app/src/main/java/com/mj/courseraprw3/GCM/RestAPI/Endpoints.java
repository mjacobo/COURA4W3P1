package com.mj.courseraprw3.GCM.RestAPI;

import com.mj.courseraprw3.GCM.model.UsuarioResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by leyenda on 10/26/16.
 */

public interface Endpoints {

    @FormUrlEncoded
    @POST(ConstantesRestAPI.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("id_dispositivo")  String id_dispositivo, @Field("id_usuario_instagram")  String id_usuario_instagram);

    @GET(ConstantesRestAPI.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> toqueFoto(@Path("user_id") String user_id, @Path("media_id") String media_id);
}
