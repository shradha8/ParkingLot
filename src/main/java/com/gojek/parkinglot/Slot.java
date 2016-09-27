package com.gojek.parkinglot;

public class Slot {
    private Boolean isAvailable;
    private Ticket ticket;

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void isAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
