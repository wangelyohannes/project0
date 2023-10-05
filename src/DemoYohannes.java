import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;

public class DemoYohannes {
    public static void main(String[] args) {
        ArrayList<SeasonTicketHolder> ticketHolders = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        String choice;
        do {
            System.out.print("Enter the customer's name: ");
            String name = scanner.nextLine();

            String ticketPlan;
            do {
                System.out.println("Choose from one of the following Home Game Season Ticket Plans:");
                System.out.println("A. Standard Seating - $35.99 / per game");
                System.out.println("B. Premium Seating - $125.99 / per game");
                System.out.println("C. Luxury Seating - $325.99 / per game");
                System.out.print("Enter your choice (A, B, or C): ");
                ticketPlan = scanner.nextLine().toUpperCase();
            } while (!ticketPlan.equals("A") && !ticketPlan.equals("B") && !ticketPlan.equals("C"));

            int numOfGames;
            boolean invalidInput;
            do {
                System.out.print("How many games are being purchased? (Min: 1 game, Max: 30 games) ");
                invalidInput = false;
                try {
                    numOfGames = scanner.nextInt();
                    scanner.nextLine(); // Consume the remaining newline character

                    if (numOfGames < 1 || numOfGames > 30) {
                        System.out.println("ERROR: INVALID NUMBER OF GAMES ENTERED, please try again.");
                        invalidInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("ERROR: INVALID NUMBER OF GAMES ENTERED, please try again.");
                    scanner.nextLine(); // Consume the invalid input
                    invalidInput = true;
                    numOfGames = 0; // Assign a default value
                }
            } while (invalidInput);

            SeasonTicketHolder ticketHolder = new SeasonTicketHolder(name, ticketPlan, numOfGames);
            ticketHolders.add(ticketHolder);
            echo "# project0"
            System.out.print("Do you wish to enter information for another season ticket holder (Y/N)? ");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("Y"));

        System.out.println("\nTicket Holder List");
        System.out.println("---------------------------");
        double totalRevenue = 0.0;
        int totalGamesPurchased = 0;
        for (SeasonTicketHolder ticketHolder : ticketHolders) {
            System.out.println("Customer Name: " + ticketHolder.getName());
            System.out.println("Home Game Ticket Plan: " + getTicketPlanName(ticketHolder.getTicketPlan()));
            System.out.println("Number of Home Games: " + ticketHolder.getNumOfGames());
            System.out.println("Cost Per Home Game: " + currencyFormat.format(ticketHolder.getTicketCost()));
            System.out.println("Home Game Ticket Reservation Subtotal: " +
                    currencyFormat.format(ticketHolder.getTicketCost() * ticketHolder.getNumOfGames()));
            System.out.println("Home Game Ticket Reservation Fee: " +
                    currencyFormat.format(ticketHolder.calculateReservationFee()));
            System.out.println("Season Ticket Plan Price: " +
                    currencyFormat.format(ticketHolder.calculateSeasonTicketPlanPrice()));
            System.out.println();
            totalRevenue += ticketHolder.calculateSeasonTicketPlanPrice();
            totalGamesPurchased += ticketHolder.getNumOfGames();
        }

        System.out.println("Totals:");
        System.out.println("---------------------------");
        System.out.println("Total Season Ticket Holders: " + ticketHolders.size());
        System.out.println("Total Games Purchased: " + totalGamesPurchased);
        System.out.println("Total Season Ticket Revenue: " + currencyFormat.format(totalRevenue));
    }

    public static String getTicketPlanName(String ticketPlan) {
        if (ticketPlan.equals("A")) {
            return "Standard Seating";
        } else if (ticketPlan.equals("B")) {
            return "Premium Seating";
        } else if (ticketPlan.equals("C")) {
            return "Luxury Seating";
        } else {
            return "Unknown";
        }
    }
}

class SeasonTicketHolder {
    private String name;
    private String ticketPlan;
    private int numOfGames;

    public SeasonTicketHolder(String name, String ticketPlan, int numOfGames) {
        this.name = name;
        this.ticketPlan = ticketPlan;
        this.numOfGames = numOfGames;
    }

    public String getName() {
        return name;
    }

    public String getTicketPlan() {
        return ticketPlan;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public double getTicketCost() {
        if (ticketPlan.equals("A")) {
            return 35.99;
        } else if (ticketPlan.equals("B")) {
            return 125.99;
        } else if (ticketPlan.equals("C")) {
            return 325.99;
        } else {
            return 0.0;
        }
    }

    public double calculateReservationFee() {
        if (ticketPlan.equals("A")) {
            return 214.0;
        } else if (ticketPlan.equals("B")) {
            return 214.0;
        } else if (ticketPlan.equals("C")) {
            return 214.0;
        } else {
            return 0.0;
        }
    }

    public double calculateSeasonTicketPlanPrice() {
        return (getTicketCost() * numOfGames) + calculateReservationFee();
    }
}
