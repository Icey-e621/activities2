package es.uvigo.esei.aed2.activity6.topologicalordering;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

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

import java.util.List;
import java.util.Map;
import java.util.Set;

import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;

public class TopologicalOrdering {
  /**
   * Calcula el orden topológico de un grafo dirigido acíclico (DAG).
   * 
   * @param graph grafo dirigido acíclico.
   * @return lista de vértices en orden topológico.
   */
  public static <T, E> List<Vertex<T>> getTopologicalOrder(Graph<T, E> graph) {
    Map<Vertex<T>, Integer> grados = new HashMap<>();
    Set<Vertex<T>> vSet = graph.getVertices();

    vSet.forEach(elem->grados.put(elem, getPredecessorNum(graph, elem)));
    List<Vertex<T>> tempList = new LinkedList<>();
    while (!grados.isEmpty()) {
      if (grados.containsValue(0)){
        grados.forEach((z,i) -> {
          if (i==0){
            tempList.add(z);
          }
        });
        tempList.forEach(element->grados.remove(element));
      }
      graph.getAdjacentsVertex(tempList.get(0)).forEach(z->{
        grados.replace(z, grados.get(z) == null ? -1 : grados.get(z)-1);
      });
    }
    return tempList;
  }
  public static <T, E> int getPredecessorNum(Graph<T, E> graph, Vertex<T> vertex) {
    Set<Vertex<T>> set = new HashSet<>();
    graph.getEdges().forEach(edg -> {
      if (edg.getTarget().equals(vertex)) set.add(edg.getSource());
    });
    return set.size();
  }
}
