package com.brianelinsky.graph;

import com.google.common.graph.Graph;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Given a graph and a source vertex, DepthFirstSearch calculates which vertices are reachable from
 * the source vertex, and how many that is. The nodes in your graph must be usable as map keys.
 * Additionally, it supports pathfinding.
 * https://github.com/google/guava/wiki/GraphsExplained#graph-elements-nodes-and-edges.
 */
public class DepthFirstSearchPaths<T> implements Search<T>, Paths<T> {
  private final HashMap<T, Boolean> marked; // Is there a path from 's' to 'v'?
  private final HashMap<T, T> edgeTo; // For key vertex, value indicated the vertex we came from.
  private final T source;
  private int count; // number of vertices connected to 's'

  /**
   * @param graph The graph.
   * @param source The source vertex.
   */
  public DepthFirstSearchPaths(Graph<T> graph, T source) {
    this.marked = new HashMap<>();
    this.edgeTo = new HashMap<>();
    this.source = source;

    graph.nodes().forEach(node -> marked.put(node, false));
    edgeTo.put(source, source);
    dfs(graph, source);
  }

  /**
   * Is there a path between {@code source} and {@code target}?
   *
   * @param target The target vertex.
   * @return {@code true} if there exists a path between {@code source} and {@code target}.
   *     Otherwise returns {@code false}.
   */
  @Override
  public boolean marked(T target) {
    return marked.get(target);
  }

  /**
   * Returns the number of vertices connected to the {@code source} vertex.
   *
   * @return the number of vertices connected to the {@code source} vertex.
   */
  @Override
  public int count() {
    return count;
  }

  /**
   * Returns true if there exists a path from source to target.
   *
   * @param target Target vertex.
   * @return {@code true} if there exists a path from source to target.
   */
  @Override
  public boolean hasPathTo(T target) {
    // TODO
    return false;
  }

  /**
   * Returns an iterable of vertices describing the path from the source vertex to target vertex.
   *
   * @param target target vertex.
   * @return iterable of vertices describing the path from the source vertex to target vertex.
   */
  @Override
  public Iterable<T> pathTo(T target) {
    if (!edgeTo.containsKey(target)) {
      throw new IllegalArgumentException(
          "There does not exist a path between the source and target vertices.");
    }
    LinkedList<T> result = new LinkedList<>();
    T curr = target;
    while (curr != source) {
      result.addFirst(curr);
      curr = edgeTo.get(curr);
    }
    result.addFirst(source);
    return result;
  }

  private void dfs(Graph<T> graph, T vertex) {
    count++;
    marked.put(vertex, true);
    for (T adjacentVertex : graph.adjacentNodes(vertex)) {
      if (!marked.get(adjacentVertex)) {
        edgeTo.put(adjacentVertex, vertex);
        dfs(graph, adjacentVertex);
      }
    }
  }
}
