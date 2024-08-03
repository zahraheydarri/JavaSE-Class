package tamrin;

public class Main {
    public static void main(String[] args) {
        int a =19;
        int b =3;

        if (a>b){
            int c = a;
            a=b;
            b=c;
        }

        for (int i = a; i <= b; i++){
            System.out.println(i);
        }
    }
}
