import java.util.ArrayList;
import java.util.List;

public class PhiladelphiaEagles {
    private String teamName;
    private String founded;
    private List<Player> players;
    private List<Coach> coaches;

    public PhiladelphiaEagles() {
        this.teamName = "Philadelphia Eagles";
        this.founded = "1933";
        this.players = new ArrayList<>();
        this.coaches = new ArrayList<>();
        initializeDefaultRoster();
    }

    // Initialize with some famous Eagles players and coaches
    private void initializeDefaultRoster() {
        // Add some iconic Eagles players
        players.add(new Player("Jalen Hurts", 1, "Quarterback", "Active"));
        players.add(new Player("DeVonta Smith", 6, "Wide Receiver", "Active"));
        players.add(new Player("A.J. Brown", 11, "Wide Receiver", "Active"));
        players.add(new Player("Lane Johnson", 65, "Right Tackle", "Active"));
        players.add(new Player("Haason Reddick", 7, "Edge Rusher", "Active"));

        // Add some coaches
        coaches.add(new Coach("Nick Sirianni", "Head Coach", 5, "Offense"));
        coaches.add(new Coach("Sean Desai", "Defensive Coordinator", 3, "Defense"));
        coaches.add(new Coach("Charlie Walters", "Offensive Line Coach", 2, "Offense"));
    }

    // Player Management
    public void addPlayer(String name, int number, String position, String status) {
        players.add(new Player(name, number, position, status));
        System.out.println("✓ Player " + name + " added to the roster!");
    }

    public void removePlayer(String name) {
        players.removeIf(p -> p.getName().equalsIgnoreCase(name));
        System.out.println("✓ Player " + name + " removed from the roster!");
    }

    public void displayAllPlayers() {
        if (players.isEmpty()) {
            System.out.println("\nNo players in the roster.");
            return;
        }
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PHILADELPHIA EAGLES - PLAYERS ROSTER");
        System.out.println("=".repeat(60));
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("=".repeat(60));
    }

    public Player findPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    public void updatePlayerStatus(String name, String newStatus) {
        Player player = findPlayer(name);
        if (player != null) {
            player.setStatus(newStatus);
            System.out.println("✓ Updated " + name + "'s status to " + newStatus);
        } else {
            System.out.println("✗ Player not found!");
        }
    }

    // Coach Management
    public void addCoach(String name, String position, int yearsExperience, String specialty) {
        coaches.add(new Coach(name, position, yearsExperience, specialty));
        System.out.println("✓ Coach " + name + " added to the coaching staff!");
    }

    public void removeCoach(String name) {
        coaches.removeIf(c -> c.getName().equalsIgnoreCase(name));
        System.out.println("✓ Coach " + name + " removed from the coaching staff!");
    }

    public void displayAllCoaches() {
        if (coaches.isEmpty()) {
            System.out.println("\nNo coaches on staff.");
            return;
        }
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PHILADELPHIA EAGLES - COACHING STAFF");
        System.out.println("=".repeat(60));
        for (Coach coach : coaches) {
            System.out.println(coach);
        }
        System.out.println("=".repeat(60));
    }

    public Coach findCoach(String name) {
        for (Coach coach : coaches) {
            if (coach.getName().equalsIgnoreCase(name)) {
                return coach;
            }
        }
        return null;
    }

    public void updateCoachPosition(String name, String newPosition) {
        Coach coach = findCoach(name);
        if (coach != null) {
            coach.setPosition(newPosition);
            System.out.println("✓ Updated " + name + "'s position to " + newPosition);
        } else {
            System.out.println("✗ Coach not found!");
        }
    }

    // Team Info
    public void displayTeamInfo() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PHILADELPHIA EAGLES - ORGANIZATION INFO");
        System.out.println("=".repeat(60));
        System.out.println("Team Name: " + teamName);
        System.out.println("Founded: " + founded);
        System.out.println("Total Players: " + players.size());
        System.out.println("Total Coaches: " + coaches.size());
        System.out.println("=".repeat(60));
    }

    // Getters
    public String getTeamName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<Coach> getCoaches() {
        return new ArrayList<>(coaches);
    }
}
