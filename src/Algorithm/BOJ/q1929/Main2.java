package Algorithm.BOJ.q1929;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        byte arr[] = new byte[(N / 8) + 1];

        // 각 값을 저장할 배열 만들기
        // 0이면 소수, 1이면 소수가 아님
        for (int i = 0; i < (N / 8) + 1; i++) {
            // 8비트 모두 1로 채우기 위해 down casting활용
            arr[i] = (byte) Integer.MAX_VALUE;
        }

        // 0과 1은 소수가 아니므로 0으로 비트 체크
        arr[0] &= ~(3);

        // 모든 수 접근하기
        for (int i = 2; i <= N; i++) {
            // 해당 비트가 1이라면,
            if ((arr[i >>> 3] & (1 << (i & 7))) > 0) {
                // 해당 값의 n배수에 있는 비트를 0으로 변경
                for (int j = 2*i; j <= N; j += i) {
                    arr[j >>> 3] &= ~(1 << (j & 7));
                }
            }
        }

        // 출력
        for (int i = M; i <= N; i++) {
            // 해당 비트가 1이라면 출력
            if ((arr[i >>> 3] & (1 << (i & 7))) > 0) {
                System.out.println(i);
            }
        }
        sc.close();
    }
}
