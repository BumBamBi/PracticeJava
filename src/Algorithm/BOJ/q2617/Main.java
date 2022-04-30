package Algorithm.BOJ.q2617;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visit;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        list = new ArrayList<ArrayList<Integer>>(N);
        dp = new int[N + 1][2];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<Integer>());
        }

        int half = (N + 1) / 2;

        for (int i = 0; i < M; i++) {
            int heavy = sc.nextInt();
            int light = sc.nextInt();

            list.get(heavy).add(light);
        }

        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            //깊이우선탐색할때마다, 내려감
            DFS(i, i);
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (dp[i][0] >= half || dp[i][1] >= half) {
                result++;
            }
        }

        System.out.println(result);
        sc.close();
    }
    
    static void DFS(int current, int start) {
		
		visit[current] = true;
		
		for(int next : list.get(current))
			if(!visit[next]) {
				dp[start][0]++; //0 : 나보다 가벼운 것
				dp[next][1]++;//1 : 나보다 무거운 것
				DFS(next, start);
			}
	}
}
