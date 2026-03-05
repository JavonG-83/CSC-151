import java.util.Scanner;

public class EaglesManagementSystem {
    private PhiladelphiaEagles eagles;
    private Scanner scanner;

    public EaglesManagementSystem() {
        eagles = new PhiladelphiaEagles();
        scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PHILADELPHIA EAGLES MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("1. Players Management");
        System.out.println("2. Coaches Management");
        System.out.println("3. Team Information");
        System.out.println("4. Exit");
        System.out.println("=".repeat(60));
        System.out.print("Select an option: ");
    }

    public void displayPlayersMenu() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("PLAYERS MANAGEMENT");
        System.out.println("-".repeat(60));
        System.out.println("1. View All Players");
        System.out.println("2. Add New Player");
        System.out.println("3. Remove Player");
        System.out.println("4. Update Player Status");
        System.out.println("5. Search Player");
        System.out.println("6. Back to Main Menu");
        System.out.println("-".repeat(60));
        System.out.print("Select an option: ");
    }

    public void displayCoachesMenu() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("COACHES MANAGEMENT");
        System.out.println("-".repeat(60));
        System.out.println("1. View All Coaches");
        System.out.println("2. Add New Coach");
        System.out.println("3. Remove Coach");
        System.out.println("4. Update Coach Position");
        System.out.println("5. Search Coach");
        System.out.println("6. Back to Main Menu");
        System.out.println("-".repeat(60));
        System.out.print("Select an option: ");
    }

    public void handlePlayersMenu() {
        boolean inPlayersMenu = true;
        while (inPlayersMenu) {
            displayPlayersMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    eagles.displayAllPlayers();
                    break;
                case "2":
                    addPlayerInteractive();
                    break;
                case "3":
                    removePlayerInteractive();
                    break;
                case "4":
                    updatePlayerStatusInteractive();
                    break;
                case "5":
                    searchPlayerInteractive();
                    break;
                case "6":
                    inPlayersMenu = false;
                    break;
                default:
                    System.out.println("✗ Invalid option. Please try again.");
            }
        }
    }

    public void handleCoachesMenu() {
        boolean inCoachesMenu = true;
        while (inCoachesMenu) {
            displayCoachesMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    eagles.displayAllCoaches();
                    break;
                case "2":
                    addCoachInteractive();
                    break;
                case "3":
                    removeCoachInteractive();
                    break;
                case "4":
                    updateCoachPositionInteractive();
                    break;
                case "5":
                    searchCoachInteractive();
                    break;
                case "6":
                    inCoachesMenu = false;
                    break;
                default:
                    System.out.println("✗ Invalid option. Please try again.");
            }
        }
    }

    // Player Interactive Methods
    private void addPlayerInteractive() {
        System.out.println("\n--- Add New Player ---");
        System.out.print("Enter player name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter jersey number: ");
        int number;
        try {
            number = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid number format!");
            return;
        }

        System.out.print("Enter position: ");
        String position = scanner.nextLine().trim();

        System.out.print("Enter status (Active/Injured/Reserve/etc): ");
        String status = scanner.nextLine().trim();

        eagles.addPlayer(name, number, position, status);
    }

    private void removePlayerInteractive() {
        System.out.println("\n--- Remove Player ---");
        System.out.print("Enter player name to remove: ");
        String name = scanner.nextLine().trim();
        eagles.removePlayer(name);
    }

    private void updatePlayerStatusInteractive() {
        System.out.println("\n--- Update Player Status ---");
        System.out.print("Enter player name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter new status: ");
        String newStatus = scanner.nextLine().trim();

        eagles.updatePlayerStatus(name, newStatus);
    }

    private void searchPlayerInteractive() {
        System.out.println("\n--- Search Player ---");
        System.out.print("Enter player name to search: ");
        String name = scanner.nextLine().trim();

        Player player = eagles.findPlayer(name);
        if (player != null) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("PLAYER FOUND:");
            System.out.println(player);
            System.out.println("=".repeat(60));
        } else {
            System.out.println("✗ Player not found!");
        }
    }

    // Coach Interactive Methods
    private void addCoachInteractive() {
        System.out.println("\n--- Add New Coach ---");
        System.out.print("Enter coach name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter position (Head Coach/Coordinator/etc): ");
        String position = scanner.nextLine().trim();

        System.out.print("Enter years of experience: ");
        int yearsExperience;
        try {
            yearsExperience = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid number format!");
            return;
        }

        System.out.print("Enter specialty (Offense/Defense/Special Teams/etc): ");
        String specialty = scanner.nextLine().trim();

        eagles.addCoach(name, position, yearsExperience, specialty);
    }

    private void removeCoachInteractive() {
        System.out.println("\n--- Remove Coach ---");
        System.out.print("Enter coach name to remove: ");
        String name = scanner.nextLine().trim();
        eagles.removeCoach(name);
    }

    private void updateCoachPositionInteractive() {
        System.out.println("\n--- Update Coach Position ---");
        System.out.print("Enter coach name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter new position: ");
        String newPosition = scanner.nextLine().trim();

        eagles.updateCoachPosition(name, newPosition);
    }

    private void searchCoachInteractive() {
        System.out.println("\n--- Search Coach ---");
        System.out.print("Enter coach name to search: ");
        String name = scanner.nextLine().trim();

        Coach coach = eagles.findCoach(name);
        if (coach != null) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("COACH FOUND:");
            System.out.println(coach);
            System.out.println("=".repeat(60));
        } else {
            System.out.println("✗ Coach not found!");
        }
    }

    public void start() {
        boolean running = true;
        System.out.println("\n╔" + "═".repeat(58) + "╗");
        System.out.println("║ Welcome to the Philadelphia Eagles Management System ║");
        System.out.println("╚" + "═".repeat(58) + "╝");

        while (running) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handlePlayersMenu();
                    break;
                case "2":
                    handleCoachesMenu();
                    break;
                case "3":
                    eagles.displayTeamInfo();
                    break;
                case "4":
                    running = false;
                    System.out.println("\nThank you for using the Eagles Management System!");
                    System.out.println("Go Eagles! 🦅\n");
                    break;
                default:
                    System.out.println("✗ Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        EaglesManagementSystem system = new EaglesManagementSystem();
        system.start();
    }
}
