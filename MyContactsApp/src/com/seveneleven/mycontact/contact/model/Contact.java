package com.seveneleven.mycontact.contact.model;

import java.util.*;

public abstract class Contact {

    private String name;
    private List<PhoneNumber> phoneNumbers;
    private List<Email> emails;
    private List<Tag>tags = new ArrayList<>();

    public Contact(String name) {
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        this.emails = new ArrayList<>();
    }
    
    public List<PhoneNumber>getPhoneNumbers(){
    	return phoneNumbers;
    }
    
    public List<Email>getEmails(){
    	return emails;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public void addPhoneNumber(PhoneNumber phone) {
        phoneNumbers.add(phone);
    }

    public void addEmail(Email email) {
        emails.add(email);
    }
    
    public void addTag(Tag tag) {
    	tags.add(tag);
    }

    public void displayCommon() {
        System.out.println("Name: " + name);

        System.out.println("Phones:");
        for (PhoneNumber p : phoneNumbers) {
            System.out.println("- " + p.getNumber());
        }

        System.out.println("Emails:");
        for (Email e : emails) {
            System.out.println("- " + e.getEmail());
        }
    }

    public abstract void display();
}
