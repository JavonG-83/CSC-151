//Javon Green (March 2026)
//Create a program that asks for a list of purchases, does some claculatiosn on the array, and displays the final details

import java.util.Scanner;
public class RerunA_BudgetTrackerLite {
    public static void main(String[] args){

    Scanner prompt = new Scanner(System.in);

    //DECLARE\\
        double max=0, min=0, total=0, average;

    //INPUT\\    
    System.out.print("How many items have you purchased? ");
    int count = prompt.nextInt();
    double[] items = new double[count];
        for (int i = 0; i<count; i++){
            System.out.println("Enter the price of item #"+(i+1)+": ");
            items[i] = prompt.nextDouble();
        }
    //CALCULATE\\
    max=items[0];
    min=items[0];
    for (int i = 0; i<count; i++){
        total += items[i];
        if(items[i] > max){
            max = items[i];
        }
        if (items[i] < min){
            min = items[i];
        }
    }
    average = total / count;

    //OUTPUT\\
    System.out.println("Amount: "+count);
    System.out.println("Total: "+total);
    System.out.println("Highest: "+max);
    System.out.println("Lowest: "+min);
    System.out.println("Average: "+average);

     prompt.close();
}}