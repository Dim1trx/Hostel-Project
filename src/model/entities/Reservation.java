package model.entities;

import model.exceptions.DomainException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
        if (checkIn.isAfter(checkOut)) {
            throw new DomainException("Check-out date must be after check-in.");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long duration(){
        return Duration.between(this.checkIn.atStartOfDay(), this.checkOut.atStartOfDay()).toDays();
    }

    public void  updateDates(LocalDate checkIn, LocalDate checkOut)  {
        LocalDate now = LocalDate.now();

        if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
            throw new DomainException("Reservation date must be future dates.");
        }
        if (checkIn.isAfter(checkOut)) {
            throw new DomainException("Check-out date must be after check-in.");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;

    }

    @Override
    public String toString() {
        return "Room: " + roomNumber +
                ", check-in: " + checkIn.format(fmt) +
                ", check-out: " + checkOut.format(fmt) +
                ", " + duration() + " nights" +
                '.';

    }
}
