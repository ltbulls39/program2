package edu.sdsu.cs.datastructures;

/**
 * @author Nicholas Hernandez
 *      - cssc0256
 * @author Bernard Gonzales
 *      - cssc
 */

public interface IEdge<E> {
    boolean equals(IEdge<E> edge);

    IVertex getStartVertex();

    IVertex getEndVertex();

    E getCost();
}
