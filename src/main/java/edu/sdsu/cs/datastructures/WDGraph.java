package edu.sdsu.cs.datastructures;


import java.util.*;

public class WDGraph<V, E> implements IGraph<V, E> {
    private Map<IVertex<V>, LinkedList<IEdge<E>>> graph;

    private int vertices;
    private int edges;



    public WDGraph() {
        graph = new HashMap<>();
        vertices = 0;
        edges = 0;
    }


    @Override
    public Iterable<IVertex<V>> vertices() {
        final Set<IVertex<V>> set = graph.keySet();
        return new Iterable<IVertex<V>>() {
            @Override
            public Iterator<IVertex<V>> iterator() {
                return set.iterator();

            }
        };
    }

    @Override
    public Iterable<IEdge<E>> edges() {
        return new Iterable<IEdge<E>>() {
            @Override
            public Iterator<IEdge<E>> iterator() {
                Set<IVertex<V>> set = graph.keySet();
                List<IEdge<E>> edgeList = new LinkedList<>();

                for (IVertex<V> vertex : set) {
                    LinkedList<IEdge<E>> tempList = graph.get(vertex);
                    for (IEdge<E> edge : tempList) {
                        ((LinkedList<IEdge<E>>) edgeList).addFirst(edge);
                    }
                }

                return edgeList.iterator();
            }
        };
    }

    @Override
    public int numEdges() {
        return edges;
    }

    @Override
    public int numVertices() {
        return vertices;
    }

    @Override
    public int minimumDistance(IVertex<V> start, IVertex<V> end) {
        return 0;
    }

    @Override
    public Iterable<IEdge<E>> shortestPath(IVertex<V> start, IVertex<V> end) {
        return null;
    }

    @Override
    public void connectVertices(IVertex<V> start, IVertex<V> end, int weight) {

    }

    @Override
    public void addVertex(IVertex<V> toAdd) {
//        TODO check if toAdd is already in the list
        graph.put(toAdd, new LinkedList<IEdge<E>>());
        vertices++;
    }

    @Override
    public void addEdge(IEdge<E> toAdd) {

        WeightedEdge<E> temp = ((WeightedEdge<E>)toAdd);
        LinkedList<IEdge<E>> list;

//        Case: Start is actually in the map
        if (graph.containsKey(temp.startVertex)) {
            list = graph.get(temp.startVertex);
            list.add(toAdd);
            edges++;
        }

//        Case: Start is not in the map TODO

    }


    @Override
    public String toString() {
        Set<IVertex<V>> set = graph.keySet();
        String ret = "";

        for (IVertex vertex : set) {
            ret += "Key: " + vertex + " | Value: " + graph.get(vertex) + "\n";
        }

        return ret;
    }





}
