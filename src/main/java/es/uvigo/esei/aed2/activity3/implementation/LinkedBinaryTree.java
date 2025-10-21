package es.uvigo.esei.aed2.activity3.implementation;

import java.lang.classfile.components.ClassPrinter.LeafNode;

import es.uvigo.esei.aed2.tree.binary.BinaryTree;

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

import es.uvigo.esei.aed2.tree.exceptions.EmptyTreeException;

public class LinkedBinaryTree<T> implements BinaryTree<T> {

  private LinkedBinaryTreeNode<T> rootNode;

  public LinkedBinaryTree() {
    rootNode = new LinkedBinaryTreeNode<>();
  }

  public LinkedBinaryTree(T value) throws NullPointerException {
    if (value == null) throw new NullPointerException("cant use null on");
    rootNode = new LinkedBinaryTreeNode<>(value);
  }

  public LinkedBinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild)
  throws NullPointerException {
    if (value == null || leftChild == null ||rightChild == null) throw new NullPointerException("cant use null on");
    rootNode = new LinkedBinaryTreeNode<>(value);
    rootNode.setLeftChild(toNode(leftChild));
    rootNode.setRightChild(toNode(rightChild));
  }

  private LinkedBinaryTreeNode<T> toNode(BinaryTree<T> tree){
    LinkedBinaryTreeNode<T> Node = new LinkedBinaryTreeNode<>();
    if (!tree.isEmpty()){
      Node.setValue(tree.getRootValue());
      if (tree.hasLeftChild()){
        Node.setLeftChild(toNode(tree.getLeftChild()));
      }
      if (tree.hasRightChild()){
        Node.setRightChild(toNode(tree.getRightChild()));
      }
    }
    return Node;
  }
  private LinkedBinaryTree<T> toTree(LinkedBinaryTreeNode<T> node){
    if (node == null) return null;
    LinkedBinaryTree<T> rootTreeNode = new LinkedBinaryTree<>(node.getValue());
    if (node.hasLeftChild()){
      rootTreeNode.setLeftChild(toTree(node.getLeftChild()));
    }
    if (node.hasRightChild()){
      rootTreeNode.setRightChild(toTree(node.getRightChild()));
    }
    return rootTreeNode;
  }


  // Métodos lanzan excepción
  @Override
  public T getRootValue() throws EmptyTreeException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    return rootNode.getValue();
  }

  @Override
  public void setRootValue(T value) throws EmptyTreeException, NullPointerException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    if (value == null) throw new NullPointerException("null WHY :(");
    rootNode.setValue(value);
  }

  @Override
  public boolean contains(T value) {
    if (rootNode.getValue().equals(value)) return true;
    return (hasRightChild() ? toTree(rootNode.getRightChild()).contains(value) : false) || (hasLeftChild() ? toTree(rootNode.getLeftChild()).contains(value) : false); 
  }

  @Override
  public boolean hasLeftChild() { 
    return rootNode.hasLeftChild() && rootNode.getLeftChild().getValue() != null;
  }

  @Override
  public BinaryTree<T> getLeftChild() throws EmptyTreeException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    if (rootNode.getLeftChild() == null || rootNode.getLeftChild().getValue() == null) return new LinkedBinaryTree<>();
    return toTree(rootNode.getLeftChild());
  }

  @Override
  public void setLeftChild(BinaryTree<T> leftChild) throws EmptyTreeException, NullPointerException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    if (leftChild == null) new NullPointerException("why u give me null ;(");
    rootNode.setLeftChild(toNode(leftChild));
  }

  @Override
  public void removeLeftChild() throws EmptyTreeException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    rootNode.setLeftChild(null);
  }

  @Override
  public boolean hasRightChild() {
    return rootNode.hasRightChild() && rootNode.getRightChild().getValue() != null;
  }

  @Override
  public BinaryTree<T> getRightChild() throws EmptyTreeException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    if (rootNode.getRightChild() == null || rootNode.getRightChild().getValue() == null) return new LinkedBinaryTree<>();
    return toTree(rootNode.getRightChild());
  }

  @Override
  public void setRightChild(BinaryTree<T> rightChild) throws EmptyTreeException, NullPointerException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    if (rightChild == null) new NullPointerException("why u give me null ;(");
    rootNode.setRightChild(toNode(rightChild));
  }

  @Override
  public void removeRightChild() throws EmptyTreeException {
    if (this.isEmpty()) throw new EmptyTreeException("empty fucker");
    rootNode.setRightChild(null);
  }
  
  @Override
  public void clear() {
    rootNode = new LinkedBinaryTreeNode<>();
  }

  @Override
  public boolean isEmpty() {
    return rootNode.getValue() == null;
  }
}