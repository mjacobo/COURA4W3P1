package com.mj.courseraprw3.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mj.courseraprw3.pojo.pets;
import com.mj.courseraprw3.restAPI.JsonKeys;
import com.mj.courseraprw3.restAPI.model.AccountResponse;
import com.mj.courseraprw3.restAPI.model.PetResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by leyenda on 10/26/16.
 */

public class AccountDeserealizador2 implements JsonDeserializer<AccountResponse> {

    @Override
    public AccountResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        AccountResponse accountResponse = gson.fromJson(json, AccountResponse.class);
        JsonObject accountResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.MEDIA_RESPONSE_ARRAY);

        accountResponse.setMyAccount(deserializeMyDataFromJson(accountResponseData));
        return accountResponse;
    }

    private pets deserializeMyDataFromJson(JsonObject accountResponseData){
       pets myData = new pets();

            String id           = accountResponseData.get(JsonKeys.USER_ID).getAsString();
            String fullName     = accountResponseData.get(JsonKeys.USER_FULL_NAME).getAsString();
            String urlPic       = accountResponseData.get(JsonKeys.PROFILE_PICTURE).getAsString();
            int likes           = JsonKeys.LIKES_DEFAULT;

            myData.setId(id);
            myData.setNombreCompleto(fullName);
            myData.setURLpicture(urlPic);
            myData.setLikes(likes);

        return myData;
    }
}
