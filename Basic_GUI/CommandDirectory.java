import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.Scanner;

public class CommandDirectory {

    CommandDirectory()
    {
        int choice;
        Scanner input;
        input = new Scanner(System.in);

        do
        {
            System.out.println(1 + " List directory contents: ");
            System.out.println(2 + " Add student to directory: ");
            System.out.println(3 + " Display average age of students: ");
            System.out.println(4 + " Quit Program: ");

            choice = input.nextInt();

            if (choice == 1) {
                FileIn();
            }

            else if (choice == 2) {
                FileOut();
            }

            else if (choice == 3) {
                Average();
            }

            else if (choice == 4) {
                Finish();
            }

            else{System.out.println("Not a valid choice");}
        }  while (choice != 4);
    }

    //reads in file
    private static final String fileName = "data/directory.data";

    public void FileIn() {

        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            while (fileInput.hasNext()) {
                System.out.printf(fileInput.next() + "    " + fileInput.next() + "  " + fileInput.next() + "   " + fileInput.next() + "\n");
            }

            System.out.printf("\n");
    }

    //writes out to file
    public void FileOut() {
        PrintWriter fileOutput = null;

        try {
            fileOutput = new PrintWriter(new FileOutputStream("data/directory.data", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String First, Last;
        int Age;
        String Number;
        Scanner info;

        info = new Scanner(System.in);

        System.out.println(" Enter First Name: ");
        First = info.next();

        System.out.println(" Enter Last Name: ");
        Last = info.next();

        System.out.println(" Enter Age: ");
        Age = info.nextInt();

        System.out.println(" Enter Phone Number: ");
        Number = info.next();

        fileOutput.printf("%1s %15s %15d %15s\n", First, Last, Age, Number);

        System.out.println(" The following student has been added to the directory: \n");
        System.out.println(First  +" " + Last +", " + "age: " + Age + ", " + "phone: " + Number + "\n");

        fileOutput.close();
    }

    //caculates average age of data
    public void Average()
    {
        int count  = 0;
        int total = 0;

        try {
            Scanner fileInput = new Scanner(new FileReader(fileName));

            while(fileInput.hasNext())
            {
                fileInput.next();
                fileInput.next();
                total += fileInput.nextInt();
                fileInput.next();
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(" The average age of the students is: " + total/count + "\n");
    }

    public int Finish()

    {
        return 0;
    }

    public static void main(String arg[])
    {
        new CommandDirectory();
    }
}

