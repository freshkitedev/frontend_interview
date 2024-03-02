import java.util.*;

enum BERTH {
    LOWER_BERTH,
    MIDDLE_BERTH,
    UPPER_BERTH,
    SIDE_LOWER_BERTH,
    SIDE_UPPER_BERTH,
    MAX_BERTH
};

class Passenger {
    String name;
    int age;
    String gender;
    int berthPreference;

    String childName;
    int childAge;
    String childGender;

    public Passenger(String name, int age, String gender, int berthPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.childAge = 0;
    }
}

class Ticket {
    Passenger passenger;
    String status; // "Confirmed", "RAC", "Waiting"
    int pnr;

    public Ticket(Passenger passenger, String status, int berthNumber) {
        this.passenger = passenger;
        this.status = status;
        this.pnr = berthNumber;
    }
}

public class RailwayReservationSystem {
    private int TOTAL_BERTHS;
    private int RAC_BERTH;
    private int WAITING_LIST_CAPACITY;
    private int [] berth_count;
    private int pnr;
    private static String [] birth_text = {
        "Lower",
        "Middle",
        "Upper",
        "Side Lower",
        "Side Upper"
    };

    RailwayReservationSystem(int tb, int rb, int wl) {
        TOTAL_BERTHS = tb;
        RAC_BERTH = rb;
        WAITING_LIST_CAPACITY = wl;
        pnr = 100;
        berth_count = new int[BERTH.MAX_BERTH.ordinal()];
        berth_count[BERTH.LOWER_BERTH.ordinal()] = tb < 5 ? tb: tb / 5;
        tb -= berth_count[BERTH.LOWER_BERTH.ordinal()];
        tb = tb < 0 ? 0 : tb;
        berth_count[BERTH.MIDDLE_BERTH.ordinal()] = tb < 5 ? tb: TOTAL_BERTHS / 5;
        tb -= berth_count[BERTH.MIDDLE_BERTH.ordinal()];
        tb = tb < 0 ? 0 : tb;
        berth_count[BERTH.UPPER_BERTH.ordinal()] = tb < 5 ? tb: TOTAL_BERTHS / 5;
        tb -= berth_count[BERTH.UPPER_BERTH.ordinal()];
        tb = tb < 0 ? 0 : tb;
        berth_count[BERTH.SIDE_UPPER_BERTH.ordinal()] = tb < 5 ? tb: TOTAL_BERTHS / 5;
        tb -= berth_count[BERTH.SIDE_UPPER_BERTH.ordinal()];
        tb = tb < 0 ? 0 : tb;
        int berthSum = berth_count[BERTH.LOWER_BERTH.ordinal()] + berth_count[BERTH.MIDDLE_BERTH.ordinal()] +
        berth_count[BERTH.UPPER_BERTH.ordinal()] + berth_count[BERTH.SIDE_UPPER_BERTH.ordinal()];
        berthSum = TOTAL_BERTHS - berthSum - rb;

        berth_count[BERTH.SIDE_LOWER_BERTH.ordinal()] = berthSum > 0 ? berthSum: 0;
    }

    private HashMap<Integer, Ticket> bookedTickets = new HashMap<>();
    private List<Ticket> racTickets = new LinkedList<>();
    private List<Ticket> waitingList = new LinkedList<>();

