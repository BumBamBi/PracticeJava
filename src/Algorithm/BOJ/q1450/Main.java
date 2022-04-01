package Algorithm.BOJ.q1450;

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
                if (dp[i - 1][j - weights[i]] + weights[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + weights[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(dp[i][j]);
            }
            System.out.println("");
        }

        System.out.println(dp[N][C]);

        sc.close();
    }
}