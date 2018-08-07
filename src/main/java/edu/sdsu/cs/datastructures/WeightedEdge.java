package edu.sdsu.cs.datastructures;

public class WeightedEdge<E> implements IEdge<E> {
    protected IVertex startVertex;
    protected IVertex endVertex;
    E cost;

    public WeightedEdge(IVertex startVertex, IVertex endVertex, E cost) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.cost = cost;
    }

    public void printEdge() {
        System.out.println( startVertex + " --> " + endVertex);
    }

    @Override
    public String toString() {
        return "(" + startVertex + ", " + endVertex + ")";
    }
}
