import java.util.ArrayList;
import java.util.List;

public class PrimeNumberGenerator implements PrimeNumberGeneratorImpl {

    @Override
    public List<Integer> generate(int startingValue, int endingValue) {
        List<Integer> primeNumbers = new ArrayList<>();

        for(int i=startingValue; i<=endingValue; i++){
            if(isPrime(i)){
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }

    @Override
    public boolean isPrime(int value) {
        // Special conditions
        if(value <= 1){
            return false;
        } else if(value == 2){
            return true;
        }

        if(value % 2 == 0){
            return false;
        }

        int squareRoot = (int) Math.sqrt(value) + 1;
        for(int oddNumber=3; oddNumber <= squareRoot; oddNumber += 2){
            if(value % oddNumber == 0){
                return false;
            }
        }

        return true;
    }
}
