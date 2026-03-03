//Javon Green (March 2026)
//Create  program that asks for temperatures, calculates some stuff, and outputs things

import java.util.Scanner;
public class RerunB_TempTracker{ public static void main(String[] args){
Scanner prompt = new Scanner(System.in);
//DECLARE\\

double total=0, average, range, max, min;
int count;

//INPUT\\
System.out.print("Enter the amount of readings you have: ");
count = prompt.nextInt();
double[] readings = new double[count];
for (int i=0; i<count; i++){
    System.out.print("Enter temperature reading #"+(i+1)+": ");
    readings[i] = prompt.nextDouble();
}

//CALCULATE\\
max = readings[0];
min = readings[0];
for (int i=0; i<count; i++){
    total += readings[i];
    if (max<readings[i]){
        max = readings[i];
    }
    if (min>readings[i]){
        min = readings[i];
    }
}
average = total/count;
range = max-min;

//OUTPUT\\
System.out.println("");
for(int i=0; i<count;i++){
    System.out.println("Reading #"+(i+1)+": °"+readings[i]);
}
System.out.println("");
System.out.println("Hottest Temperature Recorded: °"+max);
System.out.println("Coolest Temperature Recorded: °"+min);
System.out.println("Average Temp: °"+average);
System.out.println("Temperature Range: °"+range);

prompt.close();
}}