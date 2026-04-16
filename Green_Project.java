//Javon Green (March 2026)

import java.util.Scanner;
public class Green_Project{ public static void main(String[] args){ Scanner input = new Scanner(System.in);

//for navigating the CSV FILE
//String line, file = "GreenJ_ConPadEsti.csv";

//Basics
String company, location;
double totalCost;
int choice; //This variable is used during measuerment selection, 
//Concrete
double length, width, area, thickness, volume, costPerUnit, concreteCost;
//Labor
int workers;
double productivity, hourlyWage, crewHourlyWage, estimatedWorkHours, laborCost;

//RECEIVE Basic input and info
//company, location, length, width, thickness, workers, total labor hours,

//Get the basic info on the project
System.out.print("Enter the company name: ");
company = input.nextLine();
System.out.print("Enter the location of the project: ");
location = input.nextLine();
System.out.print("");

//Get some info on the conrete costs, by calculating the volume of concrete installed and the cost per cubic foot
    //AREA
System.out.print("How will your measure the area? type 1 for yards; type 2 for feet: ");
choice = input.nextInt();
System.out.print("Enter the length of the area: ");
length = input.nextDouble();
System.out.print("Enter the width of the area: ");
width = input.nextDouble();
area = length * width;
if (choice == 1){area = area * 9;}
    //THICKNESS
System.out.print("How will you measure the thickness? type 1 for inches; type 2 for feet:");
choice = input.nextInt();
System.out.print("Enter the thickness of the concrete: ");
thickness = input.nextDouble();
if (choice == 1){thickness = thickness/12;}
volume = area * thickness;
    //PRICING
System.out.println("How is the concrete priced? Type 1 for cubic yards; Type 2 for cubic feet");
choice = input.nextInt();
System.out.print("Enter the cost of concrete per cubic unit: ");
costPerUnit = input.nextDouble();
if (choice == 1){costPerUnit = costPerUnit / 27;}

concreteCost = costPerUnit * volume; //Get the total cost for the concrete

//Next, We must determine the labor cost
//Determine the labor costs, by multipling the crew's hourly wage to the estimated work hours based on their productivity
System.out.print("Enter the amount of workers: ");
workers = input.nextInt();
System.out.print("Enter the hourly wage per worker: ");
hourlyWage = input.nextDouble();
crewHourlyWage = workers * hourlyWage;
System.out.print("Enter the productivity rate (How much square feet of concrete your crew installs per hour): ");
productivity = input.nextDouble();
estimatedWorkHours = area / productivity;
laborCost = crewHourlyWage * estimatedWorkHours; //Get the labor costs
System.out.println("");

//Get the total cost by adding both the concrete and labor costs together
totalCost = concreteCost + laborCost;

//Display the results
System.out.println("Company name: "+company);
System.out.println("Project Location: "+location);
System.out.println("Estimated total cost for concrete: $"+concreteCost);
System.out.println("Estimated total cost for labor: $"+laborCost);
System.out.println("Overall costs estimation: $"+totalCost);

//Next, implemetn the csv file integration, to create a log system. Record the following:
//Company,Location,Area,Volume,Workers,WorkHours,ManpowerHours,LaborCost,TotalCost

input.close();
}}