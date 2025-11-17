package es.uvigo.esei.aed2.activity5;

import java.util.List;
import java.util.logging.Logger;

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

import es.uvigo.esei.aed2.tree.nary.Tree;

public class Activity5 {
  private static <T> boolean nullCheck(Tree<T> tree){
    return tree==null ||tree.isEmpty();
  }

  //exercise 1
  public static int getSum(Tree<Integer> tree) {
    if (nullCheck(tree)) return 0;
    if (tree.hasChildren() || tree.hasRightSibling()){
      return tree.getRootValue()+getSum(tree.getRightSibling())+getSum(tree.getLeftMostChild());
    }
    return tree.getRootValue();
  }

  //exercise 2
  public static <T> boolean isEqualStructure(Tree<T> treeA, Tree<T> treeB) {
    if (nullCheck(treeA) && nullCheck(treeB)) return true;
    if (nullCheck(treeA) || nullCheck(treeB)) return false;
    if (!treeA.getRootValue().equals(treeB.getRootValue())) return false;
    return isEqualStructure(treeA.getLeftMostChild(), treeB.getLeftMostChild()) && isEqualStructure(treeA.getRightSibling(), treeB.getRightSibling());
  }

  //exercise 3
  public static <T> boolean is23Tree(Tree<T> tree) {
    if (nullCheck(tree)) return true;
    if (tree.hasRightSibling() && tree.getRightSibling().hasRightSibling() ? (tree.getRightSibling().getRightSibling().hasRightSibling() ? false : true) : true) 
      return (is23Tree(tree.getLeftMostChild())) &&
        (is23Tree(tree.getRightSibling())) &&
        (tree.getRightSibling().hasRightSibling() ? is23Tree(tree.getRightSibling().getRightSibling()) : true);
    return false;
  }

  //exercise 4
  public static <T extends Comparable<T>> boolean isSelection(Tree<T> tree) {
    if (nullCheck(tree)) return true;
    if (!tree.hasChildren() || !tree.getLeftMostChild().hasRightSibling()){
      return true;
    }
    Logger.getLogger(Activity5.class.getName()).info(lowestValSiblings(tree, tree.getLeftMostChild().getRootValue()).toString());
    if (tree.getRootValue().equals(lowestValSiblings(tree, tree.getLeftMostChild().getRootValue()))){
      return isSelection(tree.getLeftMostChild()) && isSelection(tree.getRightSibling());
    }
    return false;
  }
  private static <T extends Comparable<T>> T lowestValSiblings(Tree<T> tree, T val){
    T low = val;
    if (!tree.hasRightSibling()) return low;
    if (low.compareTo(tree.getRightSibling().getRootValue()) > 0) low = tree.getRightSibling().getRootValue();
    return lowestValSiblings(tree.getRightSibling(), low);
  }

  //exercise 5
  public static <T> int getLevel(Tree<T> tree, T value) {
    if (nullCheck(tree)) return -1;
    if (tree.getRootValue().equals(value)) return 0;
    return getLevel(tree.getLeftMostChild(), value) > getLevel(tree.getRightSibling(), value) ?
      (getLevel(tree.getLeftMostChild(), value) == getLevel(tree.getRightSibling(), value) ? -1 : getLevel(tree.getLeftMostChild(), value)+1)
        : getLevel(tree.getRightSibling(), value);
  }

  //exercise 6
  public static <T> int getGrade(Tree<T> tree) {
    if (nullCheck(tree)) return -1;
    if (!tree.hasChildren() && !tree.hasRightSibling()) return 1;
    return Math.max(1+getGrade(tree.getRightSibling()), getGrade(tree.getLeftMostChild()));
  }

  //exercise 7
  public static <T> int getHeight(Tree<T> tree) {
    if (nullCheck(tree)) return -1;
    if (!tree.hasChildren() && !tree.hasRightSibling()) return 0;
    return Math.max(1+getHeight(tree.getLeftMostChild()), getHeight(tree.getRightSibling()));
  }

  //exercise 8
  public static <T> void breadthOrder(Tree<T> tree){
    if (!nullCheck(tree)){
      breadthOnlyOrder(tree);
      breadthOrder(tree.getLeftMostChild());
      breadthOrderS(tree.getRightSibling());
    }
  }
  private static <T> void breadthOrderS(Tree<T> tree){
    if (!nullCheck(tree)){
      breadthOrder(tree.getLeftMostChild());
    }
  }
  private static <T> void breadthOnlyOrder(Tree<T> tree){
    if (!nullCheck(tree)){
      System.out.print(tree.getRootValue());
      breadthOnlyOrder(tree.getRightSibling());
    }
  }

  //exercise 9
  public static int getNumberOfEvenValues(Tree<Integer> tree) {
    if (nullCheck(tree)) return 0;
    if (tree.getRootValue() % 2 == 0) return 1+ getNumberOfEvenValues(tree.getLeftMostChild()) + getNumberOfEvenValues(tree.getRightSibling());
    return getNumberOfEvenValues(tree.getLeftMostChild()) + getNumberOfEvenValues(tree.getRightSibling());
  }

  //exercise 10 
  public static <T> void getListLeaves(Tree<T> tree, List<T> list) {
    if (!nullCheck(tree)){
      if (!tree.hasChildren() && !list.contains(tree.getRootValue())) list.add(tree.getRootValue());
      getListLeaves(tree.getLeftMostChild(), list);
      getListLeaves(tree.getRightSibling(), list);
    }
  }
  
}