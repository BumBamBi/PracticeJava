package Algorithm.SWEA.q1249_yet;

import java.util.Scanner;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;

            N = sc.nextInt();
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String s = sc.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = s.charAt(j);
                }
            }
            // ------------------------------------------


            // ------------------------------------------
            System.out.println("#" + tc + answer);
        }

        sc.close();
    }
}
