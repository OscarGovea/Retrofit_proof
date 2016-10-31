package com.herzen.retrofit_proof.api;

/**
 * Created by herzen on 31/10/16.
 */
public class Repository {
    private String id;
    private String name;

    @Override
    public String toString(){
        return id + "/" + name;
    }
}
