// Experiment 4.3: Ticket Booking System

import java.util.*;
class TicketBookingSystem {
    private final boolean[] seats;
    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }
    public synchronized boolean bookSeat(int seatNumber, String userName) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println(userName + ": Invalid seat number!");
            return false;
        }
        if (seats[seatNumber - 1]) {
            System.out.println(userName + ": Seat " + seatNumber + " is already booked!");
            return false;
        }
        seats[seatNumber - 1] = true;
        System.out.println(userName + " booked seat " + seatNumber);
        return true;
    }
}
class User extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String userName;
    public User(TicketBookingSystem system, int seatNumber, String userName, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.userName = userName;
        setPriority(priority);
    }
    @Override
    public void run() {
        system.bookSeat(seatNumber, userName);
    }
}
public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(5);
        System.out.println("No bookings yet.");
        User user1 = new User(bookingSystem, 1, "Anish (VIP)", Thread.MAX_PRIORITY);
        User user2 = new User(bookingSystem, 2, "Bobby (Regular)", Thread.NORM_PRIORITY);
        User user3 = new User(bookingSystem, 3, "Charlie (VIP)", Thread.MAX_PRIORITY);
        User user4 = new User(bookingSystem, 4, "David (Regular)", Thread.MIN_PRIORITY);
        User user5 = new User(bookingSystem, 4, "Eve (VIP)", Thread.MAX_PRIORITY);
        User user6 = new User(bookingSystem, 6, "Frank (Regular)", Thread.NORM_PRIORITY);
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
        user6.start();
        try {
            user1.join();
            user2.join();
            user3.join();
            user4.join();
            user5.join();
            user6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
