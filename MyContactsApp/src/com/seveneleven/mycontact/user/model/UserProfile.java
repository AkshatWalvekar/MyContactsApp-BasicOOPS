package com.seveneleven.mycontact.user.model;

public class UserProfile {

    private String address;
    private String phone;

    public UserProfile(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}
