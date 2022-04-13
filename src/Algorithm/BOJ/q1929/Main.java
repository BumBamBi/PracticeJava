package Algorithm.BOJ.q1929;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
        int N = sc.nextInt();
		int a[] = new int[N + 1];
		
		// 각 값을 저장할 배열 만들기
		// 0이면 소수, 1이면 소수가 아님
		for (int i = M; i <= N; i++){
            a[i] = 0;
        }
		a[1] = 1;
		
		// 2 부터 쭉 N까지 진행
		for (int i = 2; i <= N; i++) {
			// i의 j배수는 전부 1로 체크 (어떤값이 j로 나누어 떨어진다면, 소수가 아님)
			for (int j = 2; i * j <= N; j++) {
				a[i * j] = 1;
			}
		}

		// 출력
		for (int i = M; i <= N; i++){
            if(a[i] != 1){
                System.out.println(i);
            }
        }
		sc.close();
    }
}
