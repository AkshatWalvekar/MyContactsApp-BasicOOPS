package com.seveneleven.mycontact.contact.model;

public class OrganizationContact extends Contact {

    private String company;

    public OrganizationContact(String name, String company) {
        super(name);
        this.company = company;
    }

    @Override
    public void display() {
        System.out.println("\nOrganization Contact");
        displayCommon();
        System.out.println("Company: " + company);
    }
}
