package Algorithm.BOJ.q1755;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int M, N;
	
	static final String[] table = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		// 문자열과 숫자를 저장할 클래스
		class Data implements Comparable<Data>{
			
			String s;
			int n;
			
			public Data(String s, int n) {
				this.s = s;
				this.n = n;
			}

			@Override
			public int compareTo(Data o) {
				return this.s.compareTo(o.s);
			}
		}
		
		// 사전순으로 정렬해줄 pq
		PriorityQueue<Data> pq = new PriorityQueue<Data>();
		
		// 입력
		for(int i=M; i<=N; i++) {
			pq.offer(new Data(int2str(i), i));
		}
		
		// 출력
		for(int i=M; i<=N;) {
			for(int j=0; j<10; j++, i++) {
				Data result = pq.poll();
				if(result == null) {
					sc.close();
					return;
				}else {
					System.out.print(result.n + " ");
				}
			}
			System.out.println();
		}
		
		sc.close();
		return;
	}

	private static String int2str(int n) {
		// 한자리수면 바로 리턴
		if(n<10) {
			return table[n];
		}else {
			String s = Integer.toString(n);
			StringBuilder sb = new StringBuilder();
			// 문장 길이 만큼 반복
			for(int i=0; i<s.length(); i++) {
				sb.append(table[s.charAt(i)-'0']);
				// 마지막 숫자가 아니라면 공백을 붙임
				if(i != s.length()-1) {
					sb.append(" ");
				}
			}
			return sb.toString();
		}
	}
}
