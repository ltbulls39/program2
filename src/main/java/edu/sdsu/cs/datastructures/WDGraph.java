package edu.sdsu.cs.datastructures;


import java.util.*;

public class WDGraph<V, E> implements IGraph<V, E> {
    private Set<V> nameSet;
    private Map<IVertex<V>, LinkedList<IEdge<E>>> graph;

    //    FIXME remove unused
    private Map<V, LinkedList<IEdge<E>>> map;
//    FIXME remove unused

    private int vertices;
    private int edges;



    public WDGraph() {
        map = new HashMap<>();
        nameSet = new HashSet<>();
        vertices = 0;
        edges = 0;

        //    FIXME remove unused
        graph = new HashMap<>();
        //    FIXME remove unused
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
        Map<IVertex<V>, Integer> distanceTable = initMap();
        Queue<IVertex<V>> priorityQueue = new PriorityQueue<>();
        distanceTable.put(start, 0);

        priorityQueue.offer(start);

        while (!priorityQueue.isEmpty()) {
            IVertex<V> current = priorityQueue.poll();
            if (distanceTable.get(current) != -1) {
                continue;
            }
//            TODO change this so it maps to its distance from the node
            distanceTable.put(current, 1);
            LinkedList<IEdge<E>> tempList = graph.get(current);
            for (IEdge<E> edge : tempList) {

            }
        }

        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void connectVertices(IVertex<V> start, IVertex<V> end, int weight) {
        E cost = (E) new Integer(weight);
        if (!nameSet.contains(start.getName()) || !nameSet.contains(end.getName())) {
            System.out.println("Cannot connect the two vertices since one or more of them are not in our graph yet");
            return;
        }


        IEdge<E> newEdge = new WeightedEdge<>(start, end, cost);
        LinkedList<IEdge<E>> tempList = graph.get(start);
        tempList.add(newEdge);
    }

    @Override
    public void addVertex(IVertex<V> toAdd) {
        V name = toAdd.getName();
        if (!nameSet.add(name)) {
            System.out.println("Cannot add " + name + " to our list, already in it");
            return;
        }

        graph.put(toAdd, new LinkedList<IEdge<E>>());
        vertices++;
    }

    @Override
    public void addEdge(IEdge<E> toAdd) {
        /**
         * Cases
         *  - StartNode is not in the map
         *  - EndNode is not in the map
         */

        if (!nameSet.contains(toAdd.getStartVertex().getName()))
            System.out.println("Given starting vertex is not actually in the graph yet");
        if (!nameSet.contains(toAdd.getEndVertex().getName()))
            System.out.println("Given ending vertex is not actually in the graph yet");

        LinkedList<IEdge<E>> list;
        list = graph.get(toAdd.getStartVertex());
        list.add(toAdd);
        edges++;
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

    private Map<IVertex<V>, Integer> initMap() {
        Map<IVertex<V>, Integer> unvisitedNodes = new HashMap<>();
        for (IVertex<V> vertex : graph.keySet()) {
            unvisitedNodes.put(vertex, -1);
        }
        return unvisitedNodes;
    }


}
