package edu.gatech.cs6310;

public class DronePilot extends Employee {
    private String licenseID;

    private Integer experience;

    private Drone assignedDrone;

    public DronePilot(String fName, String lName, String ssn, String phoneNumber, String employeeID, String licenseID, Double salary, Integer experience) {
        super(fName, lName, ssn, phoneNumber, employeeID, salary);
        this.licenseID = licenseID;
        this.experience = experience;
        this.assignedDrone = null;
    }

    @Override
    public String toString() {
        return super.toString() + "," + "licenseID:" + licenseID + "," + "experience:" + experience;
    }

    public void increaseExperience() { experience += 1; }

    /* ======================= GETTERS AND SETTERS ======================= */

    public void setAssignedDrone(Drone drone) {
        assignedDrone = drone;
    }

    public Drone getAssignedDrone() {
        return assignedDrone;
    }
}