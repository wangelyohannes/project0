public class SeasonTicketHolders {
    private String name;
    private String ticketPlan;
    private int numOfGames;

    public SeasonTicketHolders(String name, String ticketPlan, int numOfGames) {
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

    public double calculateSeasonTicketPlanPrice() {
        double homeGameCost = 0.0;
        double reservationFee = 0.0;

        if (ticketPlan.equals("A")) {
            homeGameCost = 35.99;
        } else if (ticketPlan.equals("B")) {
            homeGameCost = 125.99;
        } else if (ticketPlan.equals("C")) {
            homeGameCost = 325.99;
        }

        if (numOfGames >= 1 && numOfGames <= 10) {
            reservationFee = 214.0;
        } else if (numOfGames >= 11 && numOfGames <= 20) {
            reservationFee = 323.59;
        } else if (numOfGames >= 21 && numOfGames <= 30) {
            reservationFee = 413.5;
        }

        return (homeGameCost * numOfGames) + reservationFee;
    }
}