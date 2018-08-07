/** Interface file for P2
 * San Diego State University
 * Version: 1.1
 * note: Added addEdge method
 */
package edu.sdsu.cs.datastructures;

public interface IGraph<V, E> {

    /**
     * Provides the caller with a listing of the vertices in the graph.
     *
     * @return An Iterable object of IVertex objects
     */
    Iterable<IVertex<V>> vertices();

    /**
     * Returns a listing of the edges in the graph.
     *
     * @return An Iterable object of IEdge objects
     */
    Iterable<IEdge<E>> edges();

    /***
     * Provides the count of the edges in the graph.
     *
     * @return the number of edges
     */
    int numEdges();

    /***
     * Provides a count of the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    int numVertices();

    /**
     * Calculates the cost associated with moving between the indicated vertices
     * in the graph.
     *
     * @param start The key name of the starting vertex
     * @param end   The name of the destination vertex
     * @return The calculated cost associated with moving between the two
     * vertices in the graph
     */
    int minimumDistance(IVertex<V> start, IVertex<V> end);


    /**
     * Performs Dijkstra's Shortest path algorithm, using a priority queue, and
     * returns the list of vertex objects traversed along the path.
     *
     * @param start
     * @param end
     * @return
     */
    Iterable<IEdge<E>> shortestPath(IVertex<V> start, IVertex<V> end);

    /**
     * Creates an edge of the specified weight, if one does not yet exist,
     * between the two indicated vertices.
     *
     * @param start Initial vertex
     * @param end Terminal vertex
     * @param weight Cost of movement between nodes
     */
    void connectVertices(IVertex<V> start, IVertex<V> end, int weight);


    void addVertex(IVertex<V> toAdd);

    void addEdge(IEdge<E> toAdd);
}
