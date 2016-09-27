package com.gojek.parkinglot;

public class ParkingLot {
    private Slot[] slots;

    public ParkingLot(int noOfSlots) {
        slots = new Slot[noOfSlots];
        for(int i = 0;i<noOfSlots;i++) {
            slots[i] = new Slot();
            slots[i].isAvailable(true);
        }
    }

    public Slot[] getSlots() {
        return slots;
    }
}
