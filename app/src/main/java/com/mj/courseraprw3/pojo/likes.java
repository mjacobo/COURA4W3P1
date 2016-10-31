package com.mj.courseraprw3.pojo;

/**
 * Created by leyenda on 10/30/16.
 */

public class likes {
    private String username;
    private String first_name;
    private String last_name;
    private String type;
    private String id;

    public likes(String username, String first_name, String last_name, String type, String id) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.type = type;
        this.id = id;
    }

    public likes() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
