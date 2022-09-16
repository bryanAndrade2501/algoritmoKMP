package com.KMP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // TODO Auto-generated method stub
        // char[] text = new String(
        // "FSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG")
        // .toCharArray();
        // AACGAACDAACHAACFACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFSDAACGTBFSDFSDGSDFSDAACGFASSTGG
        // AACGTDGDFSFSDFAACGAACDAACHAACFDAACGFASSTGG
        // AACGAACDAACHAACFTBFSDFSDGSDFSDAACGFASSTGG AACGAACDAACHAACF
        //char[] pattern = new String("0101001001010111").toCharArray();
        char[] pattern = new String("AAABBAAA").toCharArray();
        try {

            // Lectura de Archivos
            File doc = new File(
                    "D:\\CadenasKMP.txt");

            BufferedReader obj = new BufferedReader(new FileReader(doc));

            String linea;
            int cont = 0;
            while ((linea = obj.readLine()) != null) {
                // System.out.println(linea);
                cont++;
                // System.out.println("En la l�nea: "+cont+" encontre esta coincidencia:
                // "+fuerzaBruta(linea, patronEnString));
                System.out.println("En la línea: " + cont + " encontre esta coincidencia: "
                        + findKMP(linea.toCharArray(), pattern));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        // System.out.println(findKMP(text, pattern));

    }


    public static String findKMP(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        boolean comprobar = false;
        String strResultados = "";
        if (m == 0)
            return strResultados; // trivial search for empty string
            comprobar=false;
        int[] fail = computeFailKMP(pattern); // computed by private utility
        //System.out.println(Arrays.toString(fail)); // Para visualizar el contenido de la funci�n de fallo.
        int j = 0; // index into text
        int k = 0; // index into pattern
        while (j < n) {
            if (text[j] == pattern[k]) { // pattern[0..k] matched thus far
                if (k == m - 1) {
                    strResultados += (j - m + 1) + ","; // match is complete

                    k = 0;
                }
                j++; // otherwise, try to extend match
                k++;
            } else if (k > 0)
                k = fail[k - 1]; // reuse suffix of P[0..k-1]
            else
                j++;
        }

        return strResultados; // reached end without match
    }

    private static int[] computeFailKMP(char[] pattern) {
        int m = pattern.length;
        int[] fail = new int[m]; // Mismo tama�o que el patr�n
        int j = 1;
        int k = 0;
        while (j < m) { // compute fail[j] during this pass, if nonzero
            if (pattern[j] == pattern[k]) { // k + 1 characters match thus far
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) // k follows a matching prefix
                k = fail[k - 1];
            else // no match found starting at j
                j++;
        }
        // System.out.println(fail);
        return fail;
    }
}
