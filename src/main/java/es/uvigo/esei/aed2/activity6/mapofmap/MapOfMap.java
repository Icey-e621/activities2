  package es.uvigo.esei.aed2.activity6.mapofmap;

  import java.util.HashSet;
  import java.util.Iterator;
  import java.util.Set;

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

  import es.uvigo.esei.aed2.activity6.HashMap.HashMap;
  import es.uvigo.esei.aed2.graph.Edge;
  import es.uvigo.esei.aed2.graph.Graph;
  import es.uvigo.esei.aed2.graph.Vertex;
  import es.uvigo.esei.aed2.map.Map;

  public class MapOfMap<T, E> implements Graph<T, E> {

    private final Map<Vertex<T>, Map<Vertex<T>, E>> mapOfVertices;

    public MapOfMap() {
      this.mapOfVertices = new HashMap<>();
    }
    
    public MapOfMap(Set<Vertex<T>> vertices, Set<Edge<T, E>> edges) {
      this();
      vertices.forEach(z->addVertex(z));
      edges.forEach(z->addEdge(z.getSource(), z.getTarget(), z.getLabel()));
    }

    @Override
    public boolean isEmpty() {
      return mapOfVertices.size() == 0;
    }

    @Override
    public int numberOfVertices() {
      return mapOfVertices.getKeys().size();
    }

    @Override
    public boolean containsVertex(Vertex<T> vertex) {
      return mapOfVertices.getKeys().contains(vertex);
    }

    @Override
    public boolean containsEdge(Vertex<T> source, Vertex<T> target, E label) {
      if (containsVertex(target) && containsVertex(source) && mapOfVertices.get(source).getKeys().contains(target) && mapOfVertices.get(source).get(target).equals(label)) return true;
      return false;
    }

    @Override
    public Set<Vertex<T>> getVertices() {
      return mapOfVertices.getKeys();
    }

    @Override
    public Set<Edge<T, E>> getEdges() {
      Set<Edge<T, E>> edges = new HashSet<>();
      for (Vertex<T> vertex : mapOfVertices.getKeys()) {
        for (Vertex<T> vertex2 : mapOfVertices.get(vertex).getKeys()){
          edges.add(new Edge<T,E>(vertex, vertex2, mapOfVertices.get(vertex).get(vertex2)));
        }
      }
      return edges;
    }

    @Override
    public Set<Vertex<T>> getAdjacentsVertex(Vertex<T> vertex) throws NullPointerException {
      if (vertex == null) throw new NullPointerException("error here");
      if (containsVertex(vertex)){
        return this.mapOfVertices.get(vertex).getKeys();
      }
      return new HashSet<>();
    }

    @Override
    public boolean addVertex(Vertex<T> vertex) throws NullPointerException {
      if (vertex == null) throw new NullPointerException("error here2");
      if (mapOfVertices.getKeys().contains(vertex)){
        return false;
      }
      this.mapOfVertices.add(vertex, new HashMap<Vertex<T>,E>());
      return true;
    }

    @Override
    public boolean addEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException, IllegalArgumentException{
      if (source.equals(target)) return false;
      if (source == null || target == null) throw new NullPointerException("error here3");
      if (!containsVertex(target) || !containsVertex(source)) throw new IllegalArgumentException("why u joining nothing with ur dad");
      if (this.containsEdge(source, target, label)) return false;
      this.mapOfVertices.get(source).add(target, label);
      return true;
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) throws NullPointerException {
      if (vertex == null) throw new NullPointerException("hey shall i say, stop giving me null");
      if (!containsVertex(vertex)) return false;
      this.mapOfVertices.remove(vertex);
      mapOfVertices.getValues().forEachRemaining(mapOfVertices->mapOfVertices.remove(vertex));
      return true;
    }

    @Override
    public boolean removeEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException {
      if (source == null || target == null) throw new NullPointerException("error here3");
      if (!containsEdge(source,target,label)) return false;
      mapOfVertices.get(source).remove(target);
      return true;
    }
    
    @Override
    public void clear(){
      mapOfVertices.clear();
    }

  }


