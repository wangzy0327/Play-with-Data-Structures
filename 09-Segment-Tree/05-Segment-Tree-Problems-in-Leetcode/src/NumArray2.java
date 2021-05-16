public class NumArray2 {

    private SegmentTree<Integer> segmentTree;
    public NumArray2(int[] nums) {
        if(nums.length > 0){
            Integer[] data = new Integer[nums.length];
            for(int i = 0;i < data.length;i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<Integer>(data,(a,b) -> a+b);
        }
    }

    public int sumRange(int left, int right) {
        if(segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null.");
        return segmentTree.query(left,right);
    }

//    ["NumArray","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange","sumRange"]
//            [[[-8261,2300,-1429,6274,9650,-3267,1414,-8102,6251,-5979,-5291,-4616,-4703]],[0,8],[4,5],[9,11],[2,11],[0,12],[9,12],[2,5],[0,9],[5,9],[0,1],[12,12],[6,7],[5,8],[1,1],[6,7],[10,11],[11,12],[7,8],[4,11],[8,9],[4,11],[2,9],[2,6],[11,12],[5,8],[11,11],[1,9],[12,12],[10,10],[4,7],[12,12],[8,10],[7,9],[4,7],[0,7],[8,9],[10,12],[0,9],[10,12],[7,12],[9,9],[0,12],[1,3],[8,8],[7,10]]
//

}
