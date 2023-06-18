package com.data.mailssend;

public class Data
{
    private long id;
    private String name;
    private  String description;
    public Data(long id,String name,String description){
        this.id=id;
        this.name=name;
        this.description=description;

    }
    public  long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
}
