import java.util.List;
import java.util.Scanner;

public class Application {

    private static PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("******************************************");
        System.out.println("*** Welcome to Prime Number Generator! ***");
        System.out.println("******************************************\n\n");

        while(true){
            System.out.print("Please enter a range of numbers for us to process (ex. 1-10) or 'Q' to quit: ");

            String userInput = scanner.next();

            if("Q".equalsIgnoreCase(userInput)){
                break;
            } else if(!isValidInput(userInput)){
                System.out.println(userInput + " is not a valid range of numbers.");
                continue;
            }

            String[] parts = userInput.split("-");
            int firstNumber = Integer.parseInt(parts[0]);
            int secondNumber = Integer.parseInt(parts[1]);

            if(firstNumber > secondNumber){
                int temp = firstNumber;
                firstNumber = secondNumber;
                secondNumber = temp;
            }

            processResult(primeNumberGenerator.generate(firstNumber, secondNumber));

            System.out.println("\n\n");
        }
    }

    protected static boolean isValidInput(String input){
        return input.matches("[0-9]+-[0-9]+");
    }

    public static void processResult(List<Integer> primeNumbers){
        if(primeNumbers == null || primeNumbers.isEmpty()){
            System.out.println("No prime numbers in the provided range!");
        } else {
            System.out.println("Prime numbers: " + primeNumbers);
        }
    }
}
