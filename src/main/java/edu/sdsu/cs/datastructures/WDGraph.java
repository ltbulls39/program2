package edu.sdsu.cs.datastructures;


import java.util.*;

public class WDGraph<V, E> implements IGraph<V, E> {
    private Set<V> nameSet;
    private Map<IVertex<V>, LinkedList<IEdge<E>>> graph;
    private int vertices;
    private int edges;

    public WDGraph() {
        nameSet = new HashSet<>();
        vertices = 0;
        edges = 0;

        graph = new LinkedHashMap<>();
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
        Iterable<IEdge<E>> iterator = shortestPath(start, end);
        int distance = 0;
        for (IEdge<E> edge : iterator) {
            distance += (Integer) edge.getCost();
        }

        return distance;
    }

    private Iterable<IEdge<E>> returnIterable(final LinkedList<IEdge<E>> returnEdges) {
        return new Iterable<IEdge<E>>() {
            @Override
            public Iterator<IEdge<E>> iterator() {
                return returnEdges.iterator();
            }
        };
    }

    @Override
    public Iterable<IEdge<E>> shortestPath(final IVertex<V> start, IVertex<V> end) {
        Map<IVertex<V>, LinkedList<IEdge<E>>> linkedHashMap = new LinkedHashMap<>();
        final LinkedList<IEdge<E>> returnEdges = new LinkedList<>();

        if (start.equals(end))      return returnIterable(returnEdges);

        /**
         * Creating sets of visited and unvisited nodes
         */
        Set<V> unvisitedNodes = new HashSet<>(nameSet);
        Map<IVertex<V>, Integer> distanceTable = initMap();
        Queue<IVertex<V>> queue = new PriorityQueue<>();
        distanceTable.put(start, 0);
        start.setDistance(0);
        queue.offer(start);



        while (!queue.isEmpty()) {
            IVertex<V> current = queue.poll();
            if (!linkedHashMap.containsKey(current)) {
                linkedHashMap.put(current, new LinkedList<IEdge<E>>());
            }

            if (!unvisitedNodes.contains(end.getName())) break;

            if (!unvisitedNodes.contains(current.getName())) {
                /**
                 * Discard the current Node, continue from Step 4
                 * If it is in the queue, we need to remove
                 * Case - first item in the queue
                 */
                continue;
            }
            /**
             * Mark Node as visited by removing it from
             * unvisited Nodes list, and adding it to
             * visited Nodes list
             */

            LinkedList<IEdge<E>> neighborList = graph.get(current);
            for (IEdge<E> neighbor : neighborList) {
                int updatedCost = distanceTable.get(current) + (Integer) neighbor.getCost();
                if (updatedCost < distanceTable.get(neighbor.getEndVertex())) {
                    distanceTable.put(neighbor.getEndVertex(), updatedCost);
                    neighbor.getEndVertex().setDistance(updatedCost);
                }
                queue.offer(neighbor.getEndVertex());

                /**
                 * If current.next has not been visited
                 *      end = current.next
                 *      end.last = current
                 */

                if (unvisitedNodes.contains(neighbor.getEndVertex().getName())) {
                    neighbor.getEndVertex().setLast(current);
                }
            }
            /**
             * Removing the current Node from UnvisitedNodes,
             * we've seen this already.
             */
            V currentName = current.getName();
            unvisitedNodes.remove(currentName);
        }

        IVertex<V> finalVertex = end;
        LinkedList<IVertex<V>> answerList = new LinkedList<>();
        answerList.addFirst(finalVertex);

        while (finalVertex.getLast() != null) {
            answerList.addFirst(finalVertex.getLast());
            finalVertex = finalVertex.getLast();
        }

        /**
         * At this point, we are guaranteed to have at least two
         * Vertices in answerList.
         *
         * We compare, two at a time, vertices to the start and
         * end vertices of each edge, which are in order
         *
         * We remove edges that our not in the correct order,
         * since vertices are already in order of correct path
         *
         * Store these edges into returnEdges LL
         */
        for (int i = 0; i < answerList.size() - 1; i++) {
            IVertex<V> elementOne = answerList.get(i);
            IVertex<V> elementTwo = answerList.get(i + 1);
            LinkedList<IEdge<E>> listOfEdges = graph.get(elementOne);
            for (IEdge<E> neighbor : listOfEdges) {
                if (((GraphVertex)neighbor.getStartVertex()).compareTo(elementOne) == 0 &&
                        ((GraphVertex)neighbor.getEndVertex()).compareTo(elementTwo) == 0) {
                    returnEdges.add(neighbor);
                }
            }
        }


        return returnIterable(returnEdges);
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
            unvisitedNodes.put(vertex, Integer.MAX_VALUE);
        }
        return unvisitedNodes;
    }

}
