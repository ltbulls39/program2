package edu.sdsu.cs.datastructures;

/**
 * @author Nicholas Hernandez
 *      - cssc0256
 * @author Bernard Gonzales
 *      - cssc
 */

public interface IVertex<V> {
    String toString();

    V getName();

    int hashCode();

    boolean equals(IVertex<V> vertex);

    int getDistance();

    void setDistance(int distance);

    IVertex<V> getLast();

    void setLast(IVertex<V> last);
}
