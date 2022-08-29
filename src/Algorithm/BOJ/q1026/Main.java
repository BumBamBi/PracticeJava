package Algorithm.BOJ.q1026;

import java.util.Scanner;

public class Main {
    static int N;
    static int[] A;
    static int[] B;
    static boolean[] flagA;
    static boolean[] flagB;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        A = new int[N + 1];
        B = new int[N + 1];
        flagA = new boolean[N + 1];
        flagB = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            B[i] = sc.nextInt();
        }


        int ans = 0;
        for (int i = 0; i < N; i++) {
            int maxIndex = maxIndexOfA();
            int minIndex = minIndexOfB();
            ans += (A[maxIndex] * B[minIndex]);
        }

        System.out.println(ans);

        sc.close();
    }

    public static int maxIndexOfA() {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < N; i++) {
            if (flagA[i] == false) {
                if (max <= A[i]) {
                    max = A[i];
                    index = i;
                }
            }
        }
        flagA[index] = true;
        return index;
    }

    public static int minIndexOfB() {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < N; i++) {
            if (flagB[i] == false) {
                if (min >= B[i]) {
                    min = B[i];
                    index = i;
                }
            }
        }
        flagB[index] = true;
        return index;
    }
}
