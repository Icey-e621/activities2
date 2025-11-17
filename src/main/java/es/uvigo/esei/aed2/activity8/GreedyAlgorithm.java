package es.uvigo.esei.aed2.activity8;
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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;

public class GreedyAlgorithm {

  // Exercise 1
  public static <T> Graph<T, Integer> traveller(Graph<T, Integer> graph, Vertex<T> vertex) {

    return null;
  }

  // Exercise 2
  public static <T> Graph<T, Integer> prim(Graph<T, Integer> graph, Vertex<T> vertex) {

    return null;
  }

  // Exercise 3
  public static <T> Map<Vertex<T>, Integer> dijkstra(Graph<T, Integer> graph, Vertex<T> vertex) {

    return null;
  }

  // Exercise 4
  public static <T> Map<Vertex<T>, String> colourMap(Graph<T, Integer> graph, String[] colours) {
    Map<Vertex<T>, String> map = new HashMap<>();
    graph.getVertices().forEach(vert->map.add(vert, getColor(graph,map,vert,colours)));

    return map;
  }
  private static <T> String getColor(Graph<T, Integer> graph, Map<Vertex<T>, String> map, Vertex<T> check, String[] colours){
    List<String> list = new LinkedList<>();
    for (String string : colours) {
      list.add(string);
    }

    graph.getAdjacentsVertex(check).forEach(vert-> {
      list.remove(map.get(vert));
    });

    return list.get(0);
  }

  // Exercise 5
  public static Map<Integer, Integer> giveChange(int amountReturned, Map<Integer, Integer> changeAvailable) {
    return null;
  }

  // Exercise 6
  public static Set<String> burnCD(int maximumCapacity, Map<String, Integer> espacePrograms) {

    return null;
  }

  // Exercise 7
  public static Map<String, Integer> fillRucksack(
    int maxVolume, Map<String, Integer> amounts, Map<String, Integer> volumes
  ) {

    return null;
  }

  // Exercise 8
  public static Map<Vertex<String>, String> examSchedule(Graph<String, Integer> graph, String[] daysWeek) {

    return null;
  }

  // Exercise 9
  public static Set<String> plannerActivities(List<Activity> listActivities) {

    return null;
  }

}
