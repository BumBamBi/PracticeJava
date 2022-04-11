package Algorithm.BOJ.q1149;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // 모든 집이 연속되는 색이 없도록
        
        // dp[i][j] : i번째 집을 j색으로 칠한 경우 최소값을 나타냄 
        int[][] dp = new int[N][3];

        // 첫 집을 각각의 색으로 칠할 경우의 비용
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        // 2번째 집부터 마지막 집까지 반복
        for (int i = 1; i < N; i++) {
            // 각 색깔로 해당 집을 칠 함
            for (int j = 0; j < 3; j++) {
                // 해당 색으로 해당 집을 칠하기 위한 최소 값을 구함
                // 이전에, 이번에 내가 칠할 색(j)가 아닌색으로 칠한 두 경우 중 작은 값 + 이번에 내가 칠할 색
                dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + arr[i][j];
            }
        }

        // 마지막으로 3개의 경우 중 가장 작은 경우
        int result = Math.min(dp[N - 1][0], dp[N - 1][1]);
        result = Math.min(result, dp[N - 1][2]);

        System.out.println(result);

        sc.close();
    }
}
