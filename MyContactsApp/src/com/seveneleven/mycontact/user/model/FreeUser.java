package com.seveneleven.mycontact.user.model;

public class FreeUser extends User {

    public FreeUser(String username, String email, int age, String password) {
        super(username, email, age, password);
    }

    @Override
    public void display() {
        System.out.println("Free User -> " + getUsername());
    }
}
