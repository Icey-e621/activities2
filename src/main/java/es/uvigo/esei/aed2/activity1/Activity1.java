package es.uvigo.esei.aed2.activity1;

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

import java.util.Stack;

public class Activity1 {

  /**
   * exercise 1
   *
   * @param number numero usado para calcular factorial.
   * @return el factorial de number.
   */
  public static long factorial(int number) {
    if (number<=1){
      return 1;
    }
    return number*factorial(number-1);
  }

  /**
   * exercise 2
   *
   * @param number numero usado para calcular el cuadrado.
   * @return el cuadrado de number.
   */
  public static int square(int number) {
    if (number == 0)
      return 0; // base case

   return square(number-1) + number + number - 1;
  }

  /**
   * exercise 3
   *
   * @param number numero usado para sumar sus digitos.
   * @return la suma de los dígitos de number.
   */
  public static int sumDigits(int number) {
    if (number <= 0){
      return 0;
    }
    return number % 10 + sumDigits((int)number/10);
  }

  /**
   * exercise 4
   *
   * @param number1 primer numero usado para calcular el MCD
   * @param number2 segundo numero usado para calcular el MCD
   * @return el MCD de number1 y number2
   */
  public static int mcd(int number1, int number2) {
    if (number1 == number2){
      return number1;
    }
    return number1 < number2 ? mcd(number2-number1, number1) : mcd(number1-number2, number1);
  }

  /**
   * exercise 5
   *
   * @param str String usado para invertir caracteres.
   * @return el string invertido.
   */
  public static String invert(String str) {
    if (str == ""){
      return "";
    }
    return invert(str.substring(1,str.length())) + str.charAt(0);
  }

  /**
   * exercise 6
   *
   * @param values el array usado para sumar los valores.
   * @return suma todos los valores del array.
   */
  public static int addValues(int[] values) {
    if (values.length == 1){
      return values[0];
    }
    int [] newvals = new int[values.length-1];
    for (int i = 0; i < newvals.length; i++) {
      newvals[i] = values[i];
    }
    newvals[newvals.length-1] += values[values.length-1];
    return addValues(newvals);
  }

  /**
   * exercise 7
   *
   * @param values array usado para el proceso de inversion.
   */
  public static void invertArray(int[] values) {
    invertArray2(values, (values.length/2)-1);
  }
  private static void invertArray2(int[] valores, int left){
    if (left >= 0){
      valores[left] ^= valores[valores.length-left-1];
      valores[valores.length-left-1] ^= valores[left];
      valores[left] ^= valores[valores.length-left-1];
      invertArray2(valores, left-1);
    }
  }

  /**
   * exercise 8
   *
   * @param values array usado para encontrar el entero menor.
   * @return el menor entero del array.
   */
  public static int minimum(int[] values) {
    if (values.length == 1){
      return values[0];
    }
    int [] newvals = new int[values.length-1];
    for (int i = 0; i < newvals.length; i++) {
      newvals[i] = values[i];
    }
    if (values[values.length-1] < newvals[newvals.length-1]){
      newvals[newvals.length-1] = values[values.length-1];
    }
    return minimum(newvals);
  }

  /**
   * exercise 9
   *
   * @param values array usado para la busqueda binaria.
   * @param number el numero a buscar.
   * @return el indice de number en el array o -1 si no está.
   */
  public static int binarySearch(double[] values, double number) {
    return binarySearch(values, number,0,values.length-1);
  }
  public static int binarySearch(double[] values, double number, int start,int end) {
    int m = (start + end )/ 2;
    if (values[m] < number){
      return binarySearch(values, number,m,end);
    }else if (values[m]> number){
      return binarySearch(values, number,start,m);
    }
    else if (start == end) return -1;
    return m;
  }
  

  /**
   * exercise 10
   *
   * @param <T> Tipo de los elementos contenidos en el Stack.
   * @param stack la pila a copiar.
   * @return una nueva pila con los mismos elementos que la original.
   */
  public static <T> Stack<T> copyStack(Stack<T> stack) {
    return copyStack(stack,new Stack<>(), stack.size());
  }
  public static <T> Stack<T> copyStack(Stack<T> stack, Stack<T> ToRet, int left) {
    if (left <= 0){
      return ToRet;
    }
    T temp = stack.pop();
    ToRet.addFirst(temp);
    stack.addFirst(temp);
    return copyStack(stack,ToRet,left-1);
  }
}