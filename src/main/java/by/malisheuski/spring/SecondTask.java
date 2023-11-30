package by.malisheuski.spring;

public class SecondTask {

    public static void main(String[] args) {

        System.out.println(calculateUn(20));
    }

    public static Double calculateUn(int n) {
        double factorialSum = 0.0;
        double factorial = 1.0;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
            factorialSum += factorial;
        }

        double un = 1.0 / factorial * factorialSum;
        return Math.round(un * 1e6) / 1e6;
    }
}
