package com.kylegosen.primenumbers;

import java.util.List;
import java.util.Scanner;

public class Application {
    private final static String instructions = "Please enter a range of numbers to process (ex. 1-10) or 'Q' to quit: ";
    private final static String exitMessage = "\nThanks for playing!";
    private final static String noInput = "No input detected.\n";
    private final static String notValidRange = "%s is not a valid range of numbers.\n";
    private final static String noPrimeNumbers = "No prime numbers in the provided range!";
    private final static String primeNumbersFound = "Prime numbers: %s";
    private final static String validInputRegex = "[0-9]+-[0-9]+";

    private static PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();

    /**
     * Prompts the user for input, validates the input
     *  and determines prime numbers in given range
     *
     * @param args - not supported
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("******************************************");
        System.out.println("*** Welcome to Prime Number Generator! ***");
        System.out.println("******************************************\n\n");

        String userInput;
        int[] numberParts;

        while(true){
            System.out.print(instructions);

            userInput = scanner.nextLine().trim();

            if("Q".equalsIgnoreCase(userInput)) {
                break;
            } else if(userInput.isEmpty()){
                System.out.println(noInput);
                continue;
            } else if(!isValidInput(userInput)){
                System.out.println(String.format(notValidRange, userInput));
                continue;
            }

            numberParts = getNumbersFromRange(userInput);

            processResult(primeNumberGenerator.generate(numberParts[0], numberParts[1]));

            System.out.println("\n");
        }

        System.out.println(exitMessage);
        scanner.close();
    }

    /**
     * Tests whether input is in a valid format
     *
     * @param input - validated against
     * @return - boolean (true if valid)
     */
    protected static boolean isValidInput(String input){
        return input.matches(validInputRegex);
    }

    /**
     * Parses the user provided range and flips the order
     *  if necessary
     *
     * @param input - range to parse
     * @return - int[] of 2 numbers in range
     */
    protected static int[] getNumbersFromRange(String input){
        String[] parts = input.split("-");
        int firstNumber = Integer.parseInt(parts[0]);
        int secondNumber = Integer.parseInt(parts[1]);

        if(firstNumber > secondNumber){
            int temp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = temp;
        }

        return new int[] {firstNumber, secondNumber};
    }

    /**
     * Outputs prime numbers or lack thereof to the user
     *
     * @param primeNumbers - list of numbers to output
     */
    protected static void processResult(List<Integer> primeNumbers){
        if(primeNumbers == null || primeNumbers.isEmpty()){
            System.out.println(noPrimeNumbers);
        } else {
            System.out.println(String.format(primeNumbersFound, primeNumbers.toString()));
        }
    }
}
