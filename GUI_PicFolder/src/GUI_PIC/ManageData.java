package GUI_PIC;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class ManageData
{
    private static final String fileName = "data/Images.data";

    //reads in file with URLs to initial array
    public ArrayList<String> FileIn()
    {
        ArrayList<String> URL = new ArrayList<String>();

        Scanner fileInput = null;

        try{
            fileInput = new Scanner(new FileReader(fileName));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        while(fileInput.hasNext())
        {
            URL.add(fileInput.next());
        }

        return URL;
    }

    //method to write out to file
    public void Fileout(String inputURL)
    {
        PrintWriter fileOutput = null;

        if(!inputURL.isEmpty()) {

            try {
                fileOutput = new PrintWriter(new FileOutputStream("data/Images.data", true));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            fileOutput.print(inputURL);

            fileOutput.close();
        }
    }
}
