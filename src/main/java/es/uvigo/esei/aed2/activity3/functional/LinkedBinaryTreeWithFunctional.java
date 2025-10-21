package es.uvigo.esei.aed2.activity3.functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import es.uvigo.esei.aed2.activity3.implementation.LinkedBinaryTree;

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

import es.uvigo.esei.aed2.tree.binary.BinaryTree;

public class LinkedBinaryTreeWithFunctional<T> extends LinkedBinaryTree<T> implements BinaryTreeWithFunctional<T> {

  public LinkedBinaryTreeWithFunctional() {
    super();
  }

  public LinkedBinaryTreeWithFunctional(T value) {
    super(value);
  }

  public LinkedBinaryTreeWithFunctional(
    T value,
    BinaryTree<T> left,
    BinaryTree<T> right
  ) {
    super(value, left, right);
  }

  @Override
  public void forEach(Consumer<T> action, Predicate<T> filter) {
    forEach(action, filter, this);
  }
  private void forEach(Consumer<T> action, Predicate<T> filter, BinaryTree<T> tree) {
    if (!tree.isEmpty()){
      if (filter.test(tree.getRootValue())){
        action.accept(tree.getRootValue());
      }
      if (tree.hasLeftChild()){
        forEach(action, filter, tree.getLeftChild());
      }
      if (tree.hasRightChild()){
        forEach(action, filter, tree.getRightChild());
      }
    }
  } 

  @Override
  public <E> BinaryTree<E> map(Function<T, E> mapper) {
    return map(mapper, this);
  }
  private <E> BinaryTree<E> map(Function<T, E> mapper, BinaryTree<T> treeOrgininal) {
    BinaryTree<E> treeDupTree = new LinkedBinaryTree<E>();
    if (!treeOrgininal.isEmpty()){
      treeDupTree = new LinkedBinaryTree<E>(mapper.apply(treeOrgininal.getRootValue()));
      if (treeOrgininal.hasLeftChild()){
        treeDupTree.setLeftChild(map(mapper,treeOrgininal.getLeftChild()));
      }
      if (treeOrgininal.hasRightChild()){
        treeDupTree.setRightChild(map(mapper,treeOrgininal.getRightChild()));
      }
    }
    return treeDupTree;
  }

}