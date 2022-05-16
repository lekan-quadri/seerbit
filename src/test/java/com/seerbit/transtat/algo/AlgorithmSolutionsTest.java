package com.seerbit.transtat.algo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmSolutionsTest {

    @Test
    void overlappingIntervals() {
        AlgorithmSolutions algo = new AlgorithmSolutions();
        List<Integer>[] array = new List[6];
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(4);
        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(7);
        List<Integer> l3 = new ArrayList<>();
        l3.add(5);
        l3.add(12);
        List<Integer> l4 = new ArrayList<>();
        l4.add(14);
        l4.add(20);
        List<Integer> l5 = new ArrayList<>();
        l5.add(18);
        l5.add(21);
        List<Integer> l6 = new ArrayList<>();
        l6.add(22);
        l6.add(24);

        array[0] = l1;
        array[1] = l2;
        array[2] = l3;
        array[3] = l4;
        array[4] = l5;
        array[5] = l6;

        assertEquals("[[1, 12], [14, 21], [22, 24]]",Arrays.toString(algo.overlappingIntervals(array)));
    }
}