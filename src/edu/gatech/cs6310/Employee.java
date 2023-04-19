package edu.gatech.cs6310;

public class Employee extends User{
    private String employeeID;

    private Double salary;

    public Employee(String fName, String lName,String ssn, String phoneNumber, String employeeID, Double salary) {
        super(fName, lName, ssn, phoneNumber);
        this.employeeID = employeeID;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "name:" + this.getFirstName() + "_" + this.getLastName() + "," +
                "phone:" + this.getPhoneNumber() + "," +
                "taxID:" + this.getSsn();
    }

    public String getEmployeeID() {
        return employeeID;
    }

}