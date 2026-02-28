package com.seveneleven.mycontact.user;

import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;

import com.seveneleven.mycontact.user.auth.BasicAuth;
import com.seveneleven.mycontact.user.model.*;
import com.seveneleven.mycontact.user.validation.*;
import com.seveneleven.mycontact.contact.model.*;

/*MyContactApp

MyContactsApp is a console-based Java application that allows users to manage 
their contacts efficiently. It demonstrates core OOP concepts like abstraction,
encapsulation, inheritance, and polymorphism through real-world use cases.

@author Developer
@version 7.0

*/

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        //Store Users
        List<User>users = new ArrayList<>();
        
        //Store Contacts
        List<Contact> contacts = new ArrayList<>();
        
        //UC1: Registration
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if (!EmailValidator.isValid(email)) {
            System.out.println("Invalid Email!");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!PasswordValidator.isValid(password)) {
            System.out.println("Weak Password!");
            return;
        }

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter user type (free/premium): ");
        String type = sc.nextLine();

        // Object creation 
        User user;

        if (type.equalsIgnoreCase("free")) {
            user = new FreeUser(username, email, age, password);
        } else {
            user = new PremiumUser(username, email, age, password);
        }
        
        //usercreation
        users.add(user);

        System.out.println("\n Registration Successful!");
        user.display();
        
        //UC2: Login
        System.out.println("Login ");
        
        System.out.println("Enter Username: ");
        String loginUsername = sc.nextLine();
        
        System.out.println("Enter Password: ");
        String loginPassword = sc.nextLine();
        
        BasicAuth auth = new BasicAuth();
        
        User loggedInUser = auth.login(loginUsername, loginPassword, users);
        
        //login status
        if(loggedInUser != null) {
        	System.out.println("Login Successful!");
        	System.out.println("Welcome " + loggedInUser.getUsername());
        }else {
        	System.out.println("Invalid Credentials !");
        }
        
        
        
        while(true) {

            System.out.println("\n---- MAIN MENU ----");
            System.out.println("1. Profile Management");
            System.out.println("2. Create Contact");
            System.out.println("3. View Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. View all Contact");
            System.out.println("6. Delete Contact");
            System.out.println("7. Bulk Operations");
            System.out.println("8. Search Contacts");
            System.out.println("9. FilterContacts");
            System.out.println("10. Exit");
            System.out.print("Choose option: ");

            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {

                // UC3 - Profile Management
                case 1:
                    System.out.println("\nPROFILE MANAGEMENT");

                    System.out.println("1. Update Username");
                    System.out.println("2. Update Email");
                    System.out.println("3. Change Password");
                    System.out.print("Choose option: ");

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {

                        case 1:
                            System.out.print("Enter new username: ");
                            String newUsername = sc.nextLine();
                            loggedInUser.setUsername(newUsername);
                            System.out.println("Username updated!");
                            break;

                        case 2:
                            System.out.print("Enter new email: ");
                            String newEmail = sc.nextLine();

                            if (EmailValidator.isValid(newEmail)) {
                                loggedInUser.setEmail(newEmail);
                                System.out.println("Email updated!");
                            } else {
                                System.out.println("Invalid Email!");
                            }
                            break;

                        case 3:
                            System.out.print("Enter new password: ");
                            String newPassword = sc.nextLine();

                            if (PasswordValidator.isValid(newPassword)) {
                                loggedInUser.setPassword(newPassword);
                                System.out.println("Password changed!");
                            } else {
                                System.out.println("Weak Password!");
                            }
                            break;

                        default:
                            System.out.println("Invalid choice!");
                    }

                    System.out.println("\nUpdated Profile:");
                    loggedInUser.display();
                    break;

                // UC4 - Create Contact
                case 2:
                    System.out.println("\nCREATE CONTACT");

                    System.out.print("Enter contact type (person/org): ");
                    String ctype = sc.nextLine();

                    System.out.print("Enter name: ");
                    String cname = sc.nextLine();

                    Contact contact = null;

                    if (ctype.equalsIgnoreCase("person")) {
                        System.out.print("Enter nickname: ");
                        String nickname = sc.nextLine();
                        contact = new PersonContact(cname, nickname);
                    } else {
                        System.out.print("Enter company name: ");
                        String company = sc.nextLine();
                        contact = new OrganizationContact(cname, company);
                    }

                    // Enter Contact phone numbers
                    System.out.print("How many phone numbers? ");
                    int pCount = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < pCount; i++) {
                        System.out.print("Enter phone: ");
                        String phone = sc.nextLine();
                        contact.addPhoneNumber(new PhoneNumber(phone));
                    }

                    // Enter Contact emails
                    System.out.print("How many emails? ");
                    int eCount = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < eCount; i++) {
                        System.out.print("Enter email: ");
                        String newEmail = sc.nextLine();
                        contact.addEmail(new Email(newEmail));
                    }
                    
                    contacts.add(contact);

                    contact.display();
                    break;

                	
                //UC5: View Contact
                case 3:

                	    System.out.println("\nVIEW CONTACT");

                	    if (contacts.isEmpty()) {
                	        System.out.println("No contacts available!");
                	        break;
                	    }

                	    System.out.print("Enter name to search: ");
                	    String searchName = sc.nextLine();

                	    boolean found = false;

                	    for (Contact c : contacts) {
                	        if (c.getName().equalsIgnoreCase(searchName)) {
                	            c.display();
                	            found = true;
                	            break;
                	        }
                	    }

                	    if (!found) {
                	        System.out.println("Contact not found!");
                	    }

                	    break;
                	    
                // Edit contacts feature
                case 4:

                    System.out.println("\nEDIT CONTACT");

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available!");
                        break;
                    }

                    System.out.print("Enter name to edit: ");
                    String editName = sc.nextLine();

                    Contact foundContact = null;

                    for (Contact c : contacts) {
                        if (c.getName().equalsIgnoreCase(editName)) {
                            foundContact = c;
                            break;
                        }
                    }

                    if (foundContact == null) {
                        System.out.println("Contact not found!");
                        break;
                    }

                    // Show options
                    System.out.println("1. Change Name");
                    System.out.println("2. Add Phone");
                    System.out.println("3. Add Email");
                    System.out.print("Choose option: ");

                    int editChoice = sc.nextInt();
                    sc.nextLine();

                    switch (editChoice) {

                        case 1:
                            System.out.print("Enter new name: ");
                            foundContact.setName(sc.nextLine());
                            System.out.println("Name updated!");
                            break;

                        case 2:
                            System.out.print("Enter new phone: ");
                            foundContact.addPhoneNumber(new PhoneNumber(sc.nextLine()));
                            System.out.println("Phone added!");
                            break;

                        case 3:
                            System.out.print("Enter new email: ");
                            foundContact.addEmail(new Email(sc.nextLine()));
                            System.out.println("Email added!");
                            break;

                        default:
                            System.out.println("Invalid option!");
                    }

                    System.out.println("\nUpdated Contact:");
                    foundContact.display();

                    break;
                    
                    
                //View all contacts feature
                case 5:

                    System.out.println("\nALL CONTACTS");

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available!");
                        break;
                    }

                    for (Contact c : contacts) {
                        System.out.println("------------------");
                        c.display();
                    }

                    break;
                
                    
                //Delete Contact Feature
                case 6:

                    System.out.println("\nDELETE CONTACT");

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available!");
                        break;
                    }

                    System.out.print("Enter name to delete: ");
                    String deleteName = sc.nextLine();

                    Contact contactToDelete = null;

                    for (Contact c : contacts) {
                        if (c.getName().equalsIgnoreCase(deleteName)) {
                            contactToDelete = c;
                            break;
                        }
                    }

                    if (contactToDelete == null) {
                        System.out.println("Contact not found!");
                        break;
                    }

                    System.out.print("Are you sure you want to delete? (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        contacts.remove(contactToDelete);
                        System.out.println("Contact deleted!");
                    } else {
                        System.out.println("Deletion cancelled.");
                    }

                    break;
                    
                case 7:

                    System.out.println("\nBULK OPERATIONS");

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available!");
                        break;
                    }

                    System.out.println("1. Delete Multiple Contacts");
                    System.out.println("2. Export All Contacts");
                    System.out.print("Choose option: ");

                    int bulkChoice = sc.nextInt();
                    sc.nextLine();

                    switch (bulkChoice) {

                        //Delete Multiple Contacts
                        case 1:

                            System.out.print("Enter names to delete (comma separated): ");
                            String input = sc.nextLine();

                            String[] names = input.split(",");

                            for (String n : names) {
                                n = n.trim();

                                Contact toRemove = null;

                                for (Contact c : contacts) {
                                    if (c.getName().equalsIgnoreCase(n)) {
                                        toRemove = c;
                                        break;
                                    }
                                }

                                if (toRemove != null) {
                                    contacts.remove(toRemove);
                                    System.out.println("Deleted: " + n);
                                } else {
                                    System.out.println("Not found: " + n);
                                }
                            }

                            break;

                        // Export contacts
                        case 2:

                            try {
                                FileWriter writer = new FileWriter("contacts.txt");

                                for (Contact c : contacts) {
                                    writer.write("-----\n");
                                    writer.write("Name: " + c.getName() + "\n");
                                }

                                writer.close();

                                System.out.println("Contacts exported to contacts.txt");

                            } catch (Exception e) {
                                System.out.println("Error in exporting file");
                            }

                            break;

                        default:
                            System.out.println("Invalid option!");
                    }

                    break;
                
                //Search Contact Feature
                case 8:

                    System.out.println("\nSEARCH CONTACTS");

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available!");
                        break;
                    }

                    System.out.println("1. Search by Name");
                    System.out.println("2. Search by Phone");
                    System.out.println("3. Search by Email");
                    System.out.print("Choose option: ");

                    int searchChoice = sc.nextInt();
                    sc.nextLine();

                    boolean newfound = false;

                    switch (searchChoice) {

                        // for searching by using Name
                        case 1:
                            System.out.print("Enter name: ");
                            String name = sc.nextLine();

                            for (Contact c : contacts) {
                                if (c.getName().equalsIgnoreCase(name)) {
                                    c.display();
                                    newfound = true;
                                }
                            }
                            break;

                        //for searching by using Phone number
                        case 2:
                            System.out.print("Enter phone: ");
                            String phone = sc.nextLine();

                            for (Contact c : contacts) {
                                for (PhoneNumber p : c.getPhoneNumbers()) {
                                    if (p.getNumber().contains(phone)) {
                                        c.display();
                                        found = true;
                                        break;
                                    }
                                }
                            }
                            break;

                        //for searching by using Email
                        case 3:
                            System.out.print("Enter email: ");
                            String searchemail = sc.nextLine();

                            for (Contact c : contacts) {
                                for (Email e : c.getEmails()) {
                                    if (e.getEmail().contains(searchemail)) {
                                        c.display();
                                        found = true;
                                        break;
                                    }
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid option!");
                    }

                    if (!newfound) {
                        System.out.println("No matching contacts found!");
                    }

                    break;
                
                //filtering feature
                case 9:

                    System.out.println("\nFILTER CONTACTS");

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available!");
                        break;
                    }

                    System.out.println("1. Filter by Name (starts with)");
                    System.out.println("2. Sort by Name");
                    System.out.print("Choose option: ");

                    int filterChoice = sc.nextInt();
                    sc.nextLine();

                    switch (filterChoice) {

                        // FILTER BY NAME
                        case 1:
                            System.out.print("Enter starting letter: ");
                            String prefix = sc.nextLine();

                            boolean nfound = false;

                            for (Contact c : contacts) {
                                if (c.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                                    c.display();
                                    nfound = true;
                                }
                            }

                            if (!nfound) {
                                System.out.println("❌ No matching contacts!");
                            }

                            break;

                        // SORT BY NAME
                        case 2:

                            Collections.sort(contacts, (c1, c2) ->
                                c1.getName().compareToIgnoreCase(c2.getName())
                            );

                            System.out.println("Contacts sorted by name:");

                            for (Contact c : contacts) {
                                c.display();
                            }

                            break;

                        default:
                            System.out.println("Invalid option!");
                    }

                    break;
                    
                //Exit the process.
                case 10: 
                		System.out.println("Exiting...");
                		System.out.println("ThankYou!!");
                		return;
                	    
                default:
                		System.out.println("Invalid option!");
                    
            }
        }
    }
}