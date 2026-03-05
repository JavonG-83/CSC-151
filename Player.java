public class Player {
    private String name;
    private int number;
    private String position;
    private String status; // Active, Injured, Reserve, etc.

    public Player(String name, int number, String position, String status) {
        this.name = name;
        this.number = number;
        this.position = position;
        this.status = status;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("#%d - %s | Position: %s | Status: %s", number, name, position, status);
    }
}
