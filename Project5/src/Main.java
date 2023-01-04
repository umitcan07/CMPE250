import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        /* File Read */

        File fileIn = new File(args[0]);
        Scanner data = null;
        try {
            data = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> datas = new ArrayList<>();
        while (data.hasNextLine()) {
            datas.add(data.nextLine());
        }


        /* File Write */

        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream(args[1]);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        System.setOut(fileOut);




    }
}
