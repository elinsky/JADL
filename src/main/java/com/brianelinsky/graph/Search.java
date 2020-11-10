package com.brianelinsky.graph;

/**
 * An object that implements the Search interface provides methods to determine how many vertices
 * are reachable in a undirected unweighted graph from a source vertex. Additionally, it can
 * determine if a given vertex is reachable from the source or not.
 *
 * @param <T> Generic type for vertices. Must be a hashable object.
 */
public interface Search<T> {
  /**
   * Returns true if the {@code target} vertex is reachable from the source vertex.
   *
   * @param target The target vertex.
   * @return True if there exists a path between the source and {@code target} vertices, else False.
   */
  boolean marked(T target);

  /**
   * Returns the number of reachable vertices in the graph from the source vertex.
   *
   * @return the number of reachable vertices in the graph from the source vertex.
   */
  int count();
}
