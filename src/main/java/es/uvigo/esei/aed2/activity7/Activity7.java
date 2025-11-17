package es.uvigo.esei.aed2.activity7;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;

public class Activity7 {

  //exercise 1
  public static <T, E> Set<Vertex<T>> getPredecessors(Graph<T, E> graph, Vertex<T> vertex) {
    Set<Vertex<T>> set = new HashSet<>();
    graph.getEdges().forEach(edg -> {
      if (edg.getTarget().equals(vertex)) set.add(edg.getSource());
    });
    return set;
  }

  //exercise 2
  public static <T, E> boolean isDrain(Graph<T, E> graph, Vertex<T> vertex) {
    // return getPredecessors(graph, vertex).size() == graph.getVertices().size()-1; 
    return graph.getEdges().parallelStream().map(t -> {
      return t.getTarget().equals(vertex);
    }).reduce(false, (a,b)-> a||b);
  }
  
  //exercise 3
  public static <T, E> boolean isRegular(Graph<T, E> graph) {
    if (graph.isEmpty()) return true;
    int standard = graph.getAdjacentsVertex(graph.getVertices().iterator().next()).size();
    for (Vertex<T> vert : graph.getVertices()) {
      if (standard!=graph.getAdjacentsVertex(vert).size()) return false;
    }
    return true;
  }
  
  //exercise 4
  public static <T, E> boolean isConnectedFromVertex(Graph<T, E> graph, Vertex<T> vertex) {
    Set<Vertex<T>> travel = new HashSet<>();
    travel.add(vertex);
    DFS(vertex, travel,graph.getEdges());
    return travel.equals(graph.getVertices());
  }
  private static <T,E> void DFS(Vertex<T> vertex, Set<Vertex<T>> travelSet,Set<Edge<T,E>> gSet){
    gSet.forEach(edge->{
      if (edge.getSource().equals(vertex) && !travelSet.contains(edge.getTarget())){
        travelSet.add(edge.getTarget());
        DFS(edge.getTarget(), travelSet,gSet);
      }
    });
  }

  //exercise 5
  public static <T, E> boolean thereIsPathBetweenVertices(Graph<T, E> graph, Vertex<T> source, Vertex<T> target) {
    Set<Vertex<T>> travel = new HashSet<>();
    travel.add(source);
    DFS(source, travel, graph.getEdges());
    return travel.contains(target);
  }
  
  //exercise 6
  public static <T, E> boolean isACycle(Graph<T, E> graph, List<Vertex<T>> path) {
    Set<Vertex<T>> travel = new HashSet<>();
    travel.add(path.get(0));
    DFS(path.get(0), travel, graph.getEdges());
    return travel.containsAll(path);
  }
  
  //exercise 7
  public static <T,E> int numberOfConnectedComponents(Graph<T,E> graph){
    Set<Vertex<T>> visitedSet = graph.getVertices();
    Set<Edge<T,E>> gSet = graph.getEdges(); // purely for efficiency
    int num = 0;
    
    while (!visitedSet.isEmpty()) {
      Set<Vertex<T>> travel = new HashSet<>();
      Vertex<T> sVertex = visitedSet.iterator().next();
      travel.add(sVertex);
      DFS(sVertex, travel, gSet);
      visitedSet.removeAll(travel);
      num+=1;
    }
    return num;
  } 
}
