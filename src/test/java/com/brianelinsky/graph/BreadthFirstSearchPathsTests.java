package com.brianelinsky.graph;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BreadthFirstSearchPathsTests {
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
    BreadthFirstSearchPaths<Integer> bfsp = new BreadthFirstSearchPaths<>(tinyGraph, source);
    int actual = bfsp.count();
    assertEquals(expected, actual);
  }

  @ParameterizedTest(name = "{index} Given source vertex {0}, then is marked({1}) {2}?")
  @CsvSource({"0, 4 ,true", "6, 4, false", "1, 1, true"})
  public void given_SourceVertex_Then_IsTargetVertexMarked(
      int source, int target, boolean expected) {
    BreadthFirstSearchPaths<Integer> bfsp = new BreadthFirstSearchPaths<>(tinyGraph, source);
    boolean actual = bfsp.marked(target);
    assertEquals(expected, actual);
  }

  @Test
  public void testPathFromZeroToFour() {
    // Given
    BreadthFirstSearchPaths<Integer> bfsp = new BreadthFirstSearchPaths<>(tinyGraph, 0);

    // When
    Iterable<Integer> actual = bfsp.pathTo(4);
    ArrayList<Integer> actual_as_list = new ArrayList<>();
    actual.forEach(actual_as_list::add);

    // Then
    ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(0, 2, 4));
    assert expected.equals(actual_as_list);
  }

  @Test
  public void testPathFromFiveToTwo() {
    // Given
    BreadthFirstSearchPaths<Integer> bfsp = new BreadthFirstSearchPaths<>(tinyGraph, 5);

    // When
    Iterable<Integer> actual = bfsp.pathTo(2);
    ArrayList<Integer> actual_as_list = new ArrayList<>();
    actual.forEach(actual_as_list::add);

    // Then
    ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(5, 0, 2));
    assert expected.equals(actual_as_list);
  }

  @Test
  public void testPathToSameVertex() {
    // Given
    BreadthFirstSearchPaths<Integer> bfsp = new BreadthFirstSearchPaths<>(tinyGraph, 1);

    // When
    Iterable<Integer> actual = bfsp.pathTo(1);
    ArrayList<Integer> actual_as_list = new ArrayList<>();
    actual.forEach(actual_as_list::add);

    // Then
    ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1));
    assert expected.equals(actual_as_list);
  }

  @Test
  public void testPathToDoesNotExist() {
    // Given
    BreadthFirstSearchPaths<Integer> bfsp = new BreadthFirstSearchPaths<>(tinyGraph, 2);

    // When
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Iterable<Integer> actual = bfsp.pathTo(6);
        });
  }

  @ParameterizedTest
  @CsvSource({"2, 2 ,true", "5, 1, true", "6, 0, false", "3, 6, false", "3, 0, true"})
  public void testHasPathTo(int source, int target, boolean expected) {
    BreadthFirstSearchPaths<Integer> bfsp = new BreadthFirstSearchPaths<>(tinyGraph, source);
    boolean actual = bfsp.hasPathTo(target);
    assertEquals(expected, actual);
  }
}
