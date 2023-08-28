package Graphs;
import java.util.*;

class Edge {
    int source, dest;

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }
}

class Graph {
    List<List<Integer>> adjList = null;
    
    Graph(List<Edge> edges, int n) {
        adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            int src = edge.source;
            int dest = edge.dest;

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}


public class BFS {

    public static void BFSImpl(Graph graph, int v, boolean visited[]) {
        Queue<Integer> q = new ArrayDeque<>();
        visited[v] = true;

        q.add(v);

        while (!q.isEmpty()) {
            v = q.poll();

            for (int u : graph.adjList.get(v)) {
                if (!visited[v]) {
                    visited[u] = true;
                    q.add(u);
                }
            }
        }
    }

    public static void main(String[] args) {
        // List of graph edges as per the above diagram
        List<Edge> edges = Arrays.asList(
                new Edge(1, 2), new Edge(1, 3), new Edge(1, 4), new Edge(2, 5),
                new Edge(2, 6), new Edge(5, 9), new Edge(5, 10), new Edge(4, 7),
                new Edge(4, 8), new Edge(7, 11), new Edge(7, 12)
        // vertex 0, 13, and 14 are single nodes
        );

        // total number of nodes in the graph (labelled from 0 to 14)
        int n = 15;

        // build a graph from the given edges
        Graph graph = new Graph(edges, n);

        // to keep track of whether a vertex is discovered or not
        boolean[] discovered = new boolean[n];

        // Perform BFS traversal from all undiscovered nodes to
        // cover all connected components of a graph
        for (int i = 0; i < n; i++) {
            if (discovered[i] == false) {
                // start BFS traversal from vertex `i`
                BFSImpl(graph, i, discovered);
            }
        }
    }
}