import java.util.*;

public class UberRouting {

    static final int V = 4;

    // ---------------- DIJKSTRA ----------------

    static int minDistance(int dist[], boolean visited[]) {

        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i=0;i<V;i++) {

            if(!visited[i] && dist[i] < min) {

                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    static void dijkstra(int graph[][], int src) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[src] = 0;

        for(int count=0; count<V-1; count++) {

            int u = minDistance(dist,visited);

            visited[u] = true;

            for(int v=0; v<V; v++) {

                if(!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Dijkstra Distances:");

        for(int i=0;i<V;i++) {

            System.out.println(
                    (char)('A'+i)
                            + " = "
                            + dist[i]);
        }
    }

    // ---------------- BELLMAN FORD ----------------

    static class Edge {

        int src,dest,weight;

        Edge(int s,int d,int w) {

            src=s;
            dest=d;
            weight=w;
        }
    }

    static void bellmanFord(ArrayList<Edge> edges,int src) {

        int dist[] = new int[V];

        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[src]=0;

        for(int i=1;i<V;i++) {

            for(Edge e : edges) {

                if(dist[e.src] != Integer.MAX_VALUE &&
                   dist[e.src] + e.weight < dist[e.dest]) {

                    dist[e.dest] =
                            dist[e.src] + e.weight;
                }
            }
        }

        // Negative Cycle Check

        for(Edge e : edges) {

            if(dist[e.src] != Integer.MAX_VALUE &&
               dist[e.src] + e.weight < dist[e.dest]) {

                System.out.println(
                        "Negative Cycle Detected");

                return;
            }
        }

        System.out.println("\nBellman Ford Distances:");

        for(int i=0;i<V;i++) {

            System.out.println(
                    (char)('A'+i)
                            + " = "
                            + dist[i]);
        }
    }

    public static void main(String[] args) {

        int graph[][] = {

                {0,4,2,7},
                {0,0,-1,3},
                {0,0,0,1},
                {0,0,0,0}
        };

        dijkstra(graph,0);

        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0,1,4));
        edges.add(new Edge(0,2,2));
        edges.add(new Edge(1,2,-1));
        edges.add(new Edge(1,3,3));
        edges.add(new Edge(2,3,1));
        edges.add(new Edge(0,3,7));

        bellmanFord(edges,0);
    }
}