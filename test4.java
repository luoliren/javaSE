package Demo;

public class test4 {
    public static void main(String[] args) {
        int n = 1;
        int sum = 0;

        while(sum < 9999) {
            n++;
            sum = sum + Factorial(n);
        }
        System.out.println(n);

    }
    public static int Factorial( int n) {
        if(n == 1) {
            return 1;
        } else {
            return n*Factorial(n-1);
        }

    }
}
