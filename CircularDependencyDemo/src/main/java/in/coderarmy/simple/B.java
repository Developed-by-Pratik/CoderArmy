package in.coderarmy.simple;

public class B {

    private final A a;

//    public B(A a) {
//        this.a = a;
//    }

    public B() {
        System.out.println("B created !");
        this.a = new A();
    }

}
