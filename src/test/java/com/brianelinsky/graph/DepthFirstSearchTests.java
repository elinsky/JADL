package com.brianelinsky.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstSearchTests {

  private Graph<Integer> createTinyGraph() {
    MutableGraph<Integer> graph = GraphBuilder.undirected().build();
    for (int i = 0; i < 7; i++) {
      graph.addNode(i);
    }
    graph.putEdge(0, 1);
    graph.putEdge(0, 2);
    graph.putEdge(0, 5);
    graph.putEdge(1, 2);
    graph.putEdge(2, 3);
    graph.putEdge(2, 4);
    graph.putEdge(3, 4);
    graph.putEdge(3, 5);

    return graph;
  }

  @Test
  public void given_SourceIsZero_When_Count_Then_SixConnected() {
    // Arrange
    Graph<Integer> tinyGraph = createTinyGraph();
    DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>(tinyGraph, 0);

    // Act
    int actual = dfs.count();

    // Assert
    int expected = 6;
    assertEquals(expected, actual);
  }

  @Test
  public void given_SourceIsSix_When_Count_Then_OneConnected() {
    // Arrange
    Graph<Integer> tinyGraph = createTinyGraph();
    DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>(tinyGraph, 6);

    // Act
    int actual = dfs.count();

    // Assert
    int expected = 1;
    assertEquals(expected, actual);
  }

  @Test
  public void given_SourceIsZero_When_IsFourMarked_Then_True() {
    // Arrange
    Graph<Integer> tinyGraph = createTinyGraph();
    DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>(tinyGraph, 0);

    // Act
    boolean actual = dfs.marked(4);

    // Assert
    boolean expected = true;
    assertEquals(expected, actual);
  }

  @Test
  public void given_SourceIsSix_When_IsFourMarked_Then_False() {
    // Arrange
    Graph<Integer> tinyGraph = createTinyGraph();
    DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>(tinyGraph, 6);

    // Act
    boolean actual = dfs.marked(4);

    // Assert
    boolean expected = false;
    assertEquals(expected, actual);
  }
}
