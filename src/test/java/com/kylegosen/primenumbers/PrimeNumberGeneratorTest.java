package com.kylegosen.primenumbers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PrimeNumberGeneratorTest {

    private static PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();

    @Test
    public void generate_hasPrimeNumbers(){
        // First 26 prime numbers
        List<Integer> result = primeNumberGenerator.generate(0, 101);

        int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

        for(int primeNumber : primeNumbers){
            assertTrue(primeNumber + " is prime", result.contains(primeNumber));
        }

        assertEquals(result.size(), 26);

        // Special case
        result = primeNumberGenerator.generate(7900, 7920);

        assertEquals(result.size(), 3);
        assertTrue(result.contains(7901));
        assertTrue(result.contains(7907));
        assertTrue(result.contains(7919));

        // Range is inclusive
        result = primeNumberGenerator.generate(2, 5);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(5));
    }

    @Test
    public void generate_hasNoPrimeNumbers(){
        List<Integer> result = primeNumberGenerator.generate(32, 36);

        assertTrue(result.isEmpty());
    }

    @Test
    public void isPrime_true(){
        // First 26, special cases
        int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
                7901, 7907, 7919, 2147483647};

        for(int primeNumber : primeNumbers){
            assertTrue(primeNumberGenerator.isPrime(primeNumber));
        }
    }

    @Test public void isPrime_false(){
        // Special cases
        assertFalse(primeNumberGenerator.isPrime(-7));
        assertFalse(primeNumberGenerator.isPrime(-1));
        assertFalse(primeNumberGenerator.isPrime(0));
        assertFalse(primeNumberGenerator.isPrime(1));

        // Others
        assertFalse(primeNumberGenerator.isPrime(4));
        assertFalse(primeNumberGenerator.isPrime(6));
        assertFalse(primeNumberGenerator.isPrime(8));
        assertFalse(primeNumberGenerator.isPrime(25));
        assertFalse(primeNumberGenerator.isPrime(99));
        assertFalse(primeNumberGenerator.isPrime(100));
        assertFalse(primeNumberGenerator.isPrime(7900));
        assertFalse(primeNumberGenerator.isPrime(7902));
        assertFalse(primeNumberGenerator.isPrime(7917));
    }

}
