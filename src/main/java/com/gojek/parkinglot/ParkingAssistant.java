package com.gojek.parkinglot;

public class ParkingAssistant {
    ParkingLot parkingLot;

    public ParkingLot createParkingLot(int noOfSlots) {
        parkingLot = new ParkingLot(noOfSlots);
        return parkingLot;
    }

    public Ticket park(String registrationNumber, String color) {
        Slot[] slots = parkingLot.getSlots();
        int freeSlot = 0;
        while(freeSlot<slots.length && !slots[freeSlot].isAvailable()) {
            freeSlot++;
        }
        if(freeSlot == slots.length) {
            System.out.println("Sorry, parking lot is full");
            return null;
        }
        Ticket ticket = new Ticket(registrationNumber, color, freeSlot);
        slots[freeSlot].isAvailable(false);
        slots[freeSlot].setTicket(ticket);
        System.out.println("Allocated slot number: " + (freeSlot+1));
        return ticket;
    }

    public void leave(int slotNum) {
        Slot[] slots = parkingLot.getSlots();
        slots[slotNum].isAvailable(true);
        System.out.println(String.format("Slot number {0} is free", slotNum));
    }

    public String getStatus() {
        String status = "Slot No.\tRegistration No.\tColour";
        Slot[] slots = parkingLot.getSlots();
        for(int i = 0;i<slots.length; i++) {
            if(!slots[i].isAvailable()) {
                status = status.concat("\n" + (i+1)+"\t"+ slots[i].getTicket().getRegistrationNumber() +"\t"+ slots[i].getTicket().getColor());
            }
        }
        return status;
    }
}
