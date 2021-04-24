package com.company;

public class Main {
    public static void main(String[] args) {

        //int MAX_VAL = Integer.MAX_VALUE;
        int MAX_VAL = 2_000_000;
        long t0 = System.currentTimeMillis();
        byte[] a = new byte[MAX_VAL];
        //нулевой индекс соответствует 1, первый - 2
        int count = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == 0) {
                count++;
                for (int j = (i + i + 1); j < a.length; j = j + (i + 1)) {
                    a[j] = 1;
                }
            }
        }
        System.out.println((System.currentTimeMillis() - t0));
        System.out.println(count);

//        for (int i = 1; i < MAX_VAL; i++) {
//            if (a[i] == 0) {
//                System.out.print(i+1 + ",");
//            }
//        }

    }
}
