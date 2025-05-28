import java.util.*;

class ConversionOfNumberSystems{
    public static String dectoany(double n, int base) {
        int intPart = (int) n;
        double fracPart = n - intPart;
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder result = new StringBuilder();

        while (intPart > 0) {
            int remainder = intPart % base;
            result.insert(0, digits[remainder]);
            intPart /= base;
        }

        if (result.length() == 0) {
            result.append("0");
        }

        if (fracPart != 0.0) {
            result.append(".");
            for (int i = 0; i < 4 && fracPart != (int) fracPart; i++) {
                fracPart *= base;
                int digit = (int) fracPart;
                result.append(digits[digit]);
                fracPart -= digit;
            }
        }

        return result.toString();
    }

    public static double anytodec(String input, int base) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F'};

        String intPart = "", fracPart = "";
        boolean isFraction = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '.') {
                isFraction = true;
            } else if (isFraction) {
                fracPart += ch;
            } else {
                intPart += ch;
            }
        }

        double decimal = 0;
        int power = 0;

        for (int i = intPart.length() - 1; i >= 0; i--) {
            char ch = intPart.charAt(i);
            for (int j = 0; j < base; j++) {
                if (digits[j] == ch) {
                    decimal += j * Math.pow(base, power);
                    break;
                }
            }
            power++;
        }

        if (isFraction) {
            power = -1;
            for (int i = 0; i < fracPart.length(); i++) {
                char ch = fracPart.charAt(i);
                for (int j = 0; j < base; j++) {
                    if (digits[j] == ch) {
                        decimal += j * Math.pow(base, power);
                        break;
                    }
                }
                power--;
            }
        }

        return decimal;
    }

    public static String octbin(String octal) {
        String[] binaryLookup = {"000", "001", "010", "011", "100", "101", "110", "111"};
        StringBuilder binary = new StringBuilder();

        for (int i = 0; i < octal.length(); i++) {
            char ch = octal.charAt(i);
            if (ch == '.') {
                binary.append('.');
            } else {
                int digit = Character.getNumericValue(ch);
                binary.append(binaryLookup[digit]);
            }
        }

        return binary.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose which operation you want to perform:");
        System.out.println("1. Decimal to Binary");
        System.out.println("2. Binary to Decimal");
        System.out.println("3. Octal to Binary");
        System.out.println("4. Decimal to Hexadecimal");
        System.out.println("5. Hexadecimal to Decimal");

        int choice = sc.nextInt();
        double num;
        String input;

        switch (choice) {
            case 1:
                System.out.println("Enter the Decimal number:");
                num = sc.nextDouble();
                System.out.println("Result: " + dectoany(num, 2));
                break;

            case 2:
                System.out.println("Enter the Binary number:");
                input = sc.next();
                System.out.println("Result: " + anytodec(input, 2));
                break;

            case 3:
                System.out.println("Enter the Octal number:");
                input = sc.next();
                System.out.println("Result: " + octbin(input));
                break;

            case 4:
                System.out.println("Enter the Decimal number:");
                num = sc.nextDouble();
                System.out.println("Result: " + dectoany(num, 16));
                break;

            case 5:
                System.out.println("Enter the Hexadecimal number:");
                input = sc.next();
                System.out.println("Result: " + anytodec(input, 16));
                break;

            default:
                System.out.println("Invalid choice! Please choose between 1 and 5.");
        }

        sc.close();
    }
}
