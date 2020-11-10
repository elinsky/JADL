package com.brianelinsky.graph;

/**
 * An object that implements the Path interface provides methods to determine if a path exists
 * between a source and target vertex, and if so, what that path is.
 *
 * @param <T> Target vertex. Must be a hashable object.
 */
public interface Paths<T> {
  /**
   * Returns true if there exists a path from the source vertex to the target vertex.
   *
   * @param target Target vertex.
   * @return true if there exists a path from the source vertex to the target vertex.
   */
  boolean hasPathTo(T target);

  /**
   * Returns an iterable of vertices that describe a path from the source vertex to target vertex
   *
   * @param target Target vertex.
   * @return Iterable of vertices that describe a path from the source vertex to target vertex.
   */
  Iterable<T> pathTo(T target);
}
