package es.uvigo.esei.aed2.activity4;

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

import java.util.ArrayList;
import java.util.logging.Logger;

public class BinaryMaxHeap<T extends Comparable<T>> implements MaxHeap<T> {
  private Logger MyLogger = Logger.getLogger(BinaryMaxHeap.class.getName());
  // Exercise 1
  private final ArrayList<T> heap;

  public BinaryMaxHeap() {
    this.heap = new ArrayList<>();
    this.heap.add(0, null);
  }
  private static int getLeftChild(int pos){
    return pos*2;
  }
  private static int getRightChild(int pos){
    return pos*2+1;
  }
  private static int getParent(int pos){
    return (int)(pos/2);
  }

  /**
   * Comprueba si el heap está vacío.
   *
   * @return <code>true</code> cuando el heap está vacío y <code>false</code> en
   * caso contrario.
   */
  @Override
  public boolean isEmpty() {
    return heap.size() == 1;
  }

  /**
   * Recupera el mayor elemento del heap.
   *
   * @return el elemento mayor del heap.
   * @throws HeapEmptyException cuando el heap está vacío.
   */
  @Override
  public T getMaxValue() throws HeapEmptyException {
    if (!isEmpty()){
      return heap.get(1);
    }
    throw new HeapEmptyException("Could not get max value of empty BinaryHexMap");
  }

  /**
   * Elimina y devuelve el elemento mayor del heap.
   *
   * @return el elemento mayor del heap.
   * @throws HeapEmptyException cuando el heap está vacío.
   */
  @Override
  public T removeMaxValue() throws HeapEmptyException {
    if (!isEmpty()){
      T max = heap.get(1);
      heap.set(1, null);
      if (heap.size() > 2){
        sink(1);
      }else{
        heap.removeLast();
      }
      return max;
    }
    throw new HeapEmptyException("Could not get max value of empty BinaryHexMap");
  }

  /**
   * Mueve el elemento en la posición {@code hollow} hacia arriba en el heap
   * hasta que se cumpla la propiedad de ordenación.
   *
   * @param hollow la posición del elemento a mover.
   */
  private void sink(int hollow) {
    if (!heap.isEmpty()) {
      if (heap.get(hollow) == null) {
        heap.set(hollow, heap.remove(heap.size() - 1));
      }
      int child = getGreaterChild(hollow);
      if (child != hollow) {
        T temp = heap.get(hollow);
        heap.set(hollow, heap.get(child));
        heap.set(child, temp);
        sink(child);
      }
    }
  }

  private int getGreaterChild(int pos){
    int startingpos = pos;
    if (heap.get(pos) == null){
      if (getLeftChild(pos) < heap.size()){
        if (getRightChild(pos) < heap.size()){
          return heap.get(getRightChild(pos)).compareTo(heap.get(getLeftChild(pos))) > 0 ? getRightChild(pos) : getLeftChild(pos);
        }else{
          return getLeftChild(pos);
        }
      }
    }
    if (getLeftChild(startingpos) < heap.size()){
      pos = heap.get(getLeftChild(pos)).compareTo(heap.get(pos)) > 0 ? getLeftChild(pos) : pos;
      if (getRightChild(startingpos) < heap.size()){
        pos = heap.get(getRightChild(startingpos)).compareTo(heap.get(pos)) > 0 ? getRightChild(startingpos) : pos;
      }
    }
    return pos;
  }

  /**
   * Añade un valor al heap.
   *
   * @param value el elemento a añadir.
   * @throws NullPointerException si el valor es <code>null</code>.
   */
  @Override
  public void add(T value) throws NullPointerException {
    if (value == null){
      throw new NullPointerException("Cant add null to Heap");
    }
    heap.add(value);
    int child = heap.size()-1;
    while (child > 1 && heap.get(child).compareTo(heap.get(getParent(child))) > 0){
      heap.set(child, heap.get(getParent(child)));
      heap.set(getParent(child), value); 
      child = child/2;
    }
  }

  /**
   * Elimina los valores del heap, convirtiéndolo en un heap vacío.
   */
  @Override
  public void clear() {
    heap.clear();
    heap.add(null);
  }

  /**
   * Añade un valor al heap sin mantener la propiedad de ordenacion
   *
   * @param value el elemento a añadir
   * @throws NullPointerException si el valor es <code>null</code>
   */
  @Override
  public void insert(T value) throws NullPointerException {
    if (value == null){
      throw new NullPointerException("Cant add null to Heap");
    }
    heap.add(value);
  }

  /**
   * Restablece el orden en el montículo binario
   */
  @Override
  public void orderHeap() {
    int start = getParent(heap.size());
    for (int i = start;i>0;i--){
      MyLogger.info("Now calling sink on:" + i);
      sink(i);
    }
  }

}