import java.io.FileReader; //Opens
import java.io.BufferedReader; //Reads
import java.io.IOException; // Helps with Bugs

public class RerunC_ClassGrader {
    public static void main(String[] args) {

        //Declare
        String fileName = "RerunC.csv"; //represents the file being read. ensure it's spelled exactly the same, and has the ".csv" thing at the end
        String line; //Will help store data between the lines;
        int total = 0, count = 0;
        
        //Gather
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { //This one actually opens the file
            br.readLine(); //This reads the first line and ignores it

            while ((line = br.readLine()) != null) { //Loop that stops after it hits an empty line
                String[] data = line.split(","); //This splits the lines at the comma, turning it into a array/structure
                int score = Integer.parseInt(data[1]); //converts the string based number variables into integers

                total += score;
                count++;
            }

        } catch (IOException e) { //Notifies if a file had an error
            System.out.println("Error reading file.");
        }
        
        //Calcualte & Display
        if (count > 0) {
            double average = (double) total / count;
            System.out.println("Average score: " + average);
        } else {
            System.out.println("No data found.");
        }
    }
}