package alg;

import java.util.*;

/**
 * @author chimney
 * @date 2022/1/11
 * https://leetcode-cn.com/problems/top-k-frequent-words/
 */
public class LC692 {
    public List<String> topKFrequent(String[] words, int k) {
        // calculate frequency
        Map<String, Integer> map = new HashMap<String, Integer>(words.length * 4 / 3 + 1);
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Comparable[]> minHeap = new PriorityQueue<>(k,
                (o1, o2) -> {
                        if( o1[1].compareTo(o2[1]) == 0){
                            return o2[0].compareTo(o1[0]);
                        }else {
                            return o1[1].compareTo(o2[1]);
                        }
                }
        );
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(minHeap.size() < k){
                minHeap.add(new Comparable[]{entry.getKey(), entry.getValue()});
            }else {
                if(minHeap.comparator().compare(minHeap.peek(), new Comparable[]{entry.getKey(), entry.getValue()}) < 0){
                    minHeap.poll();
                    minHeap.add(new Comparable[]{entry.getKey(), entry.getValue()});

                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!minHeap.isEmpty()){
            result.add(minHeap.poll()[0].toString());
        }
        Collections.reverse(result);
        return result;

    }

    public static void main(String[] args) {
        System.err.println(new LC692().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.err.println(new LC692().topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        System.err.println(new LC692().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 1));
    }

}
