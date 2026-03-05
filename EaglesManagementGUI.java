import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EaglesManagementGUI extends JFrame {
    private PhiladelphiaEagles eagles;
    private JTabbedPane tabbedPane;
    private JPanel playersPanel;
    private JPanel coachesPanel;
    private JPanel teamInfoPanel;

    public EaglesManagementGUI() {
        eagles = new PhiladelphiaEagles();
        initializeFrame();
    }

    private void initializeFrame() {
        setTitle("Philadelphia Eagles Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        tabbedPane = new JTabbedPane();
        createPlayersTab();
        createCoachesTab();
        createTeamInfoTab();

        tabbedPane.addTab("Players", playersPanel);
        tabbedPane.addTab("Coaches", coachesPanel);
        tabbedPane.addTab("Team Info", teamInfoPanel);

        add(tabbedPane);
        setVisible(true);
    }

    private void createPlayersTab() {
        playersPanel = new JPanel(new BorderLayout(10, 10));
        playersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel for adding players
        JPanel addPlayerPanel = new JPanel(new GridBagLayout());
        addPlayerPanel.setBorder(BorderFactory.createTitledBorder("Add New Player"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel numberLabel = new JLabel("Jersey #:");
        JTextField numberField = new JTextField(5);
        JLabel positionLabel = new JLabel("Position:");
        JTextField positionField = new JTextField(15);
        JLabel statusLabel = new JLabel("Status:");
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Active", "Injured", "Reserve", "Practice Squad"});

        gbc.gridx = 0;
        gbc.gridy = 0;
        addPlayerPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        addPlayerPanel.add(nameField, gbc);
        gbc.gridx = 2;
        addPlayerPanel.add(numberLabel, gbc);
        gbc.gridx = 3;
        addPlayerPanel.add(numberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addPlayerPanel.add(positionLabel, gbc);
        gbc.gridx = 1;
        addPlayerPanel.add(positionField, gbc);
        gbc.gridx = 2;
        addPlayerPanel.add(statusLabel, gbc);
        gbc.gridx = 3;
        addPlayerPanel.add(statusCombo, gbc);

        JButton addButton = new JButton("Add Player");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        addPlayerPanel.add(addButton, gbc);

        // Middle panel for displaying players
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Players Roster"));
        JTextArea playersDisplay = new JTextArea(15, 50);
        playersDisplay.setEditable(false);
        playersDisplay.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(playersDisplay);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for operations
        JPanel operationsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        operationsPanel.setBorder(BorderFactory.createTitledBorder("Player Operations"));

        JLabel searchLabel = new JLabel("Search Player:");
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JButton refreshButton = new JButton("Refresh List");

        JLabel removeLabel = new JLabel("Remove Player:");
        JTextField removeField = new JTextField(15);
        JButton removeButton = new JButton("Remove");

        JLabel statusUpdateLabel = new JLabel("Update Status:");
        JTextField updateNameField = new JTextField(15);
        JComboBox<String> newStatusCombo = new JComboBox<>(new String[]{"Active", "Injured", "Reserve", "Practice Squad"});
        JButton updateStatusButton = new JButton("Update");

        operationsPanel.add(searchLabel);
        operationsPanel.add(searchField);
        operationsPanel.add(searchButton);
        operationsPanel.add(refreshButton);
        operationsPanel.add(new JSeparator());
        operationsPanel.add(removeLabel);
        operationsPanel.add(removeField);
        operationsPanel.add(removeButton);
        operationsPanel.add(new JSeparator());
        operationsPanel.add(statusUpdateLabel);
        operationsPanel.add(updateNameField);
        operationsPanel.add(newStatusCombo);
        operationsPanel.add(updateStatusButton);

        // Button listeners
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int number = Integer.parseInt(numberField.getText().trim());
                String position = positionField.getText().trim();
                String status = (String) statusCombo.getSelectedItem();

                if (name.isEmpty() || position.isEmpty()) {
                    JOptionPane.showMessageDialog(playersPanel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                eagles.addPlayer(name, number, position, status);
                refreshPlayersDisplay(playersDisplay);
                nameField.setText("");
                numberField.setText("");
                positionField.setText("");
                JOptionPane.showMessageDialog(playersPanel, "Player added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(playersPanel, "Jersey number must be a valid integer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchButton.addActionListener(e -> {
            String name = searchField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(playersPanel, "Please enter a player name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Player player = eagles.findPlayer(name);
            if (player != null) {
                playersDisplay.setText("SEARCH RESULT:\n" + "=".repeat(60) + "\n" + player.toString());
            } else {
                JOptionPane.showMessageDialog(playersPanel, "Player not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> refreshPlayersDisplay(playersDisplay));

        removeButton.addActionListener(e -> {
            String name = removeField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(playersPanel, "Please enter a player name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            eagles.removePlayer(name);
            refreshPlayersDisplay(playersDisplay);
            removeField.setText("");
            JOptionPane.showMessageDialog(playersPanel, "Player removed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        updateStatusButton.addActionListener(e -> {
            String name = updateNameField.getText().trim();
            String newStatus = (String) newStatusCombo.getSelectedItem();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(playersPanel, "Please enter a player name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            eagles.updatePlayerStatus(name, newStatus);
            refreshPlayersDisplay(playersDisplay);
            updateNameField.setText("");
            JOptionPane.showMessageDialog(playersPanel, "Status updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        playersPanel.add(addPlayerPanel, BorderLayout.NORTH);
        playersPanel.add(displayPanel, BorderLayout.CENTER);
        playersPanel.add(operationsPanel, BorderLayout.SOUTH);

        refreshPlayersDisplay(playersDisplay);
    }

    private void refreshPlayersDisplay(JTextArea display) {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(70)).append("\n");
        sb.append("PHILADELPHIA EAGLES - PLAYERS ROSTER\n");
        sb.append("=".repeat(70)).append("\n");

        java.util.List<Player> players = eagles.getPlayers();
        if (players.isEmpty()) {
            sb.append("No players in the roster.\n");
        } else {
            for (Player player : players) {
                sb.append(player.toString()).append("\n");
            }
        }
        sb.append("=".repeat(70)).append("\n");
        display.setText(sb.toString());
    }

    private void createCoachesTab() {
        coachesPanel = new JPanel(new BorderLayout(10, 10));
        coachesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel for adding coaches
        JPanel addCoachPanel = new JPanel(new GridBagLayout());
        addCoachPanel.setBorder(BorderFactory.createTitledBorder("Add New Coach"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel positionLabel = new JLabel("Position:");
        JTextField positionField = new JTextField(15);
        JLabel experienceLabel = new JLabel("Years Experience:");
        JTextField experienceField = new JTextField(5);
        JLabel specialtyLabel = new JLabel("Specialty:");
        JComboBox<String> specialtyCombo = new JComboBox<>(new String[]{"Offense", "Defense", "Special Teams", "General"});

        gbc.gridx = 0;
        gbc.gridy = 0;
        addCoachPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        addCoachPanel.add(nameField, gbc);
        gbc.gridx = 2;
        addCoachPanel.add(positionLabel, gbc);
        gbc.gridx = 3;
        addCoachPanel.add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addCoachPanel.add(experienceLabel, gbc);
        gbc.gridx = 1;
        addCoachPanel.add(experienceField, gbc);
        gbc.gridx = 2;
        addCoachPanel.add(specialtyLabel, gbc);
        gbc.gridx = 3;
        addCoachPanel.add(specialtyCombo, gbc);

        JButton addButton = new JButton("Add Coach");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        addCoachPanel.add(addButton, gbc);

        // Middle panel for displaying coaches
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Coaching Staff"));
        JTextArea coachesDisplay = new JTextArea(15, 50);
        coachesDisplay.setEditable(false);
        coachesDisplay.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(coachesDisplay);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for operations
        JPanel operationsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        operationsPanel.setBorder(BorderFactory.createTitledBorder("Coach Operations"));

        JLabel searchLabel = new JLabel("Search Coach:");
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JButton refreshButton = new JButton("Refresh List");

        JLabel removeLabel = new JLabel("Remove Coach:");
        JTextField removeField = new JTextField(15);
        JButton removeButton = new JButton("Remove");

        JLabel posUpdateLabel = new JLabel("Update Position:");
        JTextField updateNameField = new JTextField(15);
        JTextField newPositionField = new JTextField(15);
        JButton updatePositionButton = new JButton("Update");

        operationsPanel.add(searchLabel);
        operationsPanel.add(searchField);
        operationsPanel.add(searchButton);
        operationsPanel.add(refreshButton);
        operationsPanel.add(new JSeparator());
        operationsPanel.add(removeLabel);
        operationsPanel.add(removeField);
        operationsPanel.add(removeButton);
        operationsPanel.add(new JSeparator());
        operationsPanel.add(posUpdateLabel);
        operationsPanel.add(updateNameField);
        operationsPanel.add(newPositionField);
        operationsPanel.add(updatePositionButton);

        // Button listeners
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String position = positionField.getText().trim();
                int experience = Integer.parseInt(experienceField.getText().trim());
                String specialty = (String) specialtyCombo.getSelectedItem();

                if (name.isEmpty() || position.isEmpty()) {
                    JOptionPane.showMessageDialog(coachesPanel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                eagles.addCoach(name, position, experience, specialty);
                refreshCoachesDisplay(coachesDisplay);
                nameField.setText("");
                positionField.setText("");
                experienceField.setText("");
                JOptionPane.showMessageDialog(coachesPanel, "Coach added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(coachesPanel, "Years of experience must be a valid integer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchButton.addActionListener(e -> {
            String name = searchField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(coachesPanel, "Please enter a coach name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Coach coach = eagles.findCoach(name);
            if (coach != null) {
                coachesDisplay.setText("SEARCH RESULT:\n" + "=".repeat(70) + "\n" + coach.toString());
            } else {
                JOptionPane.showMessageDialog(coachesPanel, "Coach not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> refreshCoachesDisplay(coachesDisplay));

        removeButton.addActionListener(e -> {
            String name = removeField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(coachesPanel, "Please enter a coach name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            eagles.removeCoach(name);
            refreshCoachesDisplay(coachesDisplay);
            removeField.setText("");
            JOptionPane.showMessageDialog(coachesPanel, "Coach removed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        updatePositionButton.addActionListener(e -> {
            String name = updateNameField.getText().trim();
            String newPosition = newPositionField.getText().trim();
            if (name.isEmpty() || newPosition.isEmpty()) {
                JOptionPane.showMessageDialog(coachesPanel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            eagles.updateCoachPosition(name, newPosition);
            refreshCoachesDisplay(coachesDisplay);
            updateNameField.setText("");
            newPositionField.setText("");
            JOptionPane.showMessageDialog(coachesPanel, "Position updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        coachesPanel.add(addCoachPanel, BorderLayout.NORTH);
        coachesPanel.add(displayPanel, BorderLayout.CENTER);
        coachesPanel.add(operationsPanel, BorderLayout.SOUTH);

        refreshCoachesDisplay(coachesDisplay);
    }

    private void refreshCoachesDisplay(JTextArea display) {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(70)).append("\n");
        sb.append("PHILADELPHIA EAGLES - COACHING STAFF\n");
        sb.append("=".repeat(70)).append("\n");

        java.util.List<Coach> coaches = eagles.getCoaches();
        if (coaches.isEmpty()) {
            sb.append("No coaches on staff.\n");
        } else {
            for (Coach coach : coaches) {
                sb.append(coach.toString()).append("\n");
            }
        }
        sb.append("=".repeat(70)).append("\n");
        display.setText(sb.toString());
    }

    private void createTeamInfoTab() {
        teamInfoPanel = new JPanel(new BorderLayout(10, 10));
        teamInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a panel for team info display
        JPanel infoDisplayPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        infoDisplayPanel.setBorder(BorderFactory.createTitledBorder("Team Information"));

        JLabel teamNameLabelText = new JLabel("Team Name:");
        JLabel teamNameValue = new JLabel(eagles.getTeamName());
        teamNameValue.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel foundedLabelText = new JLabel("Founded:");
        JLabel foundedValue = new JLabel("1933");
        foundedValue.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel totalPlayersLabelText = new JLabel("Total Players:");
        JLabel totalPlayersValue = new JLabel(String.valueOf(eagles.getPlayers().size()));
        totalPlayersValue.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel totalCoachesLabelText = new JLabel("Total Coaches:");
        JLabel totalCoachesValue = new JLabel(String.valueOf(eagles.getCoaches().size()));
        totalCoachesValue.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel stadiumLabelText = new JLabel("Home Stadium:");
        JLabel stadiumValue = new JLabel("Lincoln Financial Field");
        stadiumValue.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel cityLabelText = new JLabel("City:");
        JLabel cityValue = new JLabel("Philadelphia, Pennsylvania");
        cityValue.setFont(new Font("Arial", Font.BOLD, 14));

        infoDisplayPanel.add(teamNameLabelText);
        infoDisplayPanel.add(teamNameValue);
        infoDisplayPanel.add(foundedLabelText);
        infoDisplayPanel.add(foundedValue);
        infoDisplayPanel.add(totalPlayersLabelText);
        infoDisplayPanel.add(totalPlayersValue);
        infoDisplayPanel.add(totalCoachesLabelText);
        infoDisplayPanel.add(totalCoachesValue);
        infoDisplayPanel.add(stadiumLabelText);
        infoDisplayPanel.add(stadiumValue);
        infoDisplayPanel.add(cityLabelText);
        infoDisplayPanel.add(cityValue);

        // Create a panel with summary statistics
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBorder(BorderFactory.createTitledBorder("Team Summary"));
        JTextArea summaryDisplay = new JTextArea(20, 50);
        summaryDisplay.setEditable(false);
        summaryDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(summaryDisplay);
        statsPanel.add(scrollPane, BorderLayout.CENTER);

        // Refresh button
        JButton refreshButton = new JButton("Refresh Info");
        refreshButton.addActionListener(e -> {
            totalPlayersValue.setText(String.valueOf(eagles.getPlayers().size()));
            totalCoachesValue.setText(String.valueOf(eagles.getCoaches().size()));
            updateTeamSummary(summaryDisplay);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);

        teamInfoPanel.add(infoDisplayPanel, BorderLayout.NORTH);
        teamInfoPanel.add(statsPanel, BorderLayout.CENTER);
        teamInfoPanel.add(buttonPanel, BorderLayout.SOUTH);

        updateTeamSummary(summaryDisplay);
    }

    private void updateTeamSummary(JTextArea summary) {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(60)).append("\n");
        sb.append("PHILADELPHIA EAGLES - ORGANIZATION SUMMARY\n");
        sb.append("=".repeat(60)).append("\n\n");
        sb.append("Team Statistics:\n");
        sb.append("-".repeat(60)).append("\n");
        sb.append("Total Players: ").append(eagles.getPlayers().size()).append("\n");
        sb.append("Total Coaches: ").append(eagles.getCoaches().size()).append("\n\n");

        sb.append("ACTIVE PLAYERS:\n");
        sb.append("-".repeat(60)).append("\n");
        int activeCount = 0;
        for (Player p : eagles.getPlayers()) {
            if ("Active".equalsIgnoreCase(p.getStatus())) {
                sb.append(p).append("\n");
                activeCount++;
            }
        }
        if (activeCount == 0) {
            sb.append("None\n");
        }

        sb.append("\nINJURED PLAYERS:\n");
        sb.append("-".repeat(60)).append("\n");
        int injuredCount = 0;
        for (Player p : eagles.getPlayers()) {
            if ("Injured".equalsIgnoreCase(p.getStatus())) {
                sb.append(p).append("\n");
                injuredCount++;
            }
        }
        if (injuredCount == 0) {
            sb.append("None\n");
        }

        sb.append("\nCOACHING STAFF:\n");
        sb.append("-".repeat(60)).append("\n");
        for (Coach c : eagles.getCoaches()) {
            sb.append(c).append("\n");
        }

        sb.append("\n" + "=".repeat(60)).append("\n");
        summary.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EaglesManagementGUI());
    }
}
