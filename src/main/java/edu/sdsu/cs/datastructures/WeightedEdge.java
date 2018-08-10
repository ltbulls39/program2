package edu.sdsu.cs.datastructures;

public class WeightedEdge<E> implements IEdge<E> {
    private IVertex startVertex;
    private IVertex endVertex;
    private E cost;

    public WeightedEdge(IVertex startVertex, IVertex endVertex, E cost) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.cost = cost;
    }

    @Override
    public IVertex getStartVertex() {
        return startVertex;
    }

    @Override
    public IVertex getEndVertex() {
        return endVertex;
    }

    @Override
    public E getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "(" + startVertex + " --> " + endVertex + ")" + " Cost: " + cost;
    }

    @Override
    public int hashCode() {
        return cost.hashCode();
    }

    @Override
    public boolean equals(IEdge<E> edge) {
        WeightedEdge<E> temp = (WeightedEdge<E>) edge;
        return this.startVertex.equals(temp.startVertex) && this.endVertex.equals(temp.endVertex)
                && this.cost == temp.cost;
    }
}
