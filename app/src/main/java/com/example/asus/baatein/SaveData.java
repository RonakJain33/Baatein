package com.example.asus.baatein;

public class SaveData {

    private SaveData (){

    }
    String email,password,phone,address;

    public String getEmail (){ return email;}

    public void setEmail (String email) { this.email = email; }

    public String getPassword (){ return password;}

    public void setPassword (String password) { this.password = password; }

    public String getPhone (){ return phone;}

    public void setPhone (String phone) { this.phone = phone; }

    public String getAddress (){ return address;}

    public void setAddress (String address) { this.address = address; }

    public SaveData (String email, String password, String phone, String address){
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
