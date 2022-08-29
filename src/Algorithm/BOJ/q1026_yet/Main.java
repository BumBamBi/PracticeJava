package Algorithm.BOJ.q1026_yet;

import java.util.Scanner;

public class Main {
    static int N;
    static int[] A;
    static int[] B;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        A = new int[N+1];
        B = new int[N+1];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            B[i] = sc.nextInt();
        }

        

        sc.close();
    }
}
