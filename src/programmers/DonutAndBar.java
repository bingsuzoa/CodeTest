package programmers;

import java.util.*;
public class DonutAndBar {
    public int[] solution(int[][] edges){
        HashMap<Integer, Set<Integer>> graph = findMultiValue(edges);
        HashMap<Integer, Set<Integer>> totalMap = makeTotalMap(edges);
        int topKey = findTopKey(graph, totalMap);

        return findGroup(graph, totalMap, topKey);
    }

    public HashMap<Integer, Set<Integer>> findMultiValue(int[][] edges){
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int key = edges[i][0];
            int value = edges[i][1];
            if (!graph.containsKey(key)) {
                graph.put(key, new HashSet<>());
                graph.get(key).add(value);
            } else {
                Set<Integer> set = graph.get(key);
                set.add(value);
                graph.put(key, new HashSet<>());
                for(int j : set){
                    graph.get(key).add(j);
                }
                set.clear();
            }
        }
        Iterator<Integer> keys = graph.keySet().iterator();
        while(keys.hasNext()){ int key = keys.next();
            if(graph.get(key).size() == 1) keys.remove();}
        return graph;
    }
    public HashMap<Integer, Set<Integer>> makeTotalMap(int[][] edges){
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int key = edges[i][0];
            int value = edges[i][1];
            if (!graph.containsKey(key)) {
                graph.put(key, new HashSet<>());
                graph.get(key).add(value);
            } else {
                Set<Integer> set = graph.get(key);
                set.add(value);
                graph.put(key, new HashSet<>());
                for(int j : set){
                    graph.get(key).add(j);
                }
                set.clear();
            }
        }
        return graph;
    }

    public int findTopKey(HashMap<Integer, Set<Integer>> graph, HashMap<Integer, Set<Integer>> totalMap){
        int topKey = 0;
        Iterator<Integer> keys = graph.keySet().iterator();
        while(keys.hasNext()) {
            int key = keys.next();
            if (graph.get(key).size() > 2) {
                topKey = key;
                break;
            } else {
                Set<Integer> tempSet = new HashSet<>();
                tempSet.add(key);
                Set<Integer> valueSet = graph.get(key);
                for (int value : valueSet) {
                    while (!tempSet.contains(value)) {
                        tempSet.add(value);
                        if (totalMap.containsKey(value)) {
                            Set<Integer> tempValueSet = totalMap.get(value);
                            if (tempValueSet.size() == 2) break;
                            for (int tempValue : tempValueSet) value = tempValue;
                        } else topKey = key;
                    }}
                tempSet.clear();
            }}
        return topKey;}


    public int[] findGroup(HashMap<Integer, Set<Integer>> graph
                        , HashMap<Integer, Set<Integer>> totalMap
                        , int topKey){
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
        return answer;
    }
}