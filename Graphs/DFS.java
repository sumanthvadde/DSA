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


public class DFS {
    static ArrayList<Integer> DFSresult = new ArrayList<>();

    public static void DFSImpl(Graph graph, int v, boolean visited[]) {
        visited[v] = true;

        DFSresult.add(v);
        for (int u : graph.adjList.get(v)) {
            if (!visited[u]) {
                DFSImpl(graph, u, visited);
            }
        }
    }

    public static void DFSImplStack(Graph graph, int v, boolean visited[]) {
        Stack<Integer> stack = new Stack<>();

        stack.push(v);

        while (!stack.isEmpty()) {
            v = stack.pop();
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            System.out.print(v + " ");
 
            List<Integer> adjList = graph.adjList.get(v);
            for (int i = adjList.size() - 1; i >= 0; i--)
            {
                int u = adjList.get(i);
                if (!visited[u]) {
                    stack.push(u);
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        // List of graph edges as per the above diagram
        List<Edge> edges = Arrays.asList(
                // Notice that node 0 is unconnected
                new Edge(1, 2), new Edge(1, 7), new Edge(1, 8), new Edge(2, 3),
                new Edge(2, 6), new Edge(3, 4), new Edge(3, 5), new Edge(8, 9),
                new Edge(8, 12), new Edge(9, 10), new Edge(9, 11)
            );
 
        // total number of nodes in the graph (labelled from 0 to 12)
        int n = 13;
 
        // build a graph from the given edges
        Graph graph = new Graph(edges, n);
 
        // to keep track of whether a vertex is discovered or not
        boolean[] discovered = new boolean[n];
 
        // Perform DFS traversal from all undiscovered nodes to
        // cover all connected components of a graph
        for (int i = 0; i < n; i++)
        {
            if (!discovered[i]) {
                DFSImpl(graph, i, discovered);
            }
        }
    }
}
