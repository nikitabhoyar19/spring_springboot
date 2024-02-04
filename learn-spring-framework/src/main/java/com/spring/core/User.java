package com.spring.core;

public class User {

    private Address address;

    public User(){
        address = new Address();
    }

    public void address(){
        address.getAddress();
    }
}

class Address{

    public void getAddress(){
        // logic goes here
    }
}
