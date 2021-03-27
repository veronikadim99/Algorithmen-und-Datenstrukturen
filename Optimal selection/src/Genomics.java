import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Genomics {

    private static long counter;

    public static long optBottomUp(String strang, String[] dictionary) {
        // TODO

        long[] counterForGenomics = new long[strang.length() * strang.length()];

        int i  = 0;

        for (int j = strang.length() - 1; j >= 0; j--) {   //loop threw the string, reads character from the end to the beginning
           for(i = 0; i < dictionary.length; i++) {

                if (j + dictionary[i].length() > strang.length()) {
                    continue;

                }


                if (strang.startsWith(dictionary[i], j)) {
                    //if the chosen par of the string starts with the word of the dictionary
                    boolean lengthCpunter = false;
                    lengthCpunter = (j + dictionary[i].length() == strang.length()) ? true:false;
                    if(lengthCpunter == true){
                        counterForGenomics[j]++;
                    }
                    if(lengthCpunter == false){
                        counterForGenomics[j] += counterForGenomics[j + dictionary[i].length()];
                    }

                    }
                }


            }


        counter = counterForGenomics[0];  //return the first elements in the count array
        return counter;
    }

    public static void main(String[] args) {


    }
}
