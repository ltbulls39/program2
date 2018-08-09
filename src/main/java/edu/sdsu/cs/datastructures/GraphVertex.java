package edu.sdsu.cs.datastructures;


public class GraphVertex<V> implements IVertex<V> {
    private V name;

    public GraphVertex(V name) {
        this.name = name;
    }

    @Override
    public V getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(IVertex<V> vertex) {
        return name.equals(((GraphVertex<V>) vertex).name);
    }
}
