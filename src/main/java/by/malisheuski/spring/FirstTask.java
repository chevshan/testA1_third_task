package by.malisheuski.spring;


public class FirstTask {
    public static void main(String[] args) {
        int value = 257;
        String ipv4 = "0.0.1.12";
        String string = parseToIpV4(value);
        parseToInt(string);
    }

    public static String parseToIpV4(int value) {
        StringBuilder result = new StringBuilder();

        while (value > 255) {
            int balance = value % 256;
            value = (value - balance) / 256;
            result.insert(0, '.' + Integer.toString(balance));
        }

        result.insert(0, value);

        int numberOfDots = result.toString().split("\\.").length - 1;

        for (int i = numberOfDots; i < 3; i++) {
            result.insert(0, "0.");
        }

        return result.toString();
    }

    public static int parseToInt(String ipv4) {
        String[] octets = ipv4.split("\\.");
        int result = 0;
        int pow = 3;
        for (String octet : octets) {
            result += (int) (Integer.parseInt(octet) * Math.pow(256, pow));
            pow--;
        }

        return result;
    }
}
