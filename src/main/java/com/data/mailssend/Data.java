package com.data.mailssend;

public class Data
{
    //------------change ||sql dependency
    private long userId;
    private String forename;
    private  String roleFlag;
    public Data(long userId,String forename,String roleFlag){
        this.userId=userId;
        this.forename=forename;
        this.roleFlag=roleFlag;

    }
    public  long getId(){
        return userId;
    }
    public String getName(){
        return forename;
    }
    public String getDescription(){
        return roleFlag;
    }
}
