package Algorithm.BOJ.q1277_yet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int N, W;
    static float M;
    static float[] distance;
    static ArrayList<ArrayList<Integer>> adjList;
    static boolean[] visited;

    // static class Point {
    //     int y;
    //     int x;
    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        W = sc.nextInt();
        M = sc.nextFloat();

        distance = new float[N];
        Arrays.fill(distance, Float.MAX_VALUE);

        adjList = new ArrayList<ArrayList<Integer>>(N);
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        int firstX = sc.nextInt();
        int firstY = sc.nextInt();
        distance[0] = 0;

        for (int i = 1; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            float dist = (float) Math.sqrt(Math.pow(firstX - x, 2) + Math.pow(firstY - y, 2));
            distance[i] = dist;
        }

        for (int i = 0; i < W; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        djkstra(0);

        for (int i = 0; i < N; i++) {
            System.out.println(distance[i]);
        }

        sc.close();
    }
    
    static void djkstra(int start){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        // 자기 자신으로 가는 비용x
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        // 큐를 계속 돌리면서 확인
        while(!pq.isEmpty() && remainedVertexCnt > 0){
            Node current = pq.poll();

            // 이미 방문했으면 PASS
            if(visited[current.id]){
                continue;
            }
            visited[current.id] = true;
            remainedVertexCnt--;

            // 해당 정점과 연결된 간선들을 전부 확인
            int len = adjList.get(current.id).size();
            for (int i = 0; i<len; i++) {
                Node next = adjList.get(current.id).get(i);
                
                // 4. cost가 더 작을때만 갱신하고 PQ에 넣기
                if (current.cost + next.cost < distance[next.id]) {
                    distance[next.id] = current.cost + next.cost;
                    pq.add(new Node(next.id, distance[next.id]));
                }
            }
        }
    }
}
