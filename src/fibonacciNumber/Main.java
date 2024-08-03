package fibonacciNumber;

public class Main {
    public static void main(String[] args) {
        int n1 = 0, n2 = 1;
        int n = 40; // حداکثر عدد فیبوناچی که نیاز داریم

        // حلقه برای تولید اعداد فیبوناچی تا n
        for (int i = 1; i <= n; i++) {
            int next = n1 + n2;
            n1 = n2;
            n2 = next;

            // چاپ اعداد فیبوناچی از 20 تا 40
            if (i >= 20) {
                System.out.println("Fibonacci number " + i + ": " + n1);
            }
        }
    }
}
