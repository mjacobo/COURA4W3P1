package com.mj.courseraprw3.restAPI.model;

import com.mj.courseraprw3.pojo.pets;

import java.util.ArrayList;

/**
 * Created by leyenda1 on 16/10/2016.
 */

public class PetResponse {
    ArrayList<pets>  myPetsPics;

    public ArrayList<pets> getMyPetsPics() {
        return myPetsPics;
    }

    public void setMyPetsPics(ArrayList<pets> myPetsPics) {
        this.myPetsPics = myPetsPics;
    }
}
