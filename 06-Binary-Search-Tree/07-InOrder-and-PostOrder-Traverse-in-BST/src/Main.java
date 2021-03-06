public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num:nums)
            bst.add(num);

        ///////////////////
        //         5     //
        //        / \    //
        //       3  6    //
        //      / \  \   //
        //     2  4   8  //
        ////////////////////
        bst.preOrder();
        // 5 3 2 4 6 8
        System.out.println();
//        System.out.println(bst);

        bst.inOrder();
        //2 3 4 5 6 8
        System.out.println();

        bst.postOrder();
        //2 4 3 8 6 5
        System.out.println();


    }
}
