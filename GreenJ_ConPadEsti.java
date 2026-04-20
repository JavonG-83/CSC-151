// Javon Green
// Create a concrete pad estimator, that estimates the total cost of both concrete and labor for different projects
// After each project, save the Work & Manpower hours toa csv, which can be read to keep a form of 'log' on previous projects.

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class GreenJ_ConPadEsti {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String fileName = "GreenJ_ConPadEsti.csv";


        int menuChoice;
        do { //Simple menu setup
            System.out.println("\n=== Concrete Estimator Menu ===");
            System.out.println("1. Create New Estimate");
            System.out.println("2. View Work Hours Log");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            menuChoice = input.nextInt();
            input.nextLine(); // clears the buffer

            if (menuChoice == 1) {
                // Basics
                String company, location;
                int choice; //used whenever we want to ask a simple question
                double finalCost;
                // Concrete
                double length, width, area, thickness, volume, adjustedVolume, costPerUnit, concreteCost, wasteFactor = 0.05;
                // Labor
                int workers;
                double productivity, hourlyWage, crewHourlyWage, estimatedWorkHours, laborCost, manpowerHours;

                // INPUT
                System.out.println("\n---Creating new estimate---");
                System.out.print("Enter the company name: ");
                company = input.nextLine();
                System.out.print("Enter the location of the project: ");
                location = input.nextLine();
                // AREA
                System.out.println("\n--Volume--");
                System.out.print("How will you measure the area? (1 = yards, 2 = feet): ");
                choice = input.nextInt();
                System.out.print("Enter length: ");
                length = input.nextDouble();
                System.out.print("Enter width: ");
                width = input.nextDouble();
                area = length * width;
                if (choice==1){area = area*9; }
                // THICKNESS
                System.out.print("How will you measure the thickness? (1 = inches, 2 = feet): ");
                choice = input.nextInt();
                System.out.print("Enter thickness: ");
                thickness = input.nextDouble();
                if (choice==1){thickness= thickness/12; }
                volume = area*thickness;
                // WASTE CALCULATION (5%)
                adjustedVolume = volume*(1 + wasteFactor);

                // PRICING
                System.out.println("\n--Pricing--");
                System.out.print("What unit is being priced? (1 = cubic yards, 2 = cubic feet): ");
                choice = input.nextInt();
                System.out.print("Cost per unit: ");
                costPerUnit = input.nextDouble();
                if (choice == 1){costPerUnit = costPerUnit/27; }
                concreteCost = costPerUnit * adjustedVolume;

                // LABOR
                System.out.println("\n--Worker Information--");
                System.out.print("Enter the number of workers: ");
                workers = input.nextInt();
                System.out.print("Enter the hourly wage per worker: ");
                hourlyWage = input.nextDouble();
                crewHourlyWage = workers * hourlyWage;
                System.out.print("Enter your workers productivity (how many square ft of concrete is installed per hour?): ");
                productivity = input.nextDouble();
                estimatedWorkHours = area/productivity;
                laborCost = crewHourlyWage*estimatedWorkHours;
                manpowerHours = workers*estimatedWorkHours;
                finalCost = laborCost+concreteCost;

                // OUTPUT
                System.out.println("\n===Estimate Summary===");
                System.out.println("Company: " + company);
                System.out.println("Location: " + location);
                System.out.println("Base Volume: " + volume);
                System.out.println("Adjusted Volume (with 5% waste): " + adjustedVolume);
                System.out.println("Concrete Cost (with waste): $" + concreteCost);
                System.out.println("Labor Cost: $" + laborCost);
                System.out.println("Work Hours: " + estimatedWorkHours);
                System.out.println("Manpower Hours: " + manpowerHours);
                System.out.println("Final Total Cost: $" + finalCost);

                // CSV
                try (FileWriter writer = new FileWriter(fileName, true)) {
                    java.io.File file = new java.io.File(fileName);
                    if (file.length() == 0) { writer.append("WorkHours,ManpowerHours\n"); }
                    writer.append(String.valueOf(estimatedWorkHours)).append(",");
                    writer.append(String.valueOf(manpowerHours)).append("\n");
                    System.out.println("Data saved to CSV.");
                } catch (IOException e) { System.out.println("Error writing to CSV."); }

            } else if (menuChoice == 2) {
                System.out.println("\n--- Work Hours Log ---");
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    br.readLine(); // skip header
                    while ((line = br.readLine()) != null) { System.out.println(line); }
                } catch (IOException e) { System.out.println("No data found or error reading file."); }
                }
        } while (menuChoice != 3);
        System.out.println("Program exited.");
        input.close();
    }
}