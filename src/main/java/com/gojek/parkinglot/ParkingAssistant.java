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
            return null;
        }
        Ticket ticket = new Ticket(registrationNumber, color, freeSlot);
        slots[freeSlot].isAvailable(false);
        slots[freeSlot].setTicket(ticket);
        return ticket;
    }

    public void leave(int slotNum) {
        Slot[] slots = parkingLot.getSlots();
        slots[slotNum-1].isAvailable(true);
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

    public String getRegistrationNumbersForCarsWithColor(String color) {
        String registrationNumbers = "";
        Slot[] slots = parkingLot.getSlots();
        for(int i = 0;i<slots.length; i++) {
            if(!slots[i].isAvailable() && slots[i].getTicket().getColor().equals(color)) {
                if(registrationNumbers.isEmpty())
                    registrationNumbers = slots[i].getTicket().getRegistrationNumber();
                else
                    registrationNumbers = registrationNumbers + ", " + slots[i].getTicket().getRegistrationNumber();
            }
        }
        return registrationNumbers;
    }

    public String getSlotNumbersForCarsWithColor(String color) {
        String slotNumbers = "";
        Slot[] slots = parkingLot.getSlots();
        for(int i = 0;i<slots.length; i++) {
            if(!slots[i].isAvailable() && slots[i].getTicket().getColor().equals(color)) {
                if(slotNumbers.isEmpty())
                    slotNumbers = String.valueOf(i+1);
                else
                    slotNumbers = slotNumbers + ", " + (i+1);
            }
        }
        return slotNumbers;
    }

    public String getSlotNumberForARegistrationNumber(String registrationNumber) {
        Slot[] slots = parkingLot.getSlots();
        for(int i = 0;i<slots.length; i++) {
            if(!slots[i].isAvailable() && slots[i].getTicket().getRegistrationNumber().equals(registrationNumber)) {
                return String.valueOf(i + 1);
            }
        }
        return "Not found";
    }

    public String processCommand(CommandParser.CommandType commandType, String[] input) {
        String result = "";
        switch (commandType) {

            case create_parking_lot:
                int noOfSlots = Integer.parseInt(input[1]);
                ParkingLot parkingLot = createParkingLot(noOfSlots);
                if(parkingLot != null) {
                    result = "Created a parking lot with " +noOfSlots+" slots";
                }
                break;

            case park:
                Ticket ticket = park(input[1], input[2]);
                if(ticket == null) {
                    result = "Sorry, parking lot is full";
                }
                else {
                    result = "Allocated slot number: " + (ticket.getSlotNumber()+1);
                }
                break;

            case leave:
                int slotNum = Integer.parseInt(input[1]);
                leave(slotNum);
                result = String.format("Slot number " + slotNum + " is free");
                break;

            case status:
                String status = getStatus();
                result = status;
                break;

            case registration_numbers_for_cars_with_colour:
                String registrationNumbersForCarsWithColor = getRegistrationNumbersForCarsWithColor(input[1]);
                result = registrationNumbersForCarsWithColor;
                break;

            case slot_numbers_for_cars_with_colour:
                String slotNumbersForCarsWithColor = getSlotNumbersForCarsWithColor(input[1]);
                result = slotNumbersForCarsWithColor;
                break;

            case slot_number_for_registration_number:
                String slotNumberForARegistrationNumber = getSlotNumberForARegistrationNumber(input[1]);
                result = slotNumberForARegistrationNumber;
                break;
        }
        return result;
    }
}
