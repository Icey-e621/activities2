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
import java.util.HashSet;
import java.util.Iterator;
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

  // Exercise 3 djisktra but i thought it was "amount of jumps"
  public static <T> Map<Vertex<T>, Integer> dijkstra(Graph<T, Integer> graph, Vertex<T> vertex) {
    Map<Vertex<T>, Integer> map = new es.uvigo.esei.aed2.activity6.HashMap.HashMap<>();
    Logger log = Logger.getLogger(GreedyAlgorithm.class.getName());
    List<Vertex<T>> queue = new LinkedList<>();
    Set<Vertex<T>> set = new HashSet<>();
    Vertex<T> curr = null;
    int rank = 0;
    int nextRank[]={1};
    set.add(vertex);
    queue.add(vertex);
    while (!queue.isEmpty()) {
      curr = queue.remove(0);
      nextRank[0]-=1;
      if (nextRank[0]==0) {
        rank+=1;
      }
      map.add(curr, rank);
      graph.getAdjacentsVertex(curr).forEach(adj->{
        if (!set.contains(adj)){
          queue.add(adj);
          set.add(adj);
        }
      });
      if (nextRank[0]==0){
        nextRank[0]=queue.size();
      }
    }

    log.info(map.toString());
    return map;
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
    Set<String> items = amounts.getKeys();
    Map<String, Integer> end = new HashMap<>();
    Integer low[] = {volumes.getValues().next()};
    volumes.getValues().forEachRemaining(elem->low[0] = low[0]>elem?elem:low[0]);
    String curreString = nextHighest(items,volumes);
    int i = 0;
 
    while (!items.isEmpty() || maxVolume > low[0]) {
      for(i = 0;i<amounts.get(curreString) && maxVolume-volumes.get(curreString) >= 0;i++){
        maxVolume-=volumes.get(curreString);
      }
      end.add(curreString, i);
      i=0;
      items.remove(curreString);
      curreString=nextHighest(items, volumes);
    }
    
    return end;
  }
  private static String nextHighest(Set<String> items,Map<String, Integer> volumes){
    Integer num = Integer.MIN_VALUE;
    String end = "";
    for (String str : items) {
      if (volumes.get(str) > num){
        end=str;
        num=volumes.get(str);
      }
    }
    return end;
  }

  // Exercise 8
  public static Map<Vertex<String>, String> examSchedule(Graph<String, Integer> graph, String[] daysWeek) {
    Iterator<Vertex<String>> maters = graph.getVertices().iterator();
    Map<Vertex<String>, String> end = new es.uvigo.esei.aed2.activity6.HashMap.HashMap<>();
    Vertex<String> vertex = null;

    while (maters.hasNext()) { // por cada vertice
      vertex = maters.next();
      int i = 0;
      String day = daysWeek[i];
      for (;i<daysWeek.length;i++) { // por cada dia
        day = daysWeek[i];
        boolean nulled = false;
        for (Vertex<String> vertex2 : graph.getAdjacentsVertex(vertex)) { // si no es adyacente a uno que ya tiene ESE DIA
            if (end.get(vertex2)==day){
            nulled = true;
          }
        }
        if (!nulled){// añadir ese dia al vertice
          end.add(vertex, day);
          i=1000;
        }
      }
    }

    return end; // works but... oh well
  }

  // Exercise 9
  public static Set<String> plannerActivities(List<Activity> listActivities) {
    Set<String> set = new HashSet<>();
    int time[]={0};
    String curr = getNext(listActivities, time);
    while (curr!="") {
      set.add(curr);
      curr=getNext(listActivities, time);
    }
    return set;
  }
  private static String getNext(List<Activity> listActivities, int time[]){
    int limit = time[0];
    String curr = "";
    time[0] = Integer.MAX_VALUE;
    for (Activity act : listActivities) {
      if (act.getStart() >= limit && act.getEnd() < time[0]){
        time[0]=act.getEnd();
        curr = act.getNomActivity();
      }
    }
    return curr;
  }

}
