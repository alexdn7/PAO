package arrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //arrays au dimensiune fixa
        int [] arr = new int[0];
        int [] arr2 = {2, 1, 7, 3, 5};
        int [] arr3 = new int[] {1, 2, 3, 4, 5};
        int [][] arr4 = {
                {1, 2, 3, 4},
                {2, 3},
                {5, 6, 7}
        };

        /*for(int i = 0; i <  arr2.length; i++) {
            System.out.println("Elementul cu valoare: " + arr2[i]);
        }* <=> */

        System.out.println();

        for(int i : arr2) {
            System.out.println("Elementul cu valoare: " + i);
        }

        System.out.println();

        Arrays.stream(arr2)
                .forEach(e -> System.out.println("Elementul cu valoare: " + e));

        System.out.println();

        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.deepToString(arr4));

        System.out.println();

        System.out.println("Before sorting: ");
        System.out.println(Arrays.toString(arr2));

        Arrays.sort(arr2);

        System.out.println("After sorting: ");
        System.out.println(Arrays.toString(arr2));

        System.out.println();
        System.out.println(Arrays.binarySearch(arr2, 3));

        arr = addElementToArray(arr, 8);
        System.out.println(Arrays.toString(arr));

        arr = deleteElemenFromArray(arr, 8);
        System.out.println(Arrays.toString(arr));
        //System.out.println(arr[6]); -> exception
    }

    private static int[] addElementToArray(int[] arr, int value) {
        int [] newArr = new int[arr.length + 1];

        /*
        for(int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        } <=> */

        System.arraycopy(arr, 0, newArr, 0, arr.length);

        newArr[newArr.length - 1] = value;
        return newArr;
    }

    private static int[] deleteElemenFromArray(int[] arr, int value) {
        return Arrays.stream(arr)
                .filter(e -> e != value)
                .toArray();
    }
}
