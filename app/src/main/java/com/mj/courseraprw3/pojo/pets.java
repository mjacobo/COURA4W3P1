package com.mj.courseraprw3.pojo;

import java.util.ArrayList;

/**
 * Created by leyenda1 on 16/09/2016.
 */

public class pets {
    private String  id;
    private String  nombreCompleto;
    private String  URLpicture;
    private Integer likes = 0;

    public pets() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getURLpicture() {
        return URLpicture;
    }

    public void setURLpicture(String URLpicture) {
        this.URLpicture = URLpicture;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public pets (String nombreCompleto, Integer mylikes, String URLpicture){
        setURLpicture(URLpicture);
        setNombreCompleto(nombreCompleto);
        setLikes(mylikes);
    }

    public int indexOf(String needle, ArrayList<pets> haystack)
    {
        for (int i=0; i<haystack.size(); i++)
        {
            if (haystack.get(i) != null && haystack.get(i).getNombreCompleto().equals(needle)
                    || needle == null && haystack.get(i) == null) return i;
        }

        return -1;
    }

}
