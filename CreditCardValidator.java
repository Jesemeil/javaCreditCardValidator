import java.util.Scanner;

public class CreditCardValidator {

    public static boolean luhnAlgorithm(long cardNumber) {
        String cardNumberStr = Long.toString(cardNumber);
        int cardLength = cardNumberStr.length();
        int sum = 0;
        boolean doubleDigit = false;

        for (int i = cardLength - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumberStr.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return sum % 10 == 0;
    }

    public static String validateCreditCard(long cardNumber) {
        String cardNumberStr = Long.toString(cardNumber);
        int cardLength = cardNumberStr.length();
        String cardType = "";

        if (cardLength == 16 && cardNumberStr.startsWith("4")) {
            cardType = "Visa";
        } else if (cardLength == 16 && cardNumberStr.startsWith("5")) {
            cardType = "MasterCard";
        } else if (cardLength == 15 && cardNumberStr.startsWith("37")) {
            cardType = "American Express";
        } else if (cardLength == 16 && cardNumberStr.startsWith("6")) {
            cardType = "Discover";
        } else {
            cardType = "Invalid Card";
        }

        boolean isValid = luhnAlgorithm(cardNumber);

        String output = "*************************************************\n"
                + "**Credit Card Type: " + cardType + "\n"
                + "**Credit Card Number: " + cardNumber + "\n"
                + "**Credit Card Digit Length: " + cardLength + "\n"
                + "**Credit Card Validity: " + (isValid ? "Valid" : "Invalid") + "\n"
                + "**************************************************";

        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, kindly enter the card details to verify:");
        String input = scanner.nextLine().trim();

        try {
            long cardNumber = Long.parseLong(input);
            String result = validateCreditCard(cardNumber);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric card number.");
        }
    }
}
