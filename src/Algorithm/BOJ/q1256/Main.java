package Algorithm.BOJ.q1256;

import java.util.Scanner;

public class Main {
    static int N, M;
    static double K;
    static double[][] dp;
	static StringBuilder sb= new StringBuilder(); 
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		//N은 a개수, M은 z개수, K번째 문자열을 구해야함.
		//N, M은 100이하, K는 10억 이하의 수
		N= sc.nextInt();
		M= sc.nextInt();
        K = sc.nextDouble();

        dp = new double[N + 1][M + 1];

        // 재귀 (top-down)로 해결

		if(check(N, M)<K) {
			System.out.println("-1");
        } else {
            makeS(N, M, K);
            System.out.println(sb.toString());
        }
        
        sc.close();
	}
	
    // 개수 구하는 함수
    // 2/2는 2/1(aaz aza zaa) + 1/2(azz zaz zza) 뒤에 각 z와 a를 앞에 붙인 것
    // [i][j] = [i-1][j] + [i][j-1];
	public static double check(int a, int z) {
		if(a==0||z==0) return 1;
		if(dp[a][z]!=0) return dp[a][z];
		
		return dp[a][z]= Double.min(check(a-1, z)+check(a, z-1), 1000000001);
	}
	
	// 문자열 만드는 함수
    public static void makeS(int a, int z, double k) {
        // a가 0이면, 뒤에 전부 z로 채움
        if (a == 0) {
            for (int i = 0; i < z; i++)
                sb.append("z");
            return;
        }
        // z가 0이면, 뒤에 전부 a로 채움
        if (z == 0) {
            for (int i = 0; i < a; i++)
                sb.append("a");
            return;
        }
        
        // -------------------------------
        // 이분탐색느낌으로 탐색 진행 a로 시작하는 문자열/z로 시작하는 문자열
        // a를 하나 덜쓴 문자 개수를 구함
        double check = check(a - 1, z);
        // k보다 해당 문자열 개수이 작다면, 가장 앞 문자열이 z여야 함 (=이분탐색 뒤에 존재)
        if (k > check) {
            sb.append("z");
            // 앞에 z를 붙였으니, z를 하나 빼서 다음 재귀를 돌림
            // z로 시작해야하므로, k인자에 k-check를 통해 a까지 만들 수 있는 문자열의 개수는 빼서 전달( 이분탐색에서 start = mid 느낌)
            makeS(a, z - 1, k - check);
        }
        // k보다 만든 문자개수가 크거나 같다면, 가장 앞 문자열이 a여야 함 (=이분탐색 앞에 존재)
        else {
            sb.append("a");
            // 다시 앞문자열에 있는지 확인
            makeS(a - 1, z, k);
        }
    }
}
