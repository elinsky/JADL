package com.brianelinsky.graph;

import com.google.common.graph.Graph;

import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirstSearchPaths<T> implements Search<T>, Paths<T> {
  private final HashMap<T, Boolean> marked;  // Has this vertex been visited?
  private int count;                   // How many vertices are reachable from the source?
  private final HashMap<T, T> edgeTo;        // For key vertex, value indicates previous vertex on path
  private final Queue<T> queue;              // FIFO queue for BFS
  private final Graph<T> graph;
  private final T source;

  public BreadthFirstSearchPaths(Graph<T> graph, T source) {
    this.count = 1; // At a minimum, the source vertex is reachable.
    this.graph = graph;
    this.source = source;
    this.marked = new HashMap<>();
    this.edgeTo = new HashMap<>();

    this.queue = new LinkedList<>();
    for (T vertex : graph.nodes()) {
      marked.put(vertex, false);
    }

    bfs(source);
  }

  /**
   * Returns true if there exists a path from the source vertex to the target vertex.
   *
   * @param target Target vertex.
   * @return true if there exists a path from the source vertex to the target vertex.
   */
  @Override
  public boolean hasPathTo(T target) {
    return this.marked.get(target);
  }

  /**
   * Returns an iterable of vertices that describe a path from the source vertex to target vertex
   *
   * @param target Target vertex.
   * @return Iterable of vertices that describe a path from the source vertex to target vertex.
   */
  @Override
  public Iterable<T> pathTo(T target) {
    if (!hasPathTo(target)) {
      throw new IllegalArgumentException("There does not exist a path between the source and target vertices.");
    }
    LinkedList<T> result = new LinkedList<>(); // Treated as a stack.  Left side is the top.

    T current = target;
    while (current != this.source) {
      result.offerFirst(current);
      current = edgeTo.get(current);
    }
    result.offerFirst(source);
    return result;
  }

  /**
   * Returns true if the {@code target} vertex is reachable from the source vertex.
   *
   * @param target The target vertex.
   * @return True if there exists a path between the source and {@code target} vertices, else False.
   */
  @Override
  public boolean marked(T target) {
    return this.marked.get(target);
  }

  /**
   * Returns the number of reachable vertices in the graph from the source vertex.
   *
   * @return the number of reachable vertices in the graph from the source vertex.
   */
  @Override
  public int count() {
    return count;
  }

  private void bfs(T source) {
    marked.put(source, true);
    queue.offer(source);
    edgeTo.put(source, source);

    while (!queue.isEmpty()) {
      T current = queue.poll();
      for (T adj : graph.adjacentNodes(current)) {
        if (!marked(adj)) {
          count++;
          marked.put(adj, true);
          edgeTo.put(adj, current);
          queue.offer(adj);
        }
      }
    }
  }

}
