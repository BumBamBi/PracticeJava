package Algorithm._other._0331;

import java.util.Scanner;

public class q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] memo = new int[n + 1];

        memo[1] = 2;
        memo[2] = 5;

        for (int i = 3; i <= n; i++) {
            if (i % 2 == 1) {
                memo[i] = memo[i - 1] * 2;
            } else {
                memo[i] = memo[i - 1] * 2 + 1;
            }
        }

        System.out.println(memo[n]);

        sc.close();
    }
}
