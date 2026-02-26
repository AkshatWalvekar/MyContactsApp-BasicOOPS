package com.seveneleven.mycontact.user.model;

public abstract class User {

    private String username;
    private String email;
    private int age;
    private String password;

    public User(String username, String email, int age, String password) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getPassword() { return password; }

    public abstract void display();
}
