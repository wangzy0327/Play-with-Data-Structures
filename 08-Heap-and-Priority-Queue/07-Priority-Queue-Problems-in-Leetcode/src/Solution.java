import java.util.*;

/**
 * leetcode-347 前k个高频元素
 */
public class Solution {
    private class Freq{
        int e,freq;
        public Freq(int e,int freq){
            this.e = e;
            this.freq = freq;
        }
    }

    /**
     * 需要构建包含k个元素的最小堆
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        for(int num:nums){
            if(map.containsKey(num))
                map.put(num,map.get(num) + 1);
            else
                map.put(num,1);
        }
        //PriorityQueue 默认为最小堆
//        PriorityQueue<Freq> pq = new PriorityQueue<>(k,Comparator.comparing((Freq o) ->o.freq) );
        PriorityQueue<Freq> pq = new PriorityQueue<>(k,(o1,o2)-> o1.freq - o2.freq );
//        Comparator.comparing((Freq o) ->{o->freq});
        for(int key:map.keySet()){
            if(pq.size() < k )
                pq.offer(new Freq(key,map.get(key)));
            else if(map.get(key) > pq.peek().freq){
                pq.poll();
                pq.offer(new Freq(key,map.get(key)));
            }
        }
        int[] res = new int[k];
        for(int i = 0;!pq.isEmpty();i++)
            res[i] = pq.poll().e;
        return res;
    }

    public int[] topKElement2(int[] nums, int k){
        Map<Integer,Integer> map = new HashMap();
        for(int num:nums){
            if(map.containsKey(num))
                map.put(num,map.get(num) + 1);
            else
                map.put(num,1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k,(a,b) -> map.get(a)-map.get(b));
        for(int key:map.keySet()){
            if(pq.size() < k )
                pq.offer(key);
            else if(map.get(key) > map.get(pq.peek())){
                pq.poll();
                pq.offer(key);
            }
        }
        int[] res = new int[k];
        for(int i = 0;!pq.isEmpty();i++)
            res[i] = pq.poll();
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        int[] res = new Solution().topKFrequent(nums,k);
        System.out.println(Arrays.toString(res));
    }
}
