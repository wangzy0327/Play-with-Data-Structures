public class Sum {
    public static int sum(int[] arr){
        return sum(arr,0);
    }

    /**
     * 计算arr[l...n]这个区间内所有数字的和
     * @param arr
     * @param l
     * @return
     */
    private static int sum(int[] arr,int l){
        //求解最基本的问题
        if(l == arr.length)
            return 0;
        //把原问题转化为更小的问题
        //注意递归函数的“宏观”语意
        //递归函数就是一个函数，完成一个功能
        return arr[l] + sum(arr,l+1);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(sum(nums));
    }
}
