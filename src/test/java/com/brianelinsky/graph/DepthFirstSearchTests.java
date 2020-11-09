package com.brianelinsky.graph;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstSearchTests {
  MutableGraph<Integer> tinyGraph;

  @BeforeEach
  private void createTinyGraph() {
    tinyGraph = GraphBuilder.undirected().build();
    for (int i = 0; i < 7; i++) {
      tinyGraph.addNode(i);
    }
    tinyGraph.putEdge(0, 1);
    tinyGraph.putEdge(0, 2);
    tinyGraph.putEdge(0, 5);
    tinyGraph.putEdge(1, 2);
    tinyGraph.putEdge(2, 3);
    tinyGraph.putEdge(2, 4);
    tinyGraph.putEdge(3, 4);
    tinyGraph.putEdge(3, 5);
  }

  @ParameterizedTest(name = "{index} Source vertex: {0}, Connected vertex: {1}")
  @CsvSource({"0, 6", "3, 6", "6 ,1"})
  public void given_SourceVertex_When_Count_Then_HowManyConnected(int source, int expected) {
    // Given
    DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>(tinyGraph, source);

    // When
    int actual = dfs.count();

    // Then
    assertEquals(expected, actual);
  }

  @ParameterizedTest(name = "{index} Given source vertex {0}, then is marked({1}) {2}?")
  @CsvSource({"0, 4 ,true", "6, 4, false", "1, 1, true"})
  public void given_SourceVertex_Then_IsTargetVertexMarked(
      int source, int target, boolean expected) {
    // Given
    DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>(tinyGraph, source);

    // When
    boolean actual = dfs.marked(target);

    // Then
    assertEquals(expected, actual);
  }
}
