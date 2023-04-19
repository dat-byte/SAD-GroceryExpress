package edu.gatech.cs6310;

public abstract class User {
    private String fName;

    private String lName;

    private String ssn;
    private String phoneNumber;

    public User(String fName, String lName, String ssn, String phoneNumber) {
        this.fName = fName;
        this.lName = lName;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() { return lName; }

    public String getSsn() {
        return ssn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
