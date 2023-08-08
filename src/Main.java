import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact{

    private String  contactName ;
    private String contactEmail;
    private String contactNumber;

    public Contact(String name, String email, String phone) {
        this.contactName = name;
        this.contactNumber = phone;
        this.contactEmail = email;
    }

    public String getContactName() {
        return contactName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public String getContactNumber() {
        return contactNumber;
    }

}

class Phonebook{

    public List<Contact> contactsList;
    public Phonebook() {
        contactsList = new ArrayList<>();
    }

    // Methods to manage the PhoneBook
    public void addContact(Contact contact) { contactsList.add(contact);
        System.out.println(" Contact Added Successfully"); }

    public void editContact(int index, Contact updatedContact) {
        contactsList.set(index, updatedContact);
    }

    public void deleteContact(int index) {
        contactsList.remove(index);
    }

    public Contact searchContacts(String ToFind) {
        //List<Contact> searchResults = new ArrayList<>();
        Contact searchResult = null;

        for (Contact exisitingContact :  contactsList) {
            if (exisitingContact.getContactName().toLowerCase().contains(ToFind.toLowerCase())
                    ||(exisitingContact.getContactNumber().contains(ToFind)) ) {
                searchResult = exisitingContact;
            }
        }

        return searchResult;
    }

}


public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Phonebook phonebook = new Phonebook();

        File PhoneBookFile = new File("PhoneBook.txt");

        if(PhoneBookFile.exists()) {
            System.out.println("File Phone Book already exists: \n New Phonebook Not Created "  );
        }
        else {
            try {
                PhoneBookFile.createNewFile();
                System.out.println(" PhoneBook created successfully. ");
            } catch (IOException e) {
                System.out.println(" Error Found \n Cannot create PhoneBook");
                throw new RuntimeException(e);
            }
        }


        while (true) {
            System.out.println("-------------------------");
            System.out.println("          MENU ");
            System.out.println("-------------------------\n");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Display Contacts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Scanner scanner1 = new Scanner(System.in);
                    String name;
                    String email;
                    String phone;
                    System.out.println("Enter Name");
                    name = scanner1.nextLine();
                    System.out.println("Enter Email");
                    email = scanner1.nextLine();
                    System.out.println("Enter Phone Number");
                    phone = scanner1.nextLine();

                    Contact newContact = new Contact(name , email , phone);
                    phonebook.addContact(newContact);
                    break;
                case 2:

                    // Edit an existing contact by updating its attributes
                    // Reading index of the contact to edit, then read updated attributes
                    String name2;
                    String email2;
                    String phone2;
                    int index2;

                    System.out.println("Enter Index of Contact You want to Update");
                    index2 = scanner.nextInt();
                    System.out.println("Enter Update Name");
                    name2 = scanner.nextLine();
                    System.out.println("Enter Update Email");
                    email2 = scanner.nextLine();
                    System.out.println("Enter Updated Phone Number");
                    phone2 = scanner.nextLine();

                    // making new contact with update attributes
                    Contact updatedContact2 = new Contact(name2,email2,phone2);

                    phonebook.editContact(index2 , updatedContact2);
                    System.out.println("\n Contact Update Successfully ");
                    System.out.println("-----------------------------------");
                    break;
                case 3:

                    // Delete a contact by index
                    // Read index from user
                    int index3;
                    System.out.println("Enter Index of Contact You want to Delete");
                    index3 = scanner.nextInt();

                    phonebook.deleteContact(index3);
                    break;
                case 4:

                    // Search for a contact by name or phone number
                    // Read search from user

                    String ToFindString;
                    System.out.println(" Enter the Name or Phone Number You Want to search");
                    ToFindString = scanner.nextLine();

                    Contact ToFindContact = phonebook.searchContacts(ToFindString);
                    // Displaying Contact Details of Contact TO FIND
                    System.out.println(" Details of To Be Searched Contacts ");
                    System.out.println(ToFindContact.getContactName());
                    System.out.println(ToFindContact.getContactNumber());
                    System.out.println(ToFindContact.getContactEmail());
                    System.out.println("-----------------------------------");
                    break;
                case 5:

                    List<Contact> contactsList = phonebook.contactsList;
                    String fileName = "PhoneBook.txt";

                    // Writing all the contact details from array list to file Phonebook.txt
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        for (Contact contact : contactsList) {
                            writer.write("Name: " + contact.getContactName() + "\n");
                            writer.write("Phone: " + contact.getContactNumber() + "\n");
                            writer.write("Email: " + contact.getContactEmail() + "\n");
                            writer.write("--------------------------------\n");
                        }
                        System.out.println("\nContact details written to " + fileName);
                    } catch (IOException e) {
                        System.err.println("\nError writing contact details: " + e.getMessage());
                    }

                    // Display all contacts in the phonebook
                    System.out.println(" \n Displaying all contacts from Phonebook");

                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.err.println("\n Error reading the file: " + e.getMessage());
                    }

                   /* loop to display contents of array list
                    for (int i = 0 ; i< phonebook.contactsList.size() ; i++)
                    {
                        Contact contact = phonebook.contactsList.get(i);
                        System.out.println("Contact " + (i + 1) + ":");
                        System.out.println("Name: " + contact.getContactName());
                        System.out.println("Phone: " + contact.getContactNumber());
                        System.out.println("Email: " + contact.getContactEmail());
                        System.out.println("--------------------------------");
                    }*/
                case 6:
                    System.out.println("-----------------------------------");
                    System.out.println("            Exiting...");
                    System.out.println("-----------------------------------");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}