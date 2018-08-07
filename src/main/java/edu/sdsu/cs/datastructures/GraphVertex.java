package edu.sdsu.cs.datastructures;

import java.util.LinkedList;

public class GraphVertex<V> implements IVertex<V> {
    V name;

    public GraphVertex(V name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name.toString();
    }
}
