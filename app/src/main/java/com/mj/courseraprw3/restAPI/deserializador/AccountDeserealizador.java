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
import com.mj.courseraprw3.restAPI.model.PetResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by leyenda on 10/18/16.
 */

public class AccountDeserealizador implements JsonDeserializer<PetResponse> {
    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        petResponse.setMyPetsPics(deserializeAccountFromJson(petResponseData));
        return petResponse;
    }

    private ArrayList<pets> deserializeAccountFromJson(JsonArray petResponseData){
        ArrayList<pets> myPets = new ArrayList<>();

        for (int i = 0; i < petResponseData.size() ; i++) {
            JsonObject petResponseDataObject = petResponseData.get(i).getAsJsonObject();

            String id           = petResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String fullName     = petResponseDataObject.get(JsonKeys.USER_FULL_NAME).getAsString();
            String urlPic       = petResponseDataObject.get(JsonKeys.PROFILE_PICTURE).getAsString();
            int likes           = JsonKeys.LIKES_DEFAULT;

            pets myCurrentPet = new pets();
            myCurrentPet.setId(id);
            myCurrentPet.setNombreCompleto(fullName);
            myCurrentPet.setURLpicture(urlPic);
            myCurrentPet.setLikes(likes);

            myPets.add(myCurrentPet);
        }

        return myPets;
    }
}
