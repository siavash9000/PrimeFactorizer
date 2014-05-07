package primefactors;

import java.util.List;
import java.util.Random;

public class PerformanceComparator {
	private IntegerFactorizer factorizer;
	
	public PerformanceComparator(IntegerFactorizer factorizer) {
		this.factorizer = factorizer;
	}
	public long getComputationLengthInMilliseconds(long toBeFactorized) {
		long start = System.currentTimeMillis();
		List<Long> result = factorizer.factorize(toBeFactorized);
		System.out.println(result);
		long length = System.currentTimeMillis() - start;
		System.out.println(length);
		return length;
	}
	
	public float lengthOfHeavyComputation() {
		PerformanceComparator comparator = new PerformanceComparator(new TrialDivision());
		Random random = new Random();
		long lowerBound = Long.MAX_VALUE - 1000000000000000000L;
		int randomRange = 1000000;
		long toBeFactorized = lowerBound+random.nextInt(randomRange);
		System.out.println(toBeFactorized);
		float computationLength = comparator.getComputationLengthInMilliseconds(toBeFactorized)/1000;
		return computationLength;
	}
	
	public static void main(String[] args) {
		PerformanceComparator performanceComparator = new PerformanceComparator(new TrialDivision());
		float computationLength = performanceComparator.lengthOfHeavyComputation();
		System.out.println(String.format("Factorization of took %f seconds.",computationLength));
	}
}
