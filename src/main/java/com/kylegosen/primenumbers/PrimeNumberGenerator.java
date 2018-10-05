package com.kylegosen.primenumbers;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberGenerator implements PrimeNumberGeneratorImpl {

    /**
     * Creates a list of prime numbers given a starting and ending value
     *
     * @param startingValue - number to start at (inclusive)
     * @param endingValue - number to end at (inclusive)
     * @return
     */
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

    /**
     * Determines whether a number is prime
     *
     * @param value - number in question
     * @return - boolean (true if prime)
     */
    @Override
    public boolean isPrime(int value) {
        // Special conditions
        if(value <= 1){
            return false;
        } else if(value == 2){
            return true;
        } else if(value % 2 == 0){
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
