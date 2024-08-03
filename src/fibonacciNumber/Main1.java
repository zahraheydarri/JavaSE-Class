package fibonacciNumber;

public class Main1 {
    public static void main(String[] args) {
        Integer a=0 , b=1 , c;
        for (int i=3; i<=50; i++){
            c=a+b;
            System.out.println(c/b.doubleValue());
            a=b;
            b=c;
        }
    }
}
