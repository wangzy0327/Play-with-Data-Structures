/**
 * 数组最大的优点：快速查询。scores[2]
 * 数组最好应用于“索引有语意”的情况
 * 但并非所有有语意的索引都适用于数组
 * ep : 身份证号: 110103198512166666
 * 数组也可以处理”索引没有语意“的情况
 * 在这一章，主要处理”索引没有语意“的情况数组的使用
 * 索引没有语意，如何表示没有元素?
 * 如何添加元素？如何删除元素?
 * 基于java的数组，二次封装属于我们自己的数组类(动态数组)
 */
public class Main {
    public static void main(String[] args) {
        Array arr = new Array(20);
        for(int i = 0;i < 10;i++)
            arr.addLast(i);
        arr.add(1,100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);
//        [-1,0,100,1,2,3,4,5,6,7,8,9]

        arr.remove(2);
        System.out.println(arr);
//        [-1,0,1,2,3,4,5,6,7,8,9]

        arr.removeElement(4);
        System.out.println(arr);
//        [-1,0,1,2,3,5,6,7,8,9]

        arr.removeFirst();
        System.out.println(arr);
//        [0,1,2,3,5,6,7,8,9]

    }
}
