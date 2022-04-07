package Algorithm.BOJ.q2579;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] stair = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stair[i] = sc.nextInt();
        }

        // --------------------------------

        // 1계단 오른 후 -> 다음은 무조건 2계단
        // 2계단 오른 후 -> 다음은 1계단 or 2계단

        // [][1] : 1계단 오른 경우 / [][2] : 2계단 오른 경우 / [][0] 둘 중 더 큰 값
        int[][] dp = new int[N + 1][3];
        
        // dp[0][0~2]는 0으로 자동 초기화
        dp[1][0] = dp[1][1] = dp[1][2] = stair[1];
        
        // [][1]은 전 칸을 2칸으로 뛰어온 값에서 와야하고,
        // [][2]는 전전칸을 1칸으로 뛰어온 값(이번에 2칸으로) vs 전전칸을 2칸으로 뛰어온 값(이번칸을 1칸으로) 중 큰 값
        for (int i = 2; i <= N; i++) {
            dp[i][1] = dp[i-1][2] + stair[i];
            dp[i][2] = Math.max(dp[i - 2][1], dp[i - 2][2]) + stair[i];
            dp[i][0] = Math.max(dp[i][1], dp[i][2]);
        }

        System.out.println(dp[N][0]);

        sc.close();
    }
}

// 마지막에만 max해주는것이 더 나을듯
// 공간/시간 복잡도 측면에서 훨~신 나을듯ㅋㅋ