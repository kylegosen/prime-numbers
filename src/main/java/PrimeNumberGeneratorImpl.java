import java.util.List;

public interface PrimeNumberGeneratorImpl {
    List<Integer> generate(int startingValue, int endingValue);
    boolean isPrime(int value);
}
