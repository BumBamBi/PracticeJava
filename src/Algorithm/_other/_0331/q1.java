package Algorithm._other._0331;

import java.util.Scanner;

public class q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] yellow = new int[n + 1];
        int[] blue = new int[n + 1];

        yellow[1] = 1;
        blue[1] = 1;
        blue[2] = 1;
        
        for (int i = 2; i <= n; i++) {
            // yellow[i] = i;
            yellow[i] = yellow[i - 1] + blue[i - 1];
            
            // blue[i] = i - 1;
            blue[i] = yellow[i - 1];
        }

        System.out.println(yellow[n] + blue[n]);

        sc.close();
    }
}
