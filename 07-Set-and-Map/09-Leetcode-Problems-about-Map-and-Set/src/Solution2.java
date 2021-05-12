import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 350 两个数组的交集 II
 */
public class Solution2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new TreeMap();
        for(int num:nums1){
            if(!map.containsKey(num))
                map.put(num,1);
            else
                map.put(num,map.get(num) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int num:nums2){
            if(map.containsKey(num)) {
                list.add(num);
                map.put(num,map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }
        int[] res = list.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }
}
