import java.util.*;

public class SpotifyCDN {

    static int V = 4;

    // BFS
    static void bfs(int graph[][], int start) {

        boolean visited[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {

            int node = q.poll();

            System.out.print(node + " ");

            for (int i = 0; i < V; i++) {

                if (graph[node][i] != 0 && !visited[i]) {

                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    // DFS
    static void dfs(int graph[][], int node, boolean visited[]) {

        visited[node] = true;

        System.out.print(node + " ");

        for (int i = 0; i < V; i++) {

            if (graph[node][i] != 0 && !visited[i]) {

                dfs(graph, i, visited);
            }
        }
    }

    static class Edge implements Comparable<Edge> {

        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    static int find(int parent[], int i) {

        if (parent[i] == i)
            return i;

        return find(parent, parent[i]);
    }

    static void union(int parent[], int x, int y) {

        int xset = find(parent, x);
        int yset = find(parent, y);

        parent[xset] = yset;
    }

    static void kruskal(ArrayList<Edge> edges) {

        Collections.sort(edges);

        int parent[] = new int[V];

        for (int i = 0; i < V; i++)
            parent[i] = i;

        int cost = 0;

        System.out.println("\n\nMST Edges:");

        for (Edge e : edges) {

            int x = find(parent, e.src);
            int y = find(parent, e.dest);

            if (x != y) {

                System.out.println(
                        e.src + " - " +
                        e.dest + " : " +
                        e.weight);

                cost += e.weight;

                union(parent, x, y);
            }
        }

        System.out.println("Total Cost = " + cost);
    }

    public static void main(String[] args) {

        int graph[][] = {
                {0,4,2,5},
                {4,0,0,3},
                {2,0,0,1},
                {5,3,1,0}
        };

        System.out.println("BFS:");
        bfs(graph,0);

        System.out.println("\n\nDFS:");

        boolean visited[] = new boolean[V];

        dfs(graph,0,visited);

        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0,1,4));
        edges.add(new Edge(0,2,2));
        edges.add(new Edge(0,3,5));
        edges.add(new Edge(1,3,3));
        edges.add(new Edge(2,3,1));

        kruskal(edges);
    }
}