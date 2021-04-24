package com.company;

public class SimlpleNumEratosfenLessMemory {
    static long m0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    //static int MAX_VAL = 2_000_000;
    static int MAX_VAL = Integer.MAX_VALUE;
    static long sqrtMaxVal = (long) Math.sqrt(MAX_VAL);
    static byte[] arrGrid = new byte[MAX_VAL / 16 + 1];

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();


        setStatusNoSimple(0); // it's for 2
        //индексы соответствуют числам
        for (long i = 3; i <= sqrtMaxVal; i += 2) {
            if (getStatus(i) == 0) { //if simple
                for (long j = (i * i); j <= MAX_VAL; j = j + i) { // oll eveh it
                    setStatusNoSimple(j);
                }
            }
        }

        long searchTime = System.currentTimeMillis() - t0;
        long useMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - m0;
        System.out.printf("Time search: %d.%d sec\n", searchTime / 1000, searchTime % 1000);
        System.out.printf("Use memory: %d.%d Mb \n", useMemory / (1024 * 1024), (useMemory % (1024 * 1024))/1024);
        long count = getCountSimple();
        System.out.println("Found: " + count);
        if (count < 50) {
            showSimpleNumbers();
        }
    }

    private static boolean isOdd(long index) {
        return (index & 1) == 1;
    }

    private static long getOddIndex(long index) {
        // idnex / 2
        return index >>> 1;
    }

    private static byte getStatus(long address) {
        if (isOdd(address)) {
            address = getOddIndex(address);
            int indexInArr = getIndexByteInArr(address);
            int shift = getIndexBitInByte(address);
            return (byte) (((arrGrid[indexInArr] >> (shift)) & 1));
        }
        return 1; // if address Even then it's not Simplle number
    }

    private static void setStatusNoSimple(long address) {
        if (isOdd(address)) {
            address = getOddIndex(address);
            int indexInArr = getIndexByteInArr(address);
            arrGrid[indexInArr] = (byte) (arrGrid[indexInArr] | (1 << getIndexBitInByte(address)));
        }
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
        System.out.print("2,");
        for (long i = 3; i <= MAX_VAL; i += 2) {
            if (getStatus(i) == 0) {
                System.out.print(i + ",");
            }
        }
    }

    private static long getCountSimple() {
        long result = 1;
        for (long i = 3; i <= MAX_VAL; i += 2) {
            if (getStatus(i) == 0) {
                result++;
            }
        }
        return result;
    }
}