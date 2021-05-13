import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        Student student1 = new Student(5,501,"F","zy");
        System.out.println(student1.hashCode());

        Student student2 = new Student(5,501,"f","zy");
        System.out.println(student2.hashCode());

        HashSet<Student> set = new HashSet();
        set.add(student1);

        HashMap<Student,Integer> scores = new HashMap();
        scores.put(student1,100);

        //如果注释掉Student 的 hashcode，不会影响，因为会继承Object类的hashCode（以对象地址空间转化为hashcode整型）
    }
}
