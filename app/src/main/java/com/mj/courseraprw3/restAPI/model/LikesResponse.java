package com.mj.courseraprw3.restAPI.model;

import com.mj.courseraprw3.pojo.likes;

import java.util.ArrayList;

/**
 * Created by leyenda on 10/30/16.
 */

public class LikesResponse {
    ArrayList<likes> mylikes;

    public ArrayList<likes> getMylikes() {
        return mylikes;
    }

    public void setMylikes(ArrayList<likes> mylikes) {
        this.mylikes = mylikes;
    }
}
