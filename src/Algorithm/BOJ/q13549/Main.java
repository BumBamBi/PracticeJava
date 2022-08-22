package Algorithm.BOJ.q13549;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, K;
    static int ans = Integer.MAX_VALUE;
    static int[] flags = new int[100001];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        K = sc.nextInt();

        Arrays.fill(flags, -1);

        flags[N] = 0;

        func(N, 0);

        System.out.println(ans);
        sc.close();
    }
    
    public static void func(int cur, int cnt) {
        multi(cur, cnt);
        
        for (int i = 0; i <= 100000; i++) {
            if (flags[i] != -1) {
                plus(i + 1, cnt + 1);
                multi(i, cnt + 1);
            }
        }
    }
    
    public static void multi(int input, int cnt) {
        int temp = input;

        while (temp > 0 && temp <= 100000) {
            if (flags[temp] > cnt) {
                flags[temp] = cnt;
            }
            temp += input;
        }
    }

    public static void plus(int input, int cnt) {
        if (flags[input+1] > cnt) {
            flags[input+1] = cnt;
        }
    }

    public static void minus(int input, int cnt) {
        if (flags[input-1] > cnt) {
            flags[input-1] = cnt;
        }
    }
}
