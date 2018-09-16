package com.example.rs.caughther;

public class Property {

    private String sender,message;
    public Property(String name,String author){
        this.sender=name;
        this.message=author;
    }
    public String getSender(){
        return sender;
    }
    public  String getMessage(){
        return message;
    }

}
