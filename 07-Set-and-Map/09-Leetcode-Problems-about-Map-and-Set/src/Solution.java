import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * leetcode 349 两个数组的交集
 */
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new TreeSet<>();
        for(int num:nums1)
            set.add(num);

        ArrayList<Integer> list = new ArrayList<>();
        for(int num:nums2)
            if(set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        int[] res = list.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }
}
