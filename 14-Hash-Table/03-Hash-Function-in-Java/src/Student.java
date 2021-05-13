public class Student {
    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode(){
        int B = 31;

        //当整型溢出时，比如最大整型值+1，会变成最小的整型值
        // 进行循环；即使产生整型溢出，计算范围还是在整型范围内，
        // 只不过不是真正数学计算的结果，但是不影响，仍然满足函数语义
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        //不区分大小写
        hash = hash * B + firstName.toLowerCase().hashCode();
        //区分大小写
//        hash = hash * B + firstName.hashCode();
//        hash = hash * B + lastName.hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(this == o)
            return true;
        if(getClass() != o.getClass())
            return false;
        Student another = (Student)o;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }

}
