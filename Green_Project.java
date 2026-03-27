//Javon Green (March 2026)

import java.util.Scanner;
public class Green_Project{ public static void main(String[] args){ Scanner input = new Scanner(System.in);

//for navigating the CSV FILE
//String line, file = "GreenJ_ConPadEsti.csv";

//Concrete
String company, location;
double length, width, area, thickness, volume, costPerFoot, concreteCost;
//Labor
int workers;
double productivity, hourlyWage, crewHourlyWage, estimatedWorkHours, laborCost;

//RECEIVE Basic input and info
//company, location, length, width, thickness, workers, total labor hours,


System.out.print("Enter the company name: ");
company = input.nextLine();
System.out.print("Enter the location of the project: ");
location = input.nextLine();
System.out.print("");
System.out.print("Consider the following will all be measured in feet");
System.out.print("");
System.out.print("Enter the length of the area: ");
length = input.nextDouble();
System.out.print("Enter the width of the area: ");
width = input.nextDouble();
area = length * width;
System.out.print("Enter the thickness of the concrete: ");
thickness = input.nextDouble();
volume = area * thickness;
System.out.print("Enter the cost of concrete per cubic foot: ");
costPerFoot = input.nextDouble();
concreteCost = costPerFoot * volume;

//Next, We must determine the labor cost
//Get the amount of workers, hourlyWage, and productivity. Then, determine the manpower hours to find the labor cost
System.out.print("Enter the amount of workers: ");
workers = input.nextInt();
System.out.print("Enter the hourly wage per worker: ");
hourlyWage = input.nextDouble();
crewHourlyWage = workers * hourlyWage;
System.out.print("Enter the productivity rate (How much square feet of concrete your crew installs per hour): ");
productivity = input.nextDouble();
estimatedWorkHours = area / productivity;
laborCost = crewHourlyWage * estimatedWorkHours;
System.out.println("");

System.out.println("Company name: "+company);
System.out.println("Project Location: "+location);
System.out.println("Estimated total cost for labor: $"+laborCost);
System.out.println("Estimated Total cost for material: $"+concreteCost);
input.close();
}}