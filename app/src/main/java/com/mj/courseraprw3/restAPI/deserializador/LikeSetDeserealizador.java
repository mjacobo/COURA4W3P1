package com.mj.courseraprw3.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mj.courseraprw3.pojo.likesPostAnswer;
import com.mj.courseraprw3.pojo.pets;
import com.mj.courseraprw3.restAPI.JsonKeys;
import com.mj.courseraprw3.restAPI.model.AccountResponse;
import com.mj.courseraprw3.restAPI.model.LikeSetResponse;

import java.lang.reflect.Type;

/**
 * Created by leyenda on 10/30/16.
 */

public class LikeSetDeserealizador  implements JsonDeserializer<LikeSetResponse> {

    @Override
    public LikeSetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikeSetResponse likeSetResponse = gson.fromJson(json, LikeSetResponse.class);
        JsonObject likeSetResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.LIKES_SET_INFO);

        likeSetResponse.setMyLike(deserializeMyDataFromJson(likeSetResponseData));
        return likeSetResponse;
    }

    private likesPostAnswer deserializeMyDataFromJson(JsonObject accountResponseData){
        likesPostAnswer myData = new likesPostAnswer();

        int code            = accountResponseData.get(JsonKeys.LIKES_SET_INFO_CODE).getAsInt();

        myData.setCode(code);

        return myData;
    }
}
