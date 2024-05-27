package busreservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        ArrayList<Bus> buses = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        buses.add(new Bus(101, "SRT", true, 45, "Karur to Chennai", "sleeper", "08:00", "14:00"));
        buses.add(new Bus(102, "ROYAL", false, 45, "Karur to Chennai", "sitting", "09:00", "15:00"));
        buses.add(new Bus(103, "SSS", true, 45, "Karur to Chennai", "sleeper", "10:00", "16:00"));
        buses.add(new Bus(201, "SK BALU", true, 45, "Namakkal to Chennai", "sleeper", "08:30", "14:30"));
        buses.add(new Bus(202, "SKT", false, 2, "Namakkal to Chennai", "sitting", "09:30", "15:30"));

        int userin = 1;
        while (userin != 3) {
            System.out.println("Enter 1 for booking, 2 for cancellation, and 3 to exit");
            userin = scn.nextInt();

            if (userin == 1) {
                Bus.routeInfo();
                int route = scn.nextInt();

                String selectedRoute = "";
                if (route == 1) {
                    selectedRoute = "Karur to Chennai";
                } else if (route == 2) {
                    selectedRoute = "Namakkal to Chennai";
                } else {
                    System.out.println("Invalid route selected.");
                    continue;
                }

                System.out.println("Available buses for route: " + selectedRoute);
                for (Bus bus : buses) {
                    if (bus.getRoute().equals(selectedRoute)) {
                        bus.getbusinfo();
                    }
                }

                Booking booking = new Booking();
                if (booking.isavailable(buses, bookings)) {
                    bookings.add(booking);
                    double totalCost = booking.calculateTotalCost(buses);
                    System.out.println("Congratulations! Booking confirmed. Booking Number: " + booking.getBookingNumber());
                    System.out.println("Total cost: " + totalCost);
                } else {
                    System.out.println("Sorry, no available seats. Try another date or bus.");
                }
            } else if (userin == 2) {
                System.out.println("Enter booking number for cancellation: ");
                int bookingNumber = scn.nextInt();
                boolean found = false;
                for (Booking booking : bookings) {
                    if (booking.getBookingNumber() == bookingNumber) {
                        booking.cancelBooking(buses);
                        bookings.remove(booking);
                        System.out.println("Booking cancelled successfully. Refund will be initiated within one hour.");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Booking not found with the provided booking number.");
                }
            }
        }

        scn.close();
    }
}
