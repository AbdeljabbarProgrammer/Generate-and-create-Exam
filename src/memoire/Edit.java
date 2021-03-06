/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire;

import static java.lang.ProcessBuilder.Redirect.to;

/**
 *
 * @author abdeljabbar
 */
public class Edit {
    public static String[] getAllLists(String[] elements, int lengthOfList)
{
    //initialize our returned list with the number of elements calculated above
    String[] allLists = new String[(int)Math.pow(elements.length, lengthOfList)];

    //lists of length 1 are just the original elements
    if(lengthOfList == 1) return elements; 
    else {
        //the recursion--get all lists of length 3, length 2, all the way up to 1
        String[] allSublists = getAllLists(elements, lengthOfList - 1);

        //append the sublists to each element
        int arrayIndex = 0;

        for(int i = 0; i < elements.length; i++){
            for(int j = 0; j < allSublists.length; j++){
                //add the newly appended combination to the list
                allLists[arrayIndex] = elements[i] + allSublists[j];
                arrayIndex++;
            }
        }
        return allLists;
    }
}



    
}
