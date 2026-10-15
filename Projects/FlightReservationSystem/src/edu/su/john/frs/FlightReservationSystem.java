package edu.su.john.frs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FlightReservationSystem {

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final List<Flight> flights = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();
    private static int nextBookingId = 1001;

    // CUSTOM FUNCTIONAL INTERFACE
    @FunctionalInterface
    interface FlightRule {
        boolean test(Flight flight);
    }

    // FLIGHT MODEL
    static class Flight {
        private final String flightNumber;
        private final String airline;
        private final String source;
        private final String destination;
        private final LocalDateTime departure;
        private final double price;
        private final int totalSeats;
        private int availableSeats;

        Flight(String flightNumber, String airline,
               String source, String destination,
               LocalDateTime departure, double price,
               int totalSeats) {
            this.flightNumber = flightNumber;
            this.airline = airline;
            this.source = source;
            this.destination = destination;
            this.departure = departure;
            this.price = price;
            this.totalSeats = totalSeats;
            this.availableSeats = totalSeats;
        }

        public String getFlightNumber() { return flightNumber; }
        public String getAirline() { return airline; }
        public String getSource() { return source; }
        public String getDestination() { return destination; }
        public LocalDateTime getDeparture() { return departure; }
        public double getPrice() { return price; }
        public int getTotalSeats() { return totalSeats; }
        public int getAvailableSeats() { return availableSeats; }

        public boolean reserveSeats(int count) {
            if (count <= 0 || count > availableSeats) {
                return false;
            }
            availableSeats -= count;
            return true;
        }

        public void releaseSeats(int count) {
            if (count > 0 && availableSeats + count <= totalSeats) {
                availableSeats += count;
            }
        }

        @Override
        public String toString() {
            return String.format(
                "%-7s %-14s %-14s %-14s %-18s Rs.%-9.2f %d/%d",
                flightNumber, airline, source, destination,
                departure.format(FORMAT), price,
                availableSeats, totalSeats
            );
        }
    }

    // PASSENGER MODEL
    static class Passenger {
        private final String name;
        private final String email;

        Passenger(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
    }

    enum BookingStatus {
        CONFIRMED, CANCELLED
    }

    // BOOKING MODEL
    static class Booking {
        private final int bookingId;
        private final Flight flight;
        private final Passenger passenger;
        private final int seats;
        private final double totalAmount;
        private BookingStatus status;

        Booking(int bookingId, Flight flight,
                Passenger passenger, int seats) {
            this.bookingId = bookingId;
            this.flight = flight;
            this.passenger = passenger;
            this.seats = seats;
            this.totalAmount = flight.getPrice() * seats;
            this.status = BookingStatus.CONFIRMED;
        }

        public int getBookingId() { return bookingId; }
        public Flight getFlight() { return flight; }
        public Passenger getPassenger() { return passenger; }
        public int getSeats() { return seats; }
        public double getTotalAmount() { return totalAmount; }
        public BookingStatus getStatus() { return status; }

        public void cancel() {
            status = BookingStatus.CANCELLED;
        }

        @Override
        public String toString() {
            return String.format(
                "Booking ID: %d | Passenger: %s | Flight: %s | " +
                "Seats: %d | Amount: Rs.%.2f | Status: %s",
                bookingId, passenger.getName(),
                flight.getFlightNumber(), seats,
                totalAmount, status
            );
        }
    }

    // SAMPLE FLIGHT DATA
    private static void loadFlights() {
        LocalDateTime tomorrow = LocalDateTime.now()
                .plusDays(1).withSecond(0).withNano(0);

        flights.add(new Flight("AI101", "Air India",
                "Chennai", "Delhi", tomorrow.withHour(8).withMinute(0),
                5500, 100));

        flights.add(new Flight("6E202", "IndiGo",
                "Chennai", "Delhi", tomorrow.withHour(11).withMinute(30),
                4800, 120));

        flights.add(new Flight("UK303", "Vistara",
                "Chennai", "Mumbai", tomorrow.withHour(14).withMinute(0),
                6200, 80));

        flights.add(new Flight("6E404", "IndiGo",
                "Bengaluru", "Delhi", tomorrow.withHour(9).withMinute(15),
                4500, 90));

        flights.add(new Flight("AI505", "Air India",
                "Chennai", "Bengaluru", tomorrow.withHour(17).withMinute(0),
                3200, 75));

        flights.add(new Flight("SG606", "SpiceJet",
                "Mumbai", "Delhi", tomorrow.withHour(19).withMinute(30),
                3900, 60));
    }

    private static void printFlightHeader() {
        System.out.printf("%-7s %-14s %-14s %-14s %-18s %-16s %s%n",
                "Number", "Airline", "From", "To",
                "Departure", "Price", "Seats(A/T)");
        System.out.println("-".repeat(105));
    }

    private static void displayFlights(List<Flight> result) {
        if (result.isEmpty()) {
            System.out.println("No matching flights found.");
            return;
        }
        printFlightHeader();
        result.forEach(System.out::println);
    }

    // FUNCTIONAL INTERFACE + PREDICATE
    private static List<Flight> searchFlights(FlightRule rule) {
        return flights.stream()
                .filter(rule::test)
                .collect(Collectors.toList());
    }

    private static void searchByRoute() {
        String from = readText("Enter source city: ");
        String to = readText("Enter destination city: ");

        Predicate<Flight> routeRule = flight ->
                flight.getSource().equalsIgnoreCase(from)
                && flight.getDestination().equalsIgnoreCase(to)
                && flight.getDeparture().isAfter(LocalDateTime.now())
                && flight.getAvailableSeats() > 0;

        displayFlights(searchFlights(routeRule::test));
    }

    private static void searchByMaximumPrice() {
        double maxPrice = readDouble("Enter maximum ticket price: ");

        if (maxPrice < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }

        Predicate<Flight> priceRule =
                flight -> flight.getPrice() <= maxPrice
                        && flight.getDeparture().isAfter(LocalDateTime.now())
                        && flight.getAvailableSeats() > 0;

        List<Flight> result = flights.stream()
                .filter(priceRule)
                .sorted(Comparator.comparingDouble(Flight::getPrice))
                .collect(Collectors.toList());

        displayFlights(result);
    }

    // BOOKING
    private static void bookFlight() {
        String flightNumber = readText("Enter flight number: ");

        Optional<Flight> found = flights.stream()
                .filter(f -> f.getFlightNumber()
                        .equalsIgnoreCase(flightNumber))
                .filter(f -> f.getDeparture().isAfter(LocalDateTime.now()))
                .findFirst();

        if (!found.isPresent()) {
            System.out.println("Flight not found or departure has passed.");
            return;
        }

        Flight flight = found.get();

        System.out.println(flight);
        String name = readText("Passenger name: ");
        String email = readText("Passenger email: ");
        int seats = readInt("Number of seats: ");

        if (name.trim().isEmpty() || !email.contains("@")
                || email.startsWith("@") || email.endsWith("@")
                || seats <= 0) {
            System.out.println("Invalid passenger details or seat count.");
            return;
        }

        if (seats > flight.getAvailableSeats()) {
            System.out.println("Not enough seats available.");
            return;
        }

        // Supplier creates an object when needed.
        Supplier<Passenger> passengerSupplier =
                () -> new Passenger(name, email);

        Passenger passenger = passengerSupplier.get();

        if (flight.reserveSeats(seats)) {
            Booking booking = new Booking(
                    nextBookingId++, flight, passenger, seats);
            bookings.add(booking);

            System.out.println("\nBooking successful!");
            System.out.println(booking);
        } else {
            System.out.println("Booking failed. Please try again.");
        }
    }

    // CANCELLATION
    private static void cancelBooking() {
        int id = readInt("Enter booking ID to cancel: ");

        Optional<Booking> found = bookings.stream()
                .filter(b -> b.getBookingId() == id)
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .findFirst();

        if (!found.isPresent()) {
            System.out.println("Active booking not found.");
            return;
        }

        Booking booking = found.get();
        booking.getFlight().releaseSeats(booking.getSeats());
        booking.cancel();

        System.out.println("Booking cancelled successfully.");
        System.out.println(booking);
    }

    // STREAM API: SORTING
    private static void sortFlightsByPrice() {
        List<Flight> result = flights.stream()
                .filter(f -> f.getDeparture().isAfter(LocalDateTime.now()))
                .filter(f -> f.getAvailableSeats() > 0)
                .sorted(Comparator.comparingDouble(Flight::getPrice))
                .collect(Collectors.toList());

        displayFlights(result);
    }

    // STREAM API: METHOD REFERENCE + FILTER
    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings have been made.");
            return;
        }

        bookings.stream()
                .map(Booking::toString)
                .forEach(System.out::println);
    }

    // STREAM API: COUNT AND REDUCE
    private static void bookingReport() {
        long confirmedCount = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .count();

        long cancelledCount = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CANCELLED)
                .count();

        double revenue = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .mapToDouble(Booking::getTotalAmount)
                .sum();

        System.out.println("\n===== BOOKING REPORT =====");
        System.out.println("Total booking records: " + bookings.size());
        System.out.println("Confirmed bookings: " + confirmedCount);
        System.out.println("Cancelled bookings: " + cancelledCount);
        System.out.printf("Confirmed booking revenue: Rs.%.2f%n", revenue);
    }

    // STREAM API: GROUPING BY FLIGHT
    private static void revenueByFlight() {
        Map<String, Double> revenueMap = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .collect(Collectors.groupingBy(
                        b -> b.getFlight().getFlightNumber(),
                        Collectors.summingDouble(Booking::getTotalAmount)
                ));

        System.out.println("\n===== REVENUE BY FLIGHT =====");

        if (revenueMap.isEmpty()) {
            System.out.println("No confirmed bookings available.");
            return;
        }

        revenueMap.forEach((flight, revenue) ->
                System.out.printf("%s : Rs.%.2f%n", flight, revenue));
    }

    // STREAM API: FUNCTION
    private static void demonstrateFunctionalInterfaces() {
        Predicate<Flight> affordable =
                flight -> flight.getPrice() <= 5000;

        Function<Flight, String> flightDescription =
                flight -> flight.getFlightNumber() + " - "
                        + flight.getSource() + " to "
                        + flight.getDestination();

        Consumer<Flight> printDescription =
                flight -> System.out.println(flightDescription.apply(flight));

        Supplier<String> messageSupplier =
                () -> "Affordable flights (price <= Rs.5000):";

        System.out.println("\n" + messageSupplier.get());

        flights.stream()
                .filter(affordable)
                .filter(f -> f.getDeparture().isAfter(LocalDateTime.now()))
                .forEach(printDescription);
    }

    // INPUT HELPERS
    private static String readText(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (Double.isFinite(value)) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Display the validation message below.
            }
            System.out.println("Enter a valid finite number.");
        }
    }

    private static void showMenu() {
        System.out.println("\n==================================");
        System.out.println("     FLIGHT RESERVATION SYSTEM");
        System.out.println("==================================");
        System.out.println("1. View all flights");
        System.out.println("2. Search flights by route");
        System.out.println("3. Search flights by maximum price");
        System.out.println("4. Book a flight");
        System.out.println("5. Cancel a booking");
        System.out.println("6. Sort flights by price");
        System.out.println("7. View all bookings");
        System.out.println("8. Booking summary report");
        System.out.println("9. Revenue by flight");
        System.out.println("10. Demonstrate functional interfaces");
        System.out.println("0. Exit");
        System.out.println("==================================");
    }

    public static void main(String[] args) {
        loadFlights();

        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    displayFlights(flights);
                    break;
                case 2:
                    searchByRoute();
                    break;
                case 3:
                    searchByMaximumPrice();
                    break;
                case 4:
                    bookFlight();
                    break;
                case 5:
                    cancelBooking();
                    break;
                case 6:
                    sortFlightsByPrice();
                    break;
                case 7:
                    viewBookings();
                    break;
                case 8:
                    bookingReport();
                    break;
                case 9:
                    revenueByFlight();
                    break;
                case 10:
                    demonstrateFunctionalInterfaces();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
