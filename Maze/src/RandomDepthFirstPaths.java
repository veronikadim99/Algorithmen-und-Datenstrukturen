import java.util.*;

public class RandomDepthFirstPaths {
  	private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex
    private LinkedList<Integer> pathTo = new LinkedList<>();

    public RandomDepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
    }
	    
	public void randomDFS(Graph G) {
		randomDFS(G,s);
	}

    private void randomDFS(Graph G, int v) {

        //same as normal DFS
        //the only difference is that we shuffle the adj list
        Collections.shuffle(G.adj(v));
        marked[v] = true;
        for (int getVertex : G.adj(v)) {
            if (!marked[getVertex]) { this.edgeTo[getVertex]= v; randomDFS(G,getVertex); }
        }

    }

    public void randomNonrecursiveDFS(Graph G) {
		// TODO


    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * 
     * This method is different compared to the original one.
     */
    public List<Integer> pathTo(int v) {
        validateVertex(v);
        if(hasPathTo(v) == false){return null;} //if there is no path s-v; return null
        //if there is a path and v is not the source vertex
        //add v in index 0 in the list
        //add vertex to the pathTo list (recursive)
        //in the end add the source vertex (the endpoint)
        if(hasPathTo(v) == true){
            if(!pathTo.contains(v)){ pathTo.add(0,v);}
            int vertex = v; vertex = edgeTo[vertex];
            if(vertex != s){
                pathTo.add(vertex);
                pathTo(vertex);

            }else if( vertex == s) {pathTo.add(s);}
        }

        return pathTo;
    }
    
    public int [] edge() {
    	return edgeTo;
    }  

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}

