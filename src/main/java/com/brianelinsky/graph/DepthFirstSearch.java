package com.brianelinsky.graph;

import com.google.common.graph.Graph;

import java.util.HashMap;

/**
 * Given a graph and a source vertex, DepthFirstSearch calculates which vertices and how many are
 * connected to the source vertex. The nodes in your graph must be usable as map keys.
 * https://github.com/google/guava/wiki/GraphsExplained#graph-elements-nodes-and-edges.
 *
 * <p>Heavily influenced by https://algs4.cs.princeton.edu/41graph/DepthFirstSearch.java.html
 */
public class DepthFirstSearch<T> {
  private final HashMap<T, Boolean> marked; // Is there a path from 's' to 'v'?
  private int count; // number of vertices connected to 's'

  /**
   * Computes the vertices that are connected to the {@code source} vertex.
   *
   * @param graph The graph.
   * @param source The source vertex.
   */
  public DepthFirstSearch(Graph<T> graph, T source) {
    this.marked = new HashMap<>();
    graph.nodes().forEach(node -> marked.put(node, false));
    dfs(graph, source);
  }

  /**
   * Is there a path between {@code source} and {@code target}?
   *
   * @param target The target vertex.
   * @return {@code true} if there exists a path between {@code source} and {@code target}.
   *     Otherwise returns {@code false}.
   */
  public boolean marked(T target) {
    return marked.get(target);
  }

  /**
   * Returns the number of vertices connected to the {@code source} vertex.
   *
   * @return the number of vertices connected to the {@code source} vertex.
   */
  public int count() {
    return count;
  }

  private void dfs(Graph<T> graph, T vertex) {
    count++;
    marked.put(vertex, true);
    graph.adjacentNodes(vertex).stream()
        .filter(adjacentNode -> !marked.get(adjacentNode))
        .forEach(unvisitedNode -> dfs(graph, unvisitedNode));
  }
}
