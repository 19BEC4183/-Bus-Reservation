package busreservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Booking {
    ArrayList<Passenger> passengers = new ArrayList<>();
    int busno;
    Date date;
    String mobileNumber;
    String email;
    private static int bookingCount = 1000;
    private int bookingNumber;
    Scanner scn = new Scanner(System.in);

    Booking() {
    	 this.bookingNumber = ++bookingCount;
        System.out.println("Enter number of passengers: ");
        int numPassengers = scn.nextInt();
        scn.nextLine(); // consume newline

        for (int i = 0; i < numPassengers; i++) {
            System.out.println("Enter passenger " + (i + 1) + " name: ");
            String name = scn.nextLine();
            System.out.println("Enter passenger " + (i + 1) + " gender (M/F): ");
            char gender = scn.nextLine().toUpperCase().charAt(0);
            passengers.add(new Passenger(name, gender));
        }

        System.out.println("Enter bus number: ");
        busno = scn.nextInt();
        scn.nextLine(); // consume newline

        System.out.println("Enter date (dd-MM-yyyy): ");
        String dateinput = scn.nextLine();

        System.out.println("Enter mobile number: ");
        mobileNumber = scn.nextLine();

        System.out.println("Enter email: ");
        email = scn.nextLine();

        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateformat.parse(dateinput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public int getBookingNumber() {
        return bookingNumber;
    }

    public boolean isavailable(ArrayList<Bus> buses, ArrayList<Booking> bookings) {
        int capacity = 0;
        Bus selectedBus = null;

        for (Bus bus : buses) {
            if (bus.getbusno() == busno) {
                capacity = bus.getcapacity();
                selectedBus = bus;
                break;
            }
        }

        if (selectedBus == null) {
            System.out.println("Bus number not found.");
            return false;
        }

        int booked = 0;
        for (Booking booking : bookings) {
            if (booking.busno == busno && booking.date.equals(date)) {
                booked += booking.passengers.size();
            }
        }

        boolean available = (booked + passengers.size()) <= capacity;
        if (available) {
            for (int i = 0; i < passengers.size(); i++) {
                selectedBus.bookSeat(); // reduce the available seat count
            }
        }
        return available;
    }

    public double calculateTotalCost(ArrayList<Bus> buses) {
        double totalCost = 0;
        for (Bus bus : buses) {
            if (bus.getbusno() == busno) {
                totalCost = bus.calculateCost() * passengers.size();
                break;
            }
        }
        return totalCost;
    }

    public void cancelBooking(ArrayList<Bus> buses) {
        for (Bus bus : buses) {
            if (bus.getbusno() == busno) {
                bus.returnSeats(passengers.size()); // Increase available seats
                System.out.println("Booking cancelled successfully.");
                break;
            }
        }
    }
}
