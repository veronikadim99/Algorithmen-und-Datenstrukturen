import java.util.ArrayList;
import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO

        this.s = s;
        dist = new double[G.V()]; parent = new int[G.V()];


        TopologicalWD getStack = new TopologicalWD(G);
        getStack.dfs(s);

        Stack<Integer> newRev = new Stack<>();
        newRev = getStack.order();


        double INF = Double.POSITIVE_INFINITY;

        for (int i = 0; i < G.V(); i++) { dist[i] = INF; }

        dist[this.s] = 0.0;

        while(newRev.size() != 0){
            for (DirectedEdge nodeW : G.incident(newRev.pop())) { relax(nodeW);}

        }
    }








    public void relax(DirectedEdge e) {
        // TODO

        int nodeV = e.from(); int nodeW = e.to();
        int check = (dist[nodeW] > dist[nodeV] + e.weight()) ? 1:0;
        if(check == 1){parent[nodeW] = nodeV; dist[nodeW] = dist[nodeV] + e.weight();}

    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (! hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

