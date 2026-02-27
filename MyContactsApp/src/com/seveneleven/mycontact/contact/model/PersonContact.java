package com.seveneleven.mycontact.contact.model;

public class PersonContact extends Contact {

    private String nickname;

    public PersonContact(String name, String nickname) {
        super(name);
        this.nickname = nickname;
    }

    @Override
    public void display() {
        System.out.println("\nPerson Contact");
        displayCommon();
        System.out.println("Nickname: " + nickname);
    }
}