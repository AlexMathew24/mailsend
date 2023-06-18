package com.data.mailssend;

public class User {
    private String email;
    private  String query;
    public User(String email,String query){
        this.email=email;
        this.query=query;

    }
    public String getEmail(){
        return email;
    }
    public  String getQuery()
    {
        return  query;
    }
}
