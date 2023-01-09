import java.util.Arrays;

public class Main {

    public static void selectionSort(int[] array) {

        for(int i = 1; i<array.length; i++) {
            int m = i;
            while(array[m] < array[m-1]) {
                int tmp = array[m];
                array[m] = array[m-1];
                array[m-1] = tmp;
                m--;
            }

        }


    }

    public static void main(String[] args) {
        int[] array = {1,4,5,6,8,34,7,35,66,12,90,1,45};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }


}
