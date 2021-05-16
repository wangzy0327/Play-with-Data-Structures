/**
 * leetcode 307 区域和检索 - 数组可修改
 */
public class NumArray {
    private SegmentTree<Integer> segmentTree;
    public NumArray(int[] nums) {
        if(nums.length > 0){
            Integer[] data = new Integer[nums.length];
            for(int i = 0;i < data.length;i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<Integer>(data,(a,b) -> a+b);
        }
    }

    public void update(int index, int val) {
        segmentTree.set(index,val);
    }

    public int sumRange(int left, int right) {
        return segmentTree.query(left,right);
    }
}


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
