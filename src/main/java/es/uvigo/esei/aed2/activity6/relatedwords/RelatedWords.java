package es.uvigo.esei.aed2.activity6.relatedwords;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
import java.util.Set;

import es.uvigo.esei.aed2.activity6.mapofmap.MapOfMap;
import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;

public class RelatedWords {
  /**
   * Construye un grafo que representa las relaciones entre las palabras.
   *  
   * @param words palabras a relacionar.
   * @return grafo de palabras relacionadas.
   */
  public static Graph<String, Integer> buildGraph(List<String> words) {
    Set<Vertex<String>> vertices = new LinkedHashSet<>();

    words.forEach(word->vertices.add(new Vertex<String>(word)));

    Set<Edge<String,Integer>> edges = new LinkedHashSet<>();
    HashMap<Vertex<String>, List<Vertex<String>>> relations = new HashMap<>();

    vertices.forEach(vert1->vertices.forEach(vert2->{
      if (IsRelated(vert1.getValue(),vert2.getValue())){
        if (relations.get(vert1) == null) relations.put(vert1, new LinkedList<>());
        relations.get(vert1).add(vert2);
      }
    }));

    relations.forEach((vert1,vert_related) -> vert_related.forEach(related_vert->edges.add(new Edge<String,Integer>(vert1, related_vert, 0))));

    return new MapOfMap<>(vertices,edges);
  } 
  private static boolean IsRelated(String word1, String word2){
    int ret = 0;
    for (int i = 0; i < word1.length(); i++) {
      if (ret <= 1 && word1.charAt(i) != word2.charAt(i)){
        ret++;
      }
    }
    return ret==1;
  }
}
