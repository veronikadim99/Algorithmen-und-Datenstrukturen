/******************************************************************************
 *  Compilation:  javac DepthFirstPaths.java
 *  Execution:    java DepthFirstPaths G s
 *  Dependencies: Graph.java
 ******************************************************************************/

/**
 *  The {@code DepthFirstPaths} class represents a data type for finding
 *  paths from a source vertex <em>s</em> to every other vertex
 *  in an undirected graph.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Each call to {@link #hasPathTo(int)} takes constant time;
 *  each call to {@link #pathTo(int)} takes time proportional to the length
 *  of the path.
 *  It uses extra space (not including the graph) proportional to <em>V</em>.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>   
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  
 *  DISCLAIMER:
 *  These methods have been partly adjusted to fit the excersise.
 */
import sun.security.provider.certpath.Vertex;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;


public class DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    private final int s;         // source vertex
    private Queue<Integer> preorder;   // vertices in preorder
    private Queue<Integer> postorder;  // vertices in postorder
    private int lastedge = 0;
    private int lastedgeNon = 0; //last edge variable for non recursive
    private int count = 0;
    private LinkedList<Integer> pathTo = new LinkedList<>();




    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        postorder = new LinkedList<Integer>();
        preorder  = new LinkedList<Integer>();
        validateVertex(s);
    }
    
    public void dfs(Graph G) {
    	dfs(G, s);
    }


    private void dfs(Graph G, int v) {


           if(preorder.size() != G.V()) {
               preorder.add(v);
               if (v == s) {
                   edgeTo[v] = 0; //if c is the source vertex, the last edge is the source vertex
                   distTo[v] = 0; //there are no edges, because it is the source vertex
               }else {
                    if(preorder.peek() != v) {  //looks if the last element is not = to v
                        edgeTo[v] = lastedge; //the v place in the array is equals to the last edge
                        distTo[v] = distTo[edgeTo[v]]+1;
                    }else{
                        edgeTo[v] = edgeTo[lastedge];
                        distTo[v] = distTo[edgeTo[v]]+1; //add one edge to the distance of the last edge
                    }

               }

           }
        //if size of preorder list is not equal to the number of vertices
        //add the node to the list
            marked[v] = true;
            for (int w : G.adj(v)) {
                lastedge = v;
                if (!marked[w]) {
                    System.out.println(distTo[v]);
                    dfs(G, w);
                }
            }
        //if size of postorder list is not equal to the number of vertices
        //add the node to the list
            if(postorder.size() != G.V()){
                postorder.add(v);

            }



    }
    public void nonrecursiveDFS(Graph G) {

        preorder.add(s);

    	//TODO: Zeilen hinzufuegen
        marked = new boolean[G.V()];
        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                lastedgeNon = v;
                if (!marked[w]) {
                    // discovered vertex w for the first time
                    marked[w] = true;
                    //if preorderlist is not full, add the element that is going to be pushed
                    if(preorder.size() != G.V()){ preorder.add(w); }
                    edgeTo[w] = lastedgeNon;
                    //add one edge to the distance of the last edge
                    distTo[w] = distTo[edgeTo[w]]+1;

                    stack.push(w);
                }
            }
            else {
                stack.pop();
                //if postorderlist is not full, add the element that is going to be pushed
                if(postorder.size() != G.V()){ postorder.add(v); }
            }


        }
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
		// TODO
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
        //return pathTo list
        return pathTo;
    }
    
    /**
     * Returns the vertices in postorder. This method differs from the original. 
     * @return the vertices in postorder, as a queue of vertices
     */
    public Queue<Integer> post() {
        return postorder;
    }

    /**
     * Returns the vertices in preorder. This method differs from the original.
     * @return the vertices in preorder, as a queue of vertices 
     */
    public Queue<Integer> pre() {
        return preorder;
    }   
    
    /**
     * Returns the class variable edgeTo. This method differs from the original.
     * @return egdeTo
     */
    public int [] edge() {
    	return edgeTo;
    }
    
    /**
     * Returns the class variable distTo. This method differs from the original.
     * @return distTo
     */
    public int [] dist() {
    	return distTo;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}

