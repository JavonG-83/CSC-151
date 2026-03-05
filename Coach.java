public class Coach {
    private String name;
    private String position; // Head Coach, Offensive Coordinator, Defensive Coordinator, etc.
    private int yearsExperience;
    private String specialty; // Offense, Defense, Special Teams, etc.

    public Coach(String name, String position, int yearsExperience, String specialty) {
        this.name = name;
        this.position = position;
        this.yearsExperience = yearsExperience;
        this.specialty = specialty;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public String getSpecialty() {
        return specialty;
    }

    // Setters
    public void setPosition(String position) {
        this.position = position;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    @Override
    public String toString() {
        return String.format("%s | Position: %s | Specialty: %s | Experience: %d years", 
                           name, position, specialty, yearsExperience);
    }
}
