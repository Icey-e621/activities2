package es.uvigo.esei.aed2.activity6.anagrams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

public class Anagrams {

  public static Set<Set<String>> getAnagrams(List<String> words) {
    Set<Set<String>> set = new HashSet<>();
    HashMap<String, Set<String>> map = new HashMap<>();

    for (String word : words) {
      //create a word set of all the words that contain the same letters (anagrams) or get the already existing one    
      Set<String> wSet = map.get(sortLetters(word));
      if (wSet == null){
        wSet = new HashSet<>();
        wSet.add(word);
        map.put(sortLetters(word), wSet);
      }else{
        //add current word
        wSet.add(word);
      }
    }
    map.values().forEach(z->set.add(z));
    return set;
  }

  private static String sortLetters(String str) {
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
}