    public void bookTicket(Scanner scanner) {
        System.out.println("Availability:");
        System.out.print("Lower:" + berth_count[BERTH.LOWER_BERTH.ordinal()] + 
                        " Middle:" + berth_count[BERTH.MIDDLE_BERTH.ordinal()] + 
                        " Upper:" + berth_count[BERTH.UPPER_BERTH.ordinal()] + 
                        " Side lower:" + berth_count[BERTH.SIDE_LOWER_BERTH.ordinal()] + 
                        " Side Upper:" + berth_count[BERTH.SIDE_UPPER_BERTH.ordinal()] + 
                        " RAC:" + (RAC_BERTH * 2 - racTickets.size()) +
                        " Waiting list:" + (WAITING_LIST_CAPACITY - waitingList.size()));
        System.out.print("\nEnter Passenger Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Passenger Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Passenger Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Berth Preference: [0-Lower,1-Middle,2-Upper,3-SideUpper,4-SideLower] ");
        int berthPreference = scanner.nextInt();
        Passenger passenger = new Passenger(name, age, gender, berthPreference);

        System.out.print("Children travelling with you?[y/n]:");
        String choice = scanner.next();
        scanner.nextLine();
        System.out.println("Choice:" + choice);
        if (choice.equals("y")) {
            System.out.print("Enter Child Name:");
            passenger.childName = scanner.next();
            System.out.print("Enter Child Age:");
            passenger.childAge = scanner.nextInt();
            System.out.print("Enter Child Gender:");
            passenger.childGender = scanner.next();
            passenger.berthPreference = BERTH.LOWER_BERTH.ordinal();
        }

        if (passenger.age > 60 || (passenger.gender.equalsIgnoreCase("female") && passenger.age > 18)) {
            passenger.berthPreference = BERTH.LOWER_BERTH.ordinal();
            
        }
        allocateBerth(passenger);
    }

    private void allocateBerth(Passenger passenger) {
        if (bookedTickets.size() < TOTAL_BERTHS - RAC_BERTH) {
            if (berth_count[passenger.berthPreference] == 0) {
                System.out.println("Birth peference not available allocating another birth");
                for (int berth = 0; berth < BERTH.MAX_BERTH.ordinal(); berth++) {
                    if (berth_count[berth] != 0) {
                        passenger.berthPreference = berth;
                        berth_count[berth]--;
                        break;
                    }
                }
            } else {
                berth_count[passenger.berthPreference]--;
            }
            Ticket ticket = new Ticket(passenger, "Confirmed", ++pnr);
            bookedTickets.put(pnr, ticket);
            System.out.println("Ticket booked successfully for " + passenger.name + ". PNR Number: " + ticket.pnr);
        } else if (racTickets.size() < RAC_BERTH * 2) {
            Ticket ticket = new Ticket(passenger, "RAC", ++pnr);
            racTickets.add(ticket);
            System.out.println("Ticket booked successfully for " + passenger.name + " in RAC. Berth Number: " + ticket.pnr);
        } else if (waitingList.size() < WAITING_LIST_CAPACITY) {
            Ticket ticket = new Ticket(passenger, "Waiting", ++pnr);
            waitingList.add(ticket);
            System.out.println("Ticket booked successfully for " + passenger.name + " in Waiting List. Berth Number: " + ticket.pnr);
        } else {
            System.out.println("No tickets available.");
        }
    }

    private void cancelRacTicket(int racTicketIndex) {
        Ticket t = racTickets.get(racTicketIndex);
        t.status = "Cancelled";
        racTickets.remove(racTicketIndex);
        if (!waitingList.isEmpty()) {
            t = waitingList.get(0);
            waitingList.remove(0);
            t.status = "RAC";
            racTickets.add(t);
            System.out.println("Ticket cancelled successfully. Moved a waiting list ticket to RAC.");
        }
    }

    public void cancelTicket(Scanner scanner) {
        System.out.print("Enter the PNR number:");
        int pnr = scanner.nextInt();
        if (bookedTickets.containsKey(pnr)) {
            Ticket t = bookedTickets.get(pnr);
            berth_count[t.passenger.berthPreference]++;
            bookedTickets.remove(pnr);
            if (!racTickets.isEmpty()) {
                t = racTickets.get(0);
                
                cancelRacTicket(0);
                t.status = "Confirmed";
                bookedTickets.put(t.pnr, t);
            }
            System.out.println("Ticket cancelled successfully. Confirmed a RAC ticket.");
        } else if (!racTickets.isEmpty()) {
            for (int i = 0; i < racTickets.size();i++) {
                Ticket t = racTickets.get(i);
                if (t.pnr == pnr) {
                    cancelRacTicket(i);
                }
            }
        } else if (!waitingList.isEmpty()){
            for (int i = 0; i < waitingList.size();i++) {
                Ticket t = waitingList.get(i);
                t.status = "Cancelled";
                if (t.pnr == pnr) {
                    waitingList.remove(i);
                    System.out.println("Waiting list ticket cancelled successfully.");
                }
            }
        } else {
            System.out.println("PNR number is not valid.");
        }
    }

