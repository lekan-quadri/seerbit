package com.seerbit.transtat.algo;

import java.util.*;

public class AlgorithmSolutions {

    static int findXOR(int[] array) {
        int x = 0,y, xor = 0;
        for(int i = 0; i < array.length; i++){
            if(i == 0)
                x = array[i];
            else {
                y = array[i];
                xor = (x | y) & (~x | ~y);
            }
        }
        return xor;
    }

    public int[] subArrayWithMaxXOR(int[] array){

        return null;
    }


    public List<Integer>[] overlappingIntervals(List<Integer>[] array){
        List<Integer> intervals;
        List<List<Integer>> intArray = new ArrayList<>();
        /*{1,4},{3,7},{5,12},{14,20}
        loop through the array
        take the 1st element of the 1st pair, insert into 1st element of new pair
        compare the 2nd element of the 1st pair with the 1st element of the next pair,
            if overlaps (n.1 <= p.2), compare next pair (n.1 & p.2)
            if not - insert n.1 into p.2 then restart
         */
        int start, finish, counter;
        for(int i = 0; i < array.length; i++){
            intervals = new ArrayList<>();
            start = array[i].get(0).intValue();
            counter = i + 1;
            if(counter < array.length){
                while(counter < array.length){
                    if(array[i].get(1).intValue() >= array[counter].get(0).intValue()){
                        counter++;
                        i++;
                    } else {
                        finish = array[i].get(1).intValue();
                        intervals.add(start);
                        intervals.add(finish);

                        intArray.add(intervals);
                        break;
                    }
                }
            } else {
                finish = array[i].get(1).intValue();
                intervals.add(start);
                intervals.add(finish);

                intArray.add(intervals);
            }
        }

        List<Integer>[] retArray = new List[intArray.size()];
        retArray = intArray.toArray(retArray);
        return retArray;
    }
    
}
