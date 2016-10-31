package com.mj.courseraprw3.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mj.courseraprw3.pojo.likes;
import com.mj.courseraprw3.pojo.pets;
import com.mj.courseraprw3.restAPI.JsonKeys;
import com.mj.courseraprw3.restAPI.model.LikesResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by leyenda on 10/30/16.
 */

public class LikeDeserealizador implements JsonDeserializer<LikesResponse> {

    @Override
    public LikesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikesResponse likesResponse = gson.fromJson(json, LikesResponse.class);
        JsonArray likesResponseData= json.getAsJsonObject().getAsJsonArray(JsonKeys.LIKES_RESPONSE_ARRAY);

        likesResponse.setMylikes(deserializeMylikesFromJson(likesResponseData));
        return likesResponse;
    }

    private ArrayList<likes> deserializeMylikesFromJson(JsonArray likesResponseData){
        ArrayList<likes> myLikes = new ArrayList<>();

        for (int i = 0; i < likesResponseData.size() ; i++) {
            JsonObject likesResponseDataObject = likesResponseData.get(i).getAsJsonObject();

            String username     = likesResponseDataObject.get(JsonKeys.LIKES_RESPONSE_USERNAME).getAsString();
            String first_name   = likesResponseDataObject.get(JsonKeys.LIKES_RESPONSE_FIRST_NAME).getAsString();
            String last_name    = likesResponseDataObject.get(JsonKeys.LIKES_RESPONSE_LAST_NAME).getAsString();
            String type         = likesResponseDataObject.get(JsonKeys.LIKES_RESPONSE_TYPE).getAsString();
            String id           = likesResponseDataObject.get(JsonKeys.LIKES_RESPONSE_ID).getAsString();

            likes myCurrentlike = new likes();
            myCurrentlike.setUsername(username);
            myCurrentlike.setFirst_name(first_name);
            myCurrentlike.setLast_name(last_name);
            myCurrentlike.setType(type);
            myCurrentlike.setId(id);

            myLikes.add(myCurrentlike);
        }

        return myLikes;
    }
}
