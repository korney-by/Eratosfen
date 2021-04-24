package com.company;

import static java.lang.String.format;

public class SimpleNumEratosfen {

    //static int MAX_VAL = 100; //2_000_000;
    static int MAX_VAL = Integer.MAX_VALUE;
    static long sqrtMaxVal = (long) Math.sqrt(MAX_VAL);
    static byte[] arrGrid = new byte[MAX_VAL / 8 + 1];

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        //индексы соответствуют числам
        for (long i = 2; i < sqrtMaxVal; i++) {
            if (getStatus(i) == 0) {
                for (long j = (i * i); j <=MAX_VAL; j = j + i) {
                    setStatusSimple(j);
                }
            }
        }
        // подсчет памяти
        //Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()

        long searchTime = System.currentTimeMillis() - t0;
        System.out.printf("Time search: %d.%d sec\n", searchTime / 1000, searchTime % 1000);
        long count = getCountSimple();
        System.out.println("Found: " + count);
        if (count < 50) {
            showSimpleNumbers();
        }
    }

    private static byte getStatus(long address) {
        int indexInArr = getIndexByteInArr(address);
        int shift = getIndexBitInByte(address);
        return (byte) (((arrGrid[indexInArr] >> (shift)) & 1));
    }

    private static void setStatusSimple(long address) {
        int indexInArr = getIndexByteInArr(address);
        arrGrid[indexInArr] = (byte) (arrGrid[indexInArr] | (1 << getIndexBitInByte(address)));
    }

    private static int getIndexByteInArr(long address) {
        // address / 8;
        return (int) (address >>> 3);
    }

    private static int getIndexBitInByte(long address) {
        // address % 8;
        return (int) (address ^ ((address >>> 3) << 3));
    }

    private static void showSimpleNumbers() {
        for (long i = 2; i < MAX_VAL; i++) {
            if (getStatus(i) == 0) {
                System.out.print(i + ",");
            }
        }
    }

    private static String getTime(long timeInMsec) {
        return String.format("%d.%d сек", timeInMsec / 1000, timeInMsec % 1000);
    }

    private static long getCountSimple() {
        long result = 0;
        for (long i = 2; i <= MAX_VAL; i++) {
            if (getStatus(i) == 0) {
                result++;
            }
        }
        return result;
    }
}