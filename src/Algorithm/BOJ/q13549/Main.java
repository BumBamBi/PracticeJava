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
    
    public static void func(int cur, int cnt){
        if (cur == K) {
            if (ans > cur) {
                
            }
        } else {
            if (flags[cur] != 0) {
                
            }
        }
    }
}
