import java.util.*;

public class Maze {
    private final int N;
    private Graph M;
    public int startnode;
    private int edge;


    public Maze(int N, int startnode) {

        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M = new Graph(N * N);
        this.startnode = startnode;
        buildMaze();
    }

    public Maze(In in) {
        this.M = new Graph(in);
        this.N = (int) Math.sqrt(M.V());
        this.startnode = 0;
    }


    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        // TODO
        if (edge != 2 * N * N - 2 * N) {
            edge++;
            M.adj(v).add(w);
            M.adj(w).add(v);
        }


    }

    /**
     * Returns true if there is an edge between 'v' and 'w'
     *
     * @param v
     * @param w
     * @return true or false
     */
    public boolean hasEdge(int v, int w) {
        // TODO
        if ((v == w) && M.adj(v).contains(v)) { return true; }
        if (M.adj(v).contains(w)) { return true; }
        else if (M.adj(v).contains(w) && M.adj(w).contains(v)) { return true; }
        else { return false; }


    }

    /**
     * Builds a grid as a graph.
     *
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
        // TODO
        Graph G = new Graph(N * N);
        boolean checkIf = false;
        boolean checkSecondIf = false;
        //System.out.print(N*N + " vertices, " + edge + " edges " + "\n");
        for (int v = 0; v < N; v++) {
            for (int w = 0; w < N; w++) {
                checkIf = (v != N - 1) ? true : false;  //check if it is not the last one
                int vertex1 = w + v * N;
                int vertex2 = w + (v + 1) * N;
                int vertex3 = (w + v * N) + 1;
                checkSecondIf = (w != N - 1) ? true : false; //check if it is not the last one
                if (checkIf == true) {
                    G.addEdge(vertex1, vertex2);
                }  //add vertex1 and vertex2 to the graph
                if (checkSecondIf == true) {
                    G.addEdge(vertex1, vertex3);
                }  //add vertex1 and vertex3 to the graph


            }

        }
        return G;

    }

    private void buildMaze() {
        // TODO
        //make a grid
        //make new Path and random dfs for the path
        Graph newGraph = mazegrid();
        RandomDepthFirstPaths newPath = new RandomDepthFirstPaths(M, 0);
        newPath.randomDFS(newGraph);


        //iterate threw all elements
        //if there is the same edge in the grid
        //and in the path between elements
        //add the edge to graph M
        int j = 1;
        while (j < newPath.edge().length) {
            if (newGraph.adj(j).contains(newPath.edge()[j])) {
                M.addEdge(j, newPath.edge()[j]);
//            }
                j++;
            }

        }
    }


    public List<Integer> findWay(int v, int w){
		// TODO
        //if there is a path, return it
        //if there is not, return null
        DepthFirstPaths thePath = new DepthFirstPaths(M,w); thePath.dfs(M);
        boolean checkIf;
        checkIf = (!thePath.hasPathTo(v)) ? true : false;
        if(checkIf == true ) { return thePath.pathTo(v);}
        else{return null;}
    }
    
    public Graph M() {
    	return M;
    }

    public static void main(String[] args) {
        // FOR TESTING

    }


}




