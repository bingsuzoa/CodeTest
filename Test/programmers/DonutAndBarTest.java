package programmers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DonutAndBarTest {
    DonutAndBar donutAndBar =  new DonutAndBar();

    @Test
    public void solutionTest(){
        int[][] edges = {{2,1}, {2,3}, {3,3}};
        int[] actual = donutAndBar.solution(edges);
        int[] expected = {2,1,1,0};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findTopKey(){
        int[][] edges = {{2,1}, {2,3}, {3,3}};
        HashMap<Integer, Set<Integer>> graph = donutAndBar.findMultiValue(edges);
        HashMap<Integer, Set<Integer>> totalMap = donutAndBar.makeTotalMap(edges);
        int actual = donutAndBar.findTopKey(graph,totalMap);
        assertEquals(2, actual);
    }

    @Test
    public void findGroup(){
//        int[][] edges = {{2,1}, {2,3}, {3,3}};
//        int[][] edges = {{2,3},{4,3},{1,1},{2,1}};
        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        HashMap<Integer, Set<Integer>> graph = donutAndBar.findMultiValue(edges);
        HashMap<Integer, Set<Integer>> totalMap = donutAndBar.makeTotalMap(edges);
        int topKey = donutAndBar.findTopKey(graph,totalMap);
        int[] actual = donutAndBar.findGroup(graph,totalMap,topKey);
        int[] expected = {4,0,1,2};
        assertArrayEquals(expected,actual);
    }

}