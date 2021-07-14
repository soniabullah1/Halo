package com.example.halo;

public class UserID {

    public static String[] split(String element){
        String[] row = element.split("\n");
        return row;
    }

    public static String getTheString(String x){
        String[] arr = split(x);
        String response = arr[0];
        return response;
    }

    public static String getUserId(String y){
        String[] arr = split(y);
        String id = arr[1];
        return id;
    }
}