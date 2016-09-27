package com.gojek.parkinglot;

public class Ticket {
    private String registrationNumber;
    private String color;
    private int slotNumber;

    public Ticket(String registrationNumber, String color, int slotNumber) {
        this.setRegistrationNumber(registrationNumber);
        this.setColor(color);
        this.setSlotNumber(slotNumber);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
}
