package com.mj.courseraprw3.restAPI;

/**
 * Created by leyenda1 on 16/10/2016.
 */

public final class ConstantesRestApi {
    public static final String VERSION          = "/v1/";
    public static final String ROOT_URL         = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN     = "4036128990.c26c392.e7ee6cce889f483babce2353f3b630eb";
    public static final String KEY_ACCESS_TOKEN = "?access_token=" ;
    public static final String KEY_GET_RECENT_MEDIA_USER  = "users/self/media/recent";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN ;

    public static final String KEY_GET_LIST_USER_FOLLOWS = "users/self/follows";
    public static final String URL_GET_LIST_USER_FOLLOWS = KEY_GET_LIST_USER_FOLLOWS + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_MY_INFO = "users/self";
    public static final String URL_GET_MY_INFO = KEY_GET_MY_INFO + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_FRIEND_INFO = "users/search";
    public static final String KEY_QUERY_TOKEN     = "?q=";
    public static final String KEY_FRIEND_NAME     = "{friendName}";
    public static final String KEY_SACCESS_TOKEN   = "&access_token=";
    public static final String URL_GET_FRIEND_INFO = KEY_GET_FRIEND_INFO + KEY_QUERY_TOKEN + KEY_FRIEND_NAME + KEY_SACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_FRIEND_DATA = "users/";
    public static final String KEY_FRIEND_ID       = "{friendId}/";
    public static final String URL_GET_FRIEND_DATA = KEY_GET_FRIEND_DATA + KEY_FRIEND_ID + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_FRIEND_RECENT_MEDIA = "media/recent/";
    public static final String URL_GET_FRIEND_RECENT_MEDIA = KEY_GET_FRIEND_DATA + KEY_FRIEND_ID + KEY_GET_FRIEND_RECENT_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_SET_LIKE_IMAGE = "media/{media_id}/likes";
    public static final String URL_SET_LIKE_IMAGE = KEY_SET_LIKE_IMAGE + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_LIKE_IMAGE = "media/{media_id}/likes";
    public static final String URL_GET_LIKE_IMAGE = KEY_GET_LIKE_IMAGE + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}
