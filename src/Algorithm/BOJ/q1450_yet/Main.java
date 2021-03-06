package Algorithm.BOJ.q1450_yet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int C = sc.nextInt();

        int[] weights = new int[N+1];

        for (int i = 1; i <= N; i++) {
            weights[i] = sc.nextInt();
        }

        int[][] dp = new int[N+1][C+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                if (weights[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j] + weights[i], dp[i - 1][j - weights[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][C]);

        sc.close();
    }
}