package Algorithm.BOJ.q2629;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int M, N, total;
	static int[] weights;
	static boolean[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 추 무게 저장
		M = sc.nextInt();
		weights = new int[M];
		// 최대 무게
		total = 0;
		for(int i=0; i<M; i++) {
			weights[i] = sc.nextInt();
			total += weights[i];
		}
		Arrays.sort(weights);

		dp = new boolean[M+1][40001];
		
		// 무게 계산
        func(0, 0);

		// 계산할 추 입력들
		N = sc.nextInt();
		for(int i=0; i<N; i++) {
			int input = sc.nextInt();
			
			// 가능 여부에 따라 출력
			if(dp[M][input]) {
				System.out.print("Y ");
			}else {
				System.out.print("N ");
			}
		}
		sc.close();
	}

	private static void func(int i, int w) {
		// 배열초과 or 최대값을 초과 or 이미 true라면 끝
		if (i > M || w > total || dp[i][w]) {
			return;
		}
		dp[i][w] = true;
		
		// out of range 방지
		if (i < M) {
			// 더하거나
			func(i+1, w+weights[i]);
			// 빼거나
			func(i+1, Math.abs(w-weights[i]));
		}
		// 아무것도 안하거나
		func(i+1, w);
	}
}