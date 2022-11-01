import java.io.*;
import java.util.NoSuchElementException;


public class Project1 {
    public static void main(String[] args) throws FileNotFoundException {

        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream(args[1]);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        System.setOut(fileOut);

        FactoryImpl myF = new FactoryImpl();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            while (line != null) {
                String[] ls = line.split(" ");
                switch (ls[0]) {

                    case "AF":
                        myF.addFirst(new Product(Integer.parseInt(ls[1]), Integer.parseInt(ls[2])));
                        break;

                    case "AL":
                        myF.addLast(new Product(Integer.parseInt(ls[1]), Integer.parseInt(ls[2])));
                        break;

                    case "A":
                        try {
                            myF.add(Integer.parseInt(ls[1]), new Product(Integer.parseInt(ls[2]), Integer.parseInt(ls[3])));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "RF":
                        try {
                            System.out.println(myF.removeFirst());
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "RL":
                        try {
                            System.out.println(myF.removeLast());
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "RI":
                        try {
                            System.out.println(myF.removeIndex(Integer.parseInt(ls[1])));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "RP":
                        try {
                            System.out.println(myF.removeProduct(Integer.parseInt(ls[1])));
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "F":
                        try {
                            System.out.println(myF.find(Integer.parseInt(ls[1])));
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "G":
                        try {
                            System.out.println(myF.get(Integer.parseInt(ls[1])));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "U":
                        try {
                            System.out.println(myF.update(Integer.parseInt(ls[1]), Integer.parseInt(ls[2])));

                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "FD":
                        System.out.println(myF.filterDuplicates());
                        break;

                    case "R":
                        myF.reverse();
                        System.out.println(myF);
                        break;

                    case "P":
                        System.out.println(myF);
                        break;

                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}