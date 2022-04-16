package Algorithm.test;

class A {
    String name = "parents";

    A(){
        System.out.println("parents 생성");
    }

    void print() {
        System.out.println("parantes");
    }
}

class AA extends A {
    String name = "child";

    AA(){
        System.out.println("child 생성");
    }

    void print() {
        System.out.println("child");
    }

    void print2() {
        System.out.println("speical method");
    }
}

public class Main {
    public static void main(String[] args) {
        // 업캐스팅을하면, 변수는 항상 부모의 것을 가리킴
        // 그러나 메소드는 자식을 가리킴

        // 업캐스팅 변수    : 부모에 접근
        AA aa = new AA();
        A a = (A) aa;
        System.out.println(a.name);
        // 업캐스팅 메소드  : 자식에 접근
        a.print();

        int i = 0;
        int j = i++;

        System.out.println(j);


        // // 바로 업캐스팅 후, 다운캐스팅
        // A a = new AA();
        // AA aa = (AA) a;
        // System.out.println(aa.name);
        // // 메소드  : 자식에 접근
        // aa.print();
    }
}
