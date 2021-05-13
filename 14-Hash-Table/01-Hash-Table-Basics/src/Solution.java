/**
 * leetcode 387 字符串中的第一个唯一字符
 *
 * 哈希表充分体现了算法设计领域的经典思想：空间换时间
 */
public class Solution {
    public int firstUniqChar(String s) {
        //int[26] freq 就是一个哈希表 每一个字符都和一个索引相对应
        // O(1)的查找操作
        // a->0
        // b->1
        // ...      index = ch - 'a'  f(ch) = ch - 'a'
        // z->25

        int[] freq = new int[26];
        for(int i = 0;i < s.length();i++)
            freq[s.charAt(i)-'a']++;
        for(int i = 0;i < s.length();i++)
            if(freq[s.charAt(i)-'a'] == 1)
                return i;
        return -1;
    }
}