    public void printBookedTickets() {
        System.out.println("Booked Tickets:");
        if (bookedTickets.size() != 0)
            System.out.println("Confirmed Tickets");
        for (Map.Entry<Integer, Ticket> entry : bookedTickets.entrySet()) {
            Ticket ticket;
            ticket = entry.getValue();
            System.out.println("PNR Number: " + ticket.pnr + ", Passenger Name: " + ticket.passenger.name +
                    ", Age: " + ticket.passenger.age + ", Gender: " + ticket.passenger.gender + ", Berth Alloted: " +
                    birth_text[ticket.passenger.berthPreference] + ", Status: " + ticket.status);
            if (ticket.passenger.childAge != 0) {
                System.out.println("Child Name:" + ticket.passenger.childName + ", Child Age:" + ticket.passenger.childAge +
                ", Child Gender:" + ticket.passenger.childGender);
            }
        }
        if (racTickets.size() != 0)
            System.out.println("RAC Tickets");
        for (Ticket ticket : racTickets) {
            System.out.println("PNR Number: " + ticket.pnr + ", Passenger Name: " + ticket.passenger.name +
                    ", Age: " + ticket.passenger.age + ", Gender: " + ticket.passenger.gender + ", Berth Alloted: " +
                    birth_text[ticket.passenger.berthPreference] + ", Status: " + ticket.status);
            if (ticket.passenger.childAge != 0) {
                System.out.println(
                        "Child Name:" + ticket.passenger.childName + ", Child Age:" + ticket.passenger.childAge +
                                ", Child Gender:" + ticket.passenger.childGender);
            }
        }
        if (waitingList.size() != 0)
            System.out.println("Waiting list Tickets");
        for (Ticket ticket : waitingList) {
            System.out.println("PNR Number: " + ticket.pnr + ", Passenger Name: " + ticket.passenger.name +
                    ", Age: " + ticket.passenger.age + ", Gender: " + ticket.passenger.gender + ", Berth Alloted: " +
                    birth_text[ticket.passenger.berthPreference] + ", Status: " + ticket.status);
        }
        System.out.println("Total Booked Tickets: " + (bookedTickets.size() + racTickets.size() + waitingList.size()));
    }

    public void printAvailableTickets() {
        System.out.print("\nAvailable Tickets(Berth Number):");
        for (int i = bookedTickets.size() + racTickets.size() + 1; i <= TOTAL_BERTHS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Total Available Tickets: " + (TOTAL_BERTHS - bookedTickets.size() - racTickets.size()));
    }

    public static void main(String[] args) {
        int tb = Integer.parseInt(args[0]);
        int rac = Integer.parseInt(args[1]);
        int wl = Integer.parseInt(args[2]);
        if (tb <= rac) {
            System.out.println("Total berth should be greater than rac");
            return;
        }
        RailwayReservationSystem reservationSystem = new RailwayReservationSystem(tb, rac, wl);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Book Ticket\n2. Cancel Ticket\n3. Print Booked Tickets\n4. Print Available Tickets\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    reservationSystem.bookTicket(scanner);
                    break;
                case 2:
                    reservationSystem.cancelTicket(scanner);
                    break;
                case 3:
                    reservationSystem.printBookedTickets();
                    break;
                case 4:
                    reservationSystem.printAvailableTickets();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }
}
