package Algorithm.BOJ.q1769;

import java.util.Scanner;

public class Main {
    static String X;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        X = sc.nextLine();
        if (X.length() == 1) {
            int last = Integer.parseInt(X);
            System.out.println(0);
            if (last % 3 == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            func(X, 1);
        }

        sc.close();
    }
    
    public static void func(String str, int cnt) {
        int sum = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            sum += (str.charAt(i) - '0');
        }
        if (sum < 10) {
            if (sum % 3 == 0) {
                System.out.println(cnt);
                System.out.println("YES");
            } else {
                System.out.println(cnt);
                System.out.println("NO");
            }
        } else {
            func(Integer.toString(sum), cnt+1);
        }
    }
}
