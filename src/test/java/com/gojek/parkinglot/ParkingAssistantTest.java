package com.gojek.parkinglot;

import org.junit.Before;
import org.junit.Test;

public class ParkingAssistantTest {


    private ParkingAssistant parkingAssistant;

    @Before
    public void setup() {
        parkingAssistant = new ParkingAssistant();
    }

    @Test
    public void shouldCreateParkingLot() {
        int noOfSlots = 6;
        ParkingLot parkingLot = parkingAssistant.createParkingLot(noOfSlots);
        assert(parkingLot.getSlots().length == noOfSlots);
    }

    @Test
    public void shouldParkVehicle() {
        int noOfSlots = 6;
        String registrationNumber = "KA-01-HH1234";
        String color = "White";
        parkingAssistant.createParkingLot(noOfSlots);
        Ticket ticket = parkingAssistant.park(registrationNumber, color);
        assert(ticket.getRegistrationNumber()).equals(registrationNumber);
        assert(ticket.getSlotNumber() == 0);
        assert(ticket.getColor()).equals(color);
    }

    @Test
    public void shouldThrowErrorForParkRequestIfParkingLotIsFull() {
        int noOfSlots = 2;
        String registrationNumber = "KA-01-HH1234";
        String color = "White";
        parkingAssistant.createParkingLot(noOfSlots);
        Ticket ticket1 = parkingAssistant.park(registrationNumber, color);
        Ticket ticket2 = parkingAssistant.park(registrationNumber, color);
        Ticket ticket3 = parkingAssistant.park(registrationNumber, color);
        assert(ticket2 != null);
        assert(ticket3 == null);
    }

    @Test
    public void shouldVacateSlot() {
        int noOfSlots = 6;
        String registrationNumber = "KA-01-HH1234";
        String color = "White";
        ParkingLot parkingLot = parkingAssistant.createParkingLot(noOfSlots);
        Slot[] slots = parkingLot.getSlots();

        parkingAssistant.park(registrationNumber, color);
        assert(slots[0].isAvailable()).equals(false);

        parkingAssistant.leave(0);
        assert(slots[0].isAvailable()).equals(true);
    }

    @Test
    public void shouldGetStatus() {
        int noOfSlots = 6;
        String registrationNumber1 = "KA-01-HH1234";
        String color1 = "White";
        String registrationNumber2 = "KA-01-HH1235";
        String color2 = "Blue";
        parkingAssistant.createParkingLot(noOfSlots);
        Ticket ticket1 = parkingAssistant.park(registrationNumber1, color1);
        Ticket ticket2 = parkingAssistant.park(registrationNumber2, color2);

        String status = parkingAssistant.getStatus();
        assert(status.equals("Slot No.\tRegistration No.\tColour\n1\tKA-01-HH1234\tWhite\n2\tKA-01-HH1235\tBlue"));
    }

    @Test
    public void shouldGetSlotNumbersForCarsWithColor() {
        int noOfSlots = 6;
        String registrationNumber1 = "KA-01-HH1234";
        String color1 = "White";
        String registrationNumber2 = "KA-01-HH1235";
        String color2 = "Blue";
        String registrationNumber3 = "KA-01-HH1236";
        String color3 = "Blue";
        String registrationNumber4 = "KA-01-HH1237";
        String color4 = "White";
        parkingAssistant.createParkingLot(noOfSlots);
        Ticket ticket1 = parkingAssistant.park(registrationNumber1, color1);
        Ticket ticket2 = parkingAssistant.park(registrationNumber2, color2);
        Ticket ticket3 = parkingAssistant.park(registrationNumber3, color3);
        Ticket ticket4 = parkingAssistant.park(registrationNumber4, color4);

        String slotNumbers = parkingAssistant.getSlotNumbersForCarsWithColor("White");
        assert(slotNumbers.equals("1, 4"));
    }

    @Test
    public void shouldGetRegistrationNumbersForCarsWithColor() {
        int noOfSlots = 6;
        String registrationNumber1 = "KA-01-HH1234";
        String color1 = "White";
        String registrationNumber2 = "KA-01-HH1235";
        String color2 = "Blue";
        String registrationNumber3 = "KA-01-HH1236";
        String color3 = "Blue";
        String registrationNumber4 = "KA-01-HH1237";
        String color4 = "White";
        parkingAssistant.createParkingLot(noOfSlots);
        Ticket ticket1 = parkingAssistant.park(registrationNumber1, color1);
        Ticket ticket2 = parkingAssistant.park(registrationNumber2, color2);
        Ticket ticket3 = parkingAssistant.park(registrationNumber3, color3);
        Ticket ticket4 = parkingAssistant.park(registrationNumber4, color4);

        String registrationNumbersForCarsWithColor = parkingAssistant.getRegistrationNumbersForCarsWithColor("White");
        assert(registrationNumbersForCarsWithColor.equals("KA-01-HH1234, KA-01-HH1237"));
    }

    @Test
    public void shouldGetSlotNumberForARegistrationNumber() {
        int noOfSlots = 6;
        String registrationNumber1 = "KA-01-HH1234";
        String color1 = "White";
        String registrationNumber2 = "KA-01-HH1235";
        String color2 = "Blue";
        String registrationNumber3 = "KA-01-HH1236";
        String color3 = "Blue";
        String registrationNumber4 = "KA-01-HH1237";
        String color4 = "White";
        parkingAssistant.createParkingLot(noOfSlots);
        Ticket ticket1 = parkingAssistant.park(registrationNumber1, color1);
        Ticket ticket2 = parkingAssistant.park(registrationNumber2, color2);
        Ticket ticket3 = parkingAssistant.park(registrationNumber3, color3);
        Ticket ticket4 = parkingAssistant.park(registrationNumber4, color4);

        String slotNumbers = parkingAssistant.getSlotNumberForARegistrationNumber("KA-01-HH1236");
        assert(slotNumbers.equals("Allocated slot number: 3"));
    }

    @Test
    public void shouldReturnNotFoundIfSlotNumberForARegistrationNumberIsNotFound() {
        int noOfSlots = 6;
        String registrationNumber1 = "KA-01-HH1234";
        String color1 = "White";
        String registrationNumber2 = "KA-01-HH1235";
        String color2 = "Blue";
        String registrationNumber3 = "KA-01-HH1236";
        String color3 = "Blue";
        String registrationNumber4 = "KA-01-HH1237";
        String color4 = "White";
        parkingAssistant.createParkingLot(noOfSlots);
        Ticket ticket1 = parkingAssistant.park(registrationNumber1, color1);
        Ticket ticket2 = parkingAssistant.park(registrationNumber2, color2);
        Ticket ticket3 = parkingAssistant.park(registrationNumber3, color3);
        Ticket ticket4 = parkingAssistant.park(registrationNumber4, color4);

        String slotNumbers = parkingAssistant.getSlotNumberForARegistrationNumber("KA-01-HH1239");
        assert(slotNumbers.equals("Not found"));
    }

}
