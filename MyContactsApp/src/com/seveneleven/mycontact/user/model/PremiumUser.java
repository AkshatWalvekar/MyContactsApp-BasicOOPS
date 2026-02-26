package com.seveneleven.mycontact.user.model;

public class PremiumUser extends User {

    public PremiumUser(String username, String email, int age, String password) {
        super(username, email, age, password);
    }

    @Override
    public void display() {
        System.out.println("Premium User -> " + getUsername());
    }
}
