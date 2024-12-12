package programmers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
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
    public void findGroup(){
//        int[][] edges = {{2,1}, {2,3}, {3,3}};
//        int[][] edges = {{2,3},{4,3},{1,1},{2,1}};
        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        HashMap<Integer, Set<Integer>> graph = donutAndBar.findMultiValue(edges);
        HashMap<Integer, Set<Integer>> totalMap = donutAndBar.makeTotalMap(edges);
        int topKey = donutAndBar.findTopKey(graph,totalMap);
        int[] actual = donutAndBar.findGroup(graph,totalMap, topKey);
        int[] expected = {4,0,1,2};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void findGroupTest(){
//                int[][] edges = {{2,3},{4,3},{1,1},{2,1}};
//        int[][] edges = {{2,5},{5,1},{1,3},{3,4},{1,6},{6,7},{7,8},{8,1},{2,9}};
        int[][] edges = {{2,3},{2,4},{3,5},{5,6}};
//        int[][] edges = {{1,2},{2,3},{1,4},{4,5}};
//        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        HashMap<Integer, Set<Integer>> graph = donutAndBar.findMultiValue(edges);
        HashMap<Integer, Set<Integer>> totalMap = donutAndBar.makeTotalMap(edges);
        int topKey = donutAndBar.findTopKey(graph,totalMap);

            int barCount = 0;
            int eightCount;
            int donutCount;

        Set<Integer> valueSet = graph.get(topKey);
        Set<Integer> tempSet = new HashSet<>();
        for(int value : valueSet){
            tempSet.clear();
            if(!graph.containsKey(value)){
                if(!totalMap.containsKey(value)){
                    barCount++;
                } else {
                    int tempKey = value;
                    while(!tempSet.contains(tempKey)){
                        tempSet.add(tempKey);
                        if(totalMap.containsKey(tempKey)){
                            if(totalMap.get(tempKey).size() == 2) break;
                            for(int tempValue : totalMap.get(tempKey)){
                                tempKey = tempValue;
                            }
                        } else {
                            barCount++;
                            break;
                        }
                    }
                }
            }
        }
        eightCount = graph.size()-1;
        donutCount = graph.get(topKey).size() - eightCount - barCount;
            int[] answer = {topKey, donutCount, barCount, eightCount};
            for(int i : answer) System.out.println(i);
        }

}