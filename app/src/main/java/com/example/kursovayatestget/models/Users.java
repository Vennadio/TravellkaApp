package com.example.kursovayatestget.models;

public class Users {
    private String name,email,password,phone;

    public Users(){}
    public Users( String name, String email, String password, String phone){
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}
