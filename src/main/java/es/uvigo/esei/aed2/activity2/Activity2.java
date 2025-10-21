package es.uvigo.esei.aed2.activity2;

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
import es.uvigo.esei.aed2.tree.binary.LinkedBinaryTree;

public class Activity2 {
  private static int max(int a, int b) {
    return (a >= b) ? a : b;
  }

  //exercise 1
  public static <T> boolean isComplete(BinaryTree<T> tree) {
    return tree.hasLeftChild() ? tree.hasRightChild() ? isComplete(tree.getLeftChild()) && isComplete(tree.getRightChild()) : false : tree.hasRightChild() ? false : true;
  }

  //exercise 2
  public static <T> boolean areIdentical(BinaryTree<T> treeA, BinaryTree<T> treeB) {
    return (treeA.hasLeftChild() ? treeB.hasLeftChild() ? areIdentical(treeA.getLeftChild(), treeB.getLeftChild()) : false : treeB.hasLeftChild() ? false : true) &&
    (treeA.hasRightChild() ? treeB.hasRightChild() ? areIdentical(treeA.getRightChild(), treeB.getRightChild()) : false : treeB.hasRightChild() ? false : true);
  }

  //exercise 3
  public static <T> int countNodesInLevel(BinaryTree<T> tree, int level) {
    if (level == 0){
      return 1;
    }
    return (tree.hasLeftChild() ? countNodesInLevel(tree.getLeftChild(), level-1) : 0) + (tree.hasRightChild() ? countNodesInLevel(tree.getRightChild(), level-1) : 0);
  }

  //exercise 4
  public static <T> BinaryTree<T> removeLeaves(BinaryTree<T> tree) {
    if (tree.isEmpty()) return tree;
    BinaryTree<T> newTree = new LinkedBinaryTree<>();

    if (tree.hasLeftChild() || tree.hasRightChild()){
      newTree = new LinkedBinaryTree<>(tree.getRootValue());
      newTree.setLeftChild(removeLeaves(tree.getLeftChild()));
      newTree.setRightChild(removeLeaves(tree.getRightChild()));
    }
    return newTree;
  }

  //exercise 5
  public static <T> int calculateHeight(BinaryTree<T> tree) {
    if (tree.isEmpty()) return -1;
    return calculateHeight(tree,0);
  }
  private static <T> int calculateHeight(BinaryTree<T> tree, int currentHeight) {
    if (tree.hasLeftChild() && tree.hasRightChild()){
      return max(calculateHeight(tree.getLeftChild(),currentHeight+1),calculateHeight(tree.getRightChild(),currentHeight+1));
    }
    else if (tree.hasRightChild()){
      return calculateHeight(tree.getRightChild(),currentHeight+1);
    }
    else if (tree.hasLeftChild()){
      return calculateHeight(tree.getLeftChild(),currentHeight+1);
    }
    return currentHeight;
  }

  //exercise 6
  public static BinaryTree<String> rebuild(String preorder, String inorder) {
    if (preorder == ""){
      return new LinkedBinaryTree<>();
    }

    BinaryTree<String> MyTree = new LinkedBinaryTree<>(preorder.charAt(0)+"");

    String[] split = inorder.split(preorder.charAt(0)+"");
    if (split.length > 0 && split[0] != ""){
      MyTree.setLeftChild(rebuild(preorder.substring(1,split[0].length()+1), split[0]));
    }
    if (split.length > 1){
      MyTree.setRightChild(rebuild(preorder.substring(split[0].length()+1), split[1]));
    }
    
    return MyTree;
  }

  //exercise 7
  public static <T extends Comparable<T>> boolean isSelectionTree(BinaryTree<T> tree) {
    if (tree.isEmpty()) return true;
    T root = tree.getRootValue();
    if (tree.hasLeftChild() && tree.hasRightChild()){
      if (root.equals(tree.getLeftChild().getRootValue())){
        return isSelectionTree(tree.getLeftChild()) && isSelectionTree(tree.getRightChild()) && (tree.getLeftChild().getRootValue().compareTo(tree.getRightChild().getRootValue()) < 0);
      }
      if (root.equals(tree.getRightChild().getRootValue())){
        return isSelectionTree(tree.getLeftChild()) && isSelectionTree(tree.getRightChild()) && (tree.getRightChild().getRootValue().compareTo(tree.getLeftChild().getRootValue()) < 0);
      }
      return false;
    }
    if (!tree.hasLeftChild() && !tree.hasRightChild()){
      return true;
    }
    return false;
  }

  //exercise 8
  public static <T> boolean isPath(BinaryTree<T> tree, String path) {
    if (path == "" && tree.isEmpty()){
      return true;
    }
    if (tree.isEmpty()) return false;
    if (path == ""){
      return true;
    }
    if (tree.getRootValue().equals(path.charAt(0)+"")){
      return isPath(tree.getLeftChild(), path.substring(1)) || isPath(tree.getRightChild(), path.substring(1));
    }
    return false;
  }

  //exercise 9
  public static <T> BinaryTree<T> copyingToLevel(BinaryTree<T> tree, int level) {
    BinaryTree<T> Copy = new LinkedBinaryTree<>();
    if (tree.isEmpty()){
      return Copy;
    }
    if (level >= 0){
      Copy = new LinkedBinaryTree<>(tree.getRootValue());
      Copy.setLeftChild(copyingToLevel(tree.getLeftChild(), level-1));
      Copy.setRightChild(copyingToLevel(tree.getRightChild(), level-1));
    }
    return Copy;
  }

  //exercise 10
  public static <T> boolean isLevelK(BinaryTree<T> tree, T elem, int k) {
    if (tree.isEmpty()) return false;
    if (k == 0){
      if (tree.getRootValue().equals(elem)){
        return true;
      }else{
        return false;
      }
    }
    return isLevelK(tree.getLeftChild(), elem, k-1) || isLevelK(tree.getRightChild(), elem, k-1);
  }
}