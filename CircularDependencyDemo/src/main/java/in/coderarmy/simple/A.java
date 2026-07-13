package in.coderarmy.simple;

public class A {

    private final B b;

//    public A(B b) {
//        this.b = b;
//    }

    public A() {
        System.out.println("A created !");
        this.b = new B();
    }


}
