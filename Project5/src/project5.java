import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class project5 {
    public static void main(String[] args){

        /* File Read */

        File fileIn = new File("sample_input2.txt"/*args[0]*/);
        Scanner data = null;
        try {
            data = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> datas = new ArrayList<>();
        if (data != null) {
            while (data.hasNextLine()) {
                datas.add(data.nextLine());
            }
        }


        /* File Write */

        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream("sample_output2_my.txt"/*args[1]*/);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        System.setOut(fileOut);

        /* Making sense of Input */

        /* Initiating Graph */
        int V = Integer.parseInt(datas.get(0));
        int[][] graph = new int[V+8][V+8];

        for(int i =0; i<6; i++) {
            graph[0][i+1] = Integer.parseInt(datas.get(1).split(" ")[i]);
        }

        for(int i = 2; i<8; i++) {
            String current_str = datas.get(i).split(" ")[0];
            int current_int = Integer.parseInt(current_str.substring(1));

            int index = 1;
            while (index < datas.get(i).split(" ").length) {

                int dest_int;
                String dest_str = datas.get(i).split(" ")[index];
                if(dest_str.equals("KL")) {
                    dest_int = V + 7;
                } else {
                    dest_int = Integer.parseInt(dest_str.substring(1)) + 7;
                }
                index++;
                int capacity = Integer.parseInt(datas.get(i).split(" ")[index]);
                index++;
                graph[current_int+1][dest_int] = capacity;
            }

        }
        for(int i = 8; i<datas.size(); i++) {
            String current_str = datas.get(i).split(" ")[0];
            int current_int = Integer.parseInt(current_str.substring(1));

            int index = 1;
            while (index < datas.get(i).split(" ").length) {

                int dest_int;
                String dest_str = datas.get(i).split(" ")[index];
                if(dest_str.equals("KL")) {
                    dest_int = V + 7;
                } else {
                    dest_int = Integer.parseInt(dest_str.substring(1)) + 7;
                }
                index++;
                int capacity = Integer.parseInt(datas.get(i).split(" ")[index]);
                index++;
                graph[current_int+7][dest_int] = capacity;
            }

        }

        MaxFlow mp = new MaxFlow(V+8);
        System.out.println( mp.fordFulkerson(graph, 0, V+7));

    }
}
