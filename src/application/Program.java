package application;

import model.entities.Reservation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Room number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Check-in date (dd/MM/yyyy): ");
        LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);

        System.out.print("Check-out date (dd/MM/yyyy): ");
        LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

        if (checkIn.isAfter(checkOut)) {
            System.out.println("Error in reservation: Check-out date must be after check-in date!");
        } else {
            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println(reservation);

            System.out.println();
            System.out.println("Enter date to update reservation: ");

            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = LocalDate.parse(sc.nextLine(), fmt);

            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = LocalDate.parse(sc.nextLine(), fmt);

            String error =  reservation.updateDates(checkIn, checkOut);

            if(error != null) {
                System.out.println(error);
            }
            else{
                System.out.println("Reservation" + reservation);
            }

        }


        sc.close();
    }
}
