package Algorithm.BOJ.q1389;

import java.util.Scanner;

public class Main {
    static int N, M;
    static boolean[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        
        matrix = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            matrix[from][to] = true;
            matrix[to][from] = true;
        }

        for (int i = 0; i < N; i++) {
            boolean[] flag = new boolean[N]; // boolean? int?

            func();
        }

        sc.close();
    }
}
