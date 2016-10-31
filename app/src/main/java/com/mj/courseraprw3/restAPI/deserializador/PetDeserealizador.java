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
 * Created by leyenda on 10/17/16.
 */

public class PetDeserealizador implements JsonDeserializer<PetResponse> {

    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        petResponse.setMyPetsPics(deserializePetFromJson(petResponseData));
        return petResponse;
    }

    private ArrayList<pets> deserializePetFromJson(JsonArray petResponseData){
        ArrayList<pets> myPets = new ArrayList<>();

        for (int i = 0; i < petResponseData.size() ; i++) {
            JsonObject petResponseDataObject = petResponseData.get(i).getAsJsonObject();


            String id           = petResponseDataObject.get(JsonKeys.MEDIA_RESPONSE_ID).getAsString();

            JsonObject userJson = petResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String fullName     = userJson.get(JsonKeys.USER_FULL_NAME).getAsString();

            JsonObject imageJson         = petResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlPic                = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = petResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes            = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

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
