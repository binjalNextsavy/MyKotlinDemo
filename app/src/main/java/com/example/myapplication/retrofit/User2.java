package com.example.myapplication.retrofit;

import com.google.gson.annotations.SerializedName;

public class User2 {
    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;

    public User2(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
