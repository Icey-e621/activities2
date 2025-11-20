package es.uvigo.esei.aed2.activity6.HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

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

import es.uvigo.esei.aed2.map.Map;

public class HashMap<K, V> implements Map<K, V> {
  private static final int CAPACITY = 50;

  private int size;
  private List<Pair<K, V>>[] map;

  public HashMap() {
    this(CAPACITY);
  }

  private HashMap(int capacity) throws IllegalArgumentException {
    size = 0;
    this.map = new List[capacity];
    for (int i = 0; i < CAPACITY; i++) {
      map[i] = new LinkedList<Pair<K, V>>();
    }
  }

  // Métodos lanzan excepción
  @Override
  public int size() {
    return size;
  }

  @Override
  public V get(K key) {
    if (key == null)
      return null;
    V value = null;
    //for each item in the list corrresponding to the hash of the object
    for (Pair<K, V> pair : map[this.hashFunc(key)]) {
      if (key.equals(pair.k))
        value = pair.v;
    }
    return value;
  }

  @Override
  public void add(K key, V value) throws NullPointerException {
    if (key == null || value == null)
      throw new NullPointerException(" Tried to add null value to hashmap");
    boolean found = false;
    for (Pair<K, V> pair : map[hashFunc(key)]) {
      if (pair.k.equals(key)) {
        pair.setValue(value);
        found = true;
      }
    }
    if (!found) {
      map[hashFunc(key)].add(new Pair<K, V>(key, value));
      size++;
    }
  }

  @Override
  public V remove(K key) {
    V value = null;
    if (key == null)
      return null;
    List<Pair<K, V>> list = map[hashFunc(key)];
    for (int i = 0; i < list.size(); i++) {
      if (key.equals(list.get(i).k)) {
        value = list.remove(i).v;
        size--;
      }
    }
    return value;
  }

  @Override
  public Set<K> getKeys() {
    Set<K> set = new HashSet<>();
    for (List<Pair<K, V>> list : map) {
      list.forEach(z -> set.add(z.k));
    }
    return set;
  }

  @Override
  public Iterator<V> getValues() {
    Set<V> set = new HashSet<>();
    for (List<Pair<K, V>> list : map) {
      list.forEach(z -> set.add(z.v));
    }
    // return set and change type if you need to use getValues as a set
    return new MyIterator();
  }

  @Override
  public void clear() {
    size = 0;
    for (List<Pair<K, V>> list : map) {
      //we gotta CLEAR EACH LIST
      list.clear();
    }
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (List<Pair<K,V>> list : map) {
      for (Pair<K,V> pair : list) {
        sb.append(pair.k).append(":").append(pair.v);
      }
    }
    return sb.toString();
  }

  //arbitrary hash func
  private int hashFunc(K key) { // calculates index
    return Math.abs(key.hashCode() % map.length);
  }

  private class MyIterator implements Iterator<V> {
    private Iterator<K> keys = getKeys().iterator();

    @Override
    public boolean hasNext() {
      //obviusly
      return keys.hasNext();
    }

    @Override
    public V next() {
      //its just piggybacking of of the getkeys() iterator, yes
      return get(keys.next());
    }

    @Override
    public void remove() {
      //more piggybacking this time with parent calling
      HashMap.this.remove(keys.next());
    }

    @Override
    public void forEachRemaining(Consumer<? super V> action) {
      //funny thing, this is kinda like rusts [#derive ___]
      Iterator.super.forEachRemaining(action);
    }
  }

  private static class Pair<K, V> {

    private final K k;
    private V v;

    public Pair(K k, V v) {
      this.k = k;
      this.v = v;
    }

    /*
    @SuppressWarnings("unused")  // this is a public interface function and is useless since its a private class
    public K getKey() {
      return k;
    }

    @SuppressWarnings("unused")  // this is a public interface function and is useless since its a private class
    public V getValue() {
      return v;
    }
    */
    public void setValue(V v) {
      this.v = v;
    }
    

    @Override
    public boolean equals(Object other) {
      if (other instanceof Pair) {
        Pair<?, ?> hp = (Pair<?, ?>) other;
        return this.k.equals(hp.k) && this.v.equals(hp.v);
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(this.k) ^ Objects.hashCode(this.v);
    }
  }

}
